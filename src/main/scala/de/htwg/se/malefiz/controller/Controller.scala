package de.htwg.se.malefiz.controller

import com.typesafe.scalalogging.Logger
import de.htwg.se.malefiz.util.UndoManager
import de.htwg.se.malefiz.model.gameboard._
import de.htwg.se.malefiz.controller.State._

import scala.swing.Publisher

case class Controller(var gameBoard: GameBoardInterface) extends ControllerInterface with Publisher {
  private val six = 6
  private val logger = Logger(classOf[Controller])
  private val undoManager = new UndoManager()
  private var chosenPlayerStone = gameBoard.player1.stones(0)
  private var destField = gameBoard.board(8)(0).asInstanceOf[Field]


  def setPlayerCount(countPlayer: Int): Unit = {
    gameBoard = GameBoard(countPlayer)
    nextTurn()
  }

  def undo(): Unit = {
    logger.info("Active Player pressed undo")
    undoManager.undoStep()
    val oldState = state
    state = Print
    notifyObservers()
    oldState match {
      case ChooseTarget =>
        state = ChoosePlayerStone
        notifyObservers()
      case BeforeEndOfTurn => {
        if (needToSetBlockStone) {
          state = SetBlockStone
        } else {
          state = ChooseTarget
        }
        notifyObservers()
      }
      case SetBlockStone =>
        state = ChooseTarget
        notifyObservers()
      case ChoosePlayerStone =>
        state = ChoosePlayerStone
        notifyObservers()
    }
  }

  def redo(): Unit = {
    if (!undoManager.isRedoStackEmpty()) {
      logger.info("Active Player pressed redo")
      undoManager.redoStep()
      val oldState = state
      state = Print
      notifyObservers()
      oldState match {
        case ChoosePlayerStone =>
          state = ChooseTarget
          notifyObservers()
        case ChooseTarget => {
          if (needToSetBlockStone) {
            state = SetBlockStone
          } else {
            state = BeforeEndOfTurn
          }
          notifyObservers()
        }
        case SetBlockStone =>
          state = BeforeEndOfTurn
          notifyObservers()
        case BeforeEndOfTurn =>
      }
    }
  }

  def endTurn(): Unit = {
    state = EndTurn
    nextTurn()
  }

  private def nextTurn(): Unit = {
    if (!gameBoard.checkWin) {
      undoManager.clear()
      changePlayer()
      dice()
      state = Print
      notifyObservers() //print GameBoard
      state = ChoosePlayerStone
      needToSetBlockStone = false
      notifyObservers()
    } else {
      state = PlayerWon
      notifyObservers()
    }
  }

  def takeInput(x: Int, y: Int): Unit = {
    state match {
      case Print =>
      case SetPlayerCount =>
      case ChoosePlayerStone => {
        if (checkValidPlayerStone(x, y)) {
          chooseStone()
        }
      }
      case ChooseTarget => {
        if (setTarget(x, y)) {
          chooseTarget()
        }
      }
      case SetBlockStone => {
        if (setTargetForBlockStone(x, y)) {
          undoManager.doStep(new BlockStoneCommand(destField, this))
          state = Print
          notifyObservers()
          state = BeforeEndOfTurn
          notifyObservers()
        }
      }
      case PlayerWon =>
      case BeforeEndOfTurn =>
      case EndTurn =>
    }
  }

  def reset(): Unit = {
    activePlayer = gameBoard.player3
    state = SetPlayerCount
    notifyObservers()
  }

  private def chooseStone(): Unit = {
    undoManager.doStep(new ChooseCommand(chosenPlayerStone, this))
    state = Print
    notifyObservers()
    state = ChooseTarget
    notifyObservers()
  }

  private def chooseTarget(): Unit = {
    undoManager.doStep(new MoveCommand(chosenPlayerStone, destField, this))
    state = Print
    notifyObservers()
    if (needToSetBlockStone) {
      state = SetBlockStone
      notifyObservers()
    } else {
      state = BeforeEndOfTurn
      notifyObservers()
    }
  }

  private def dice(): Unit = {
    diced = scala.util.Random.nextInt(six) + 1
  }

  private def changePlayer(): Unit = {
    if (activePlayer.color == 1) {
      activePlayer = gameBoard.player4
    } else if (activePlayer.color == 4 && gameBoard.playerCount >= 3) {
      activePlayer = gameBoard.player2
    } else if (activePlayer.color == 2 && gameBoard.playerCount == 4) {
      activePlayer = gameBoard.player3
    } else if (activePlayer.color == 3) {
      activePlayer = gameBoard.player1
    } else {
      activePlayer = gameBoard.player1
    }
  }

  def setTarget(x: Int, y: Int): Boolean = {
    if (gameBoard.checkDestForPlayerStone(x, y)) {
      destField = gameBoard.board(x)(y).asInstanceOf[Field]
      true
    } else {
      false
    }
  }

  private def setTargetForBlockStone(x: Int, y: Int): Boolean = {
    if (gameBoard.checkDestForBlockStone(x, y)) {
      destField = gameBoard.board(x)(y).asInstanceOf[Field]
      true
    } else {
      false
    }

  }

  private def checkValidPlayerStone(x: Int, y: Int): Boolean = {
    if (x >= 0 && x < 17 && y >= 0 && y < 16 && (!gameBoard.board(x)(y).isFreeSpace() && gameBoard.board(x)(y).asInstanceOf[Field].stone.sort == 'p')) {
      var retBool: Boolean = false
      for (s <- activePlayer.stones) {
        if ((s.actualField.asInstanceOf[Field].x == gameBoard.board(x)(y).asInstanceOf[Field].x)
          && (s.actualField.asInstanceOf[Field].y == gameBoard.board(x)(y).asInstanceOf[Field].y)) {
          chosenPlayerStone = gameBoard.board(x)(y).asInstanceOf[Field].stone.asInstanceOf[PlayerStone]
          retBool = true
        }
      }
      retBool
    } else {
      false
    }
  }
}
