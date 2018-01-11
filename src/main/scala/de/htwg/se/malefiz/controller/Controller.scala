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

  def setPlayerCount(countPlayer: Int): Unit = {
    gameBoard = GameBoard(countPlayer)
  }

  def undo(): Unit = {
    undoManager.undoStep()
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
      changePlayer()
      dice()
      state = Print
      notifyObservers() //print maked GameBoard
      if (!takeUserChange()) {
        return
      }
    }
    state = PlayerWon
    notifyObservers()
  }

  private def takeUserChange(): Boolean = {

    state = ChosePlayerStone
    notifyObservers()
    undoManager.doStep(new ChooseCommand(chosenPlayerStone, this))
    state = Print
    notifyObservers()
    state = ChooseTarget
    notifyObservers()
    state = Print
    notifyObservers()

    if (needToSetBlockStone) {
      state = SetBlockStone
      notifyObservers()
    }
    if (reset) {
      false
    } else {
      true
    }
  }

  private def executePreOrders(): Unit = {
    state = SetPlayerCount
    notifyObservers()
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

  def makeAMove(x: Int, y: Int): Unit =
    undoManager.doStep(new MoveCommand(chosenPlayerStone, gameBoard.board(x)(y).asInstanceOf[Field], this))

  def setBlockStone(x: Int, y: Int): Unit = {
    undoManager.doStep(new BlockStoneCommand(gameBoard.board(x)(y).asInstanceOf[Field], this))
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
