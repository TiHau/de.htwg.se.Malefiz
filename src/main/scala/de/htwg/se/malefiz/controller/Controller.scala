package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.Util.UndoManager
import de.htwg.se.malefiz.model._
import de.htwg.se.malefiz.controller.State._

import scala.swing.Publisher

case class Controller(var gameBoard: GameBoardInterface) extends ControllerInterface with Publisher {

  private val undoManager = new UndoManager()
  private val six = 6
  var activePlayer: Player = gameBoard.player3
  var diced: Int = six
  private var chosenPlayerStone = gameBoard.player1.stones(0)
  private var destField = gameBoard.board(8)(0).asInstanceOf[Field]

  def setPlayerCount(countPlayer: Int): Unit = {
    gameBoard = GameBoard(countPlayer)
  }

  def undo(): Unit = {
    undoManager.undoStep()
    val oldState = state
    state = Print
    notifyObservers()
    oldState match {
      case ChooseTarget => state = ChoosePlayerStone
      case BeforeEndOfTurn => {
        if (needToSetBlockStone) {
          state = SetBlockStone
        } else {
          state = ChooseTarget
        }
      }
        // HIER FEHLEN NOCH CHOOSEPLAYERSTONE UND SETBLOCKSTONE
    }
  }

  def endTurn(): Unit = {
    state = EndTurn
  }

  def runGame(): Unit = {
    while (reset) {
      activePlayer = gameBoard.player3
      executePreOrders()
      executeGameRoutine()
    }
  }

  private def executeGameRoutine(): Unit = {
    reset = false
    while (!gameBoard.checkWin) {
      undoManager.clear()
      changePlayer()
      dice()
      state = Print
      notifyObservers() //print GameBoard
      state = ChoosePlayerStone
      needToSetBlockStone = false

      while (state != EndTurn) {
        oneMove()
      }

      if (reset) {
        return
      }
    }
    state = PlayerWon
    notifyObservers()
    mWait()
  }
  private def mWait(): Unit = {
    while (commandNotExecuted) {
      Thread.sleep(400)
    }
  }

  def takeInput(X:Int,Y:Int): Unit ={

  }
  private def oneMove(): Unit = {
    state match {
      case ChoosePlayerStone => chooseStone()
      case ChooseTarget => chooseTarget()
      case SetBlockStone => {
        notifyObservers()
        undoManager.doStep(new BlockStoneCommand(destField, this))
        state = Print
        notifyObservers()
        state = BeforeEndOfTurn
      }
      case BeforeEndOfTurn => {
        notifyObservers()
      }
      case EndTurn=>
    }
  }

  private def chooseStone(): Unit = {
    notifyObservers()
    mWait()
    undoManager.doStep(new ChooseCommand(chosenPlayerStone, this))
    state = Print
    notifyObservers()
    state = ChooseTarget
  }

  private def chooseTarget(): Unit = {
    notifyObservers() // get destination field
    mWait()
    undoManager.doStep(new MoveCommand(chosenPlayerStone, destField, this))
    state = Print
    notifyObservers()
    if (needToSetBlockStone) {
      state = SetBlockStone
    } else {
      state = BeforeEndOfTurn
    }
  }

  private def executePreOrders(): Unit = {
    state = SetPlayerCount
    notifyObservers()
    mWait()
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
    if (gameBoard.validDest(x, y)) {
      destField = gameBoard.board(x)(y).asInstanceOf[Field]
      true
    } else {
      false
    }
  }

  def setBlockStone(x: Int, y: Int): Unit = {
    destField = gameBoard.board(x)(y).asInstanceOf[Field]
  }

  def checkValidPlayerStone(x: Int, y: Int): Boolean = {
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
