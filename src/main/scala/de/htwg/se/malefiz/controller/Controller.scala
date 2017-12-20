package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.model._

case class Controller(var gameBoard: GameBoard) {
  var activePlayer = gameBoard.player1
  var diced = 6

  def setPlayerCount(countPlayer: Int): Unit = {
    gameBoard = GameBoard(countPlayer)
  }

  def printGameBoard(): String = {
    gameBoard.toString()
  }

  def runGame: Unit = {
    while(!checkWin) {
      activePlayer = gameBoard.player1
      dice()
      markAllPossibleMoves
      printGameBoard()
      markPossibleMovesOfStone(activePlayer.stones(0))
    }
    // do stuff if actuall player won the game
  }

  def dice(): Unit = {
    val six = 6
    diced = scala.util.Random.nextInt(six) + 1
  }

  def markAllPossibleMoves: Unit = {
    var markBaseMoves = true
    for (stone <- activePlayer.stones) {
      if (stone.actualField == stone.startField) {
        if (markBaseMoves) {
          val x = activePlayer.stones(0).startField.asInstanceOf[Field].x
          val y = activePlayer.stones(0).startField.asInstanceOf[Field].y
          markPossibleMovesR(x, y, diced, ' ')
          markBaseMoves = false
        }
      } else {
        val x = stone.actualField.asInstanceOf[Field].x
        val y = stone.actualField.asInstanceOf[Field].y
        markPossibleMovesR(x, y, diced, ' ')
      }
    }
  }

  def markPossibleMovesOfStone(stone: PlayerStone): Unit = {
    val x = stone.actualField.asInstanceOf[Field].x
    val y = stone.actualField.asInstanceOf[Field].y
    markPossibleMovesR(x, y, diced, ' ')
  }

  private def markPossibleMovesR(x: Int, y: Int, depth: Int, cameFrom: Char): Unit = {
    if (depth == 0) {
      gameBoard.board(x)(y).asInstanceOf[Field].avariable = true
      return
    } else {
      // If there is a blocking stone on the way dont go on
      if (gameBoard.board(x)(y).asInstanceOf[Field].stone.sort == 'b') {
        return
      }
      // up
      if (validField(x, y - 1) && cameFrom != 'u') {
        markPossibleMovesR(x, y - 1, depth - 1, 'd')
      }
      // down
      if (validField(x, y + 1) && cameFrom != 'd') {
        markPossibleMovesR(x, y - 1, depth - 1, 'u')
      }
      // left
      if (validField(x - 1, y) && cameFrom != 'r') {
        markPossibleMovesR(x - 1, y, depth - 1, 'l')
      }
      // right
      if (validField(x + 1, y) && cameFrom != 'l') {
        markPossibleMovesR(x + 1, y, depth - 1, 'r')
      }
    }
  }

  def validField(x: Int, y: Int): Boolean = {
    // check for a vailid field
    if (y > 13 || y < 0) {
      return false
    }
    if (x > 16 || x < 0) {
      return false
    }
    if (gameBoard.board(x)(y).isFreeSpace()) {
      return false
    }
    true
  }

  def unmarkPossibleMoves(): Unit = {
    for (y <- 0 to 15) {
      for (x <- 0 to 16) {
        if (!gameBoard.board(x)(y).isFreeSpace()) {
          gameBoard.board(x)(y).asInstanceOf[Field].avariable = false
        }
      }
    }
  }

  def makeMove(stone: PlayerStone, destField: Field): Boolean = {
    val xStone = stone.actualField.asInstanceOf[Field].x
    val yStone = stone.actualField.asInstanceOf[Field].y
    val xDest = destField.x
    val yDest = destField.y

    if (validField(xDest, yDest) && validDestForMove(xDest, yDest)) {
      val hitStone = gameBoard.changeTwoStones(stone.actualField.asInstanceOf[Field], destField)
      hitStone.sort match {
        case 'p' => gameBoard.resetPlayerStone(hitStone.asInstanceOf[PlayerStone])
        case 'f' => {}
        case 'b' => {}
      }
      true
    } else {
      false
    }
  }

  private def validDestForMove(x: Int, y: Int): Boolean = {
    if (gameBoard.board(x)(y).asInstanceOf[Field].avariable) {
      true
    } else {
      false
    }
  }

  private def isChosenBlockStone: Boolean = {
    //sende nachricht an benutzer das koordinaten eingegeben werden sollen wo der blockstein hin soll
    //blocksteine können nur auf freestone felder es muss wiederholt werden bis eines jener gewählt wurde
    false
  }

  private def checkWin: Boolean = {
    val xWin = 8
    val yWin = 0
    if (gameBoard.board(xWin)(yWin).asInstanceOf[Field].stone.sort == 'p') {
      true
    } else {
      false
    }
  }
}
