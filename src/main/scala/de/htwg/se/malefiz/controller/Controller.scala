package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.model._

case class Controller(var gameBoard: GameBoard) {
  var activePlayer = gameBoard.player1
  var diced = 6

  def setPlayerCount(countPlayer: Int): Unit = {
    gameBoard = GameBoard(countPlayer)
  }

  def getGameboardToPrint(): String = {
    gameBoard.toString()
  }

  def markPossibleMoves: Unit = {
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
      if (validField(x-1, y) && cameFrom != 'r') {
        markPossibleMovesR(x -1, y, depth - 1, 'l')
      }
      // right
      if (validField(x + 1, y) && cameFrom != 'l') {
        markPossibleMovesR(x+1, y, depth - 1, 'r')
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

  def fieldHasStoneOfActivePlayer(x: Int, y: Int): Boolean = {
    if (validField(x, y)) {
      return false
    }

    val stoneSort = gameBoard.board(x)(y).asInstanceOf[Field].stone.sort
    if (stoneSort != 'p') {
      return false
    }
    if (stoneSort.asInstanceOf[PlayerStone].playerColor != activePlayer) {
      return false
    }
    true
  }

  def vaildDestForMove(x: Int, y: Int): Boolean = {
    if (gameBoard.board(x)(y).asInstanceOf[Field].avariable) {
      true
    } else {
      false
    }
  }

  def unsetDicedFields(): Unit = {
    for (y <- 0 to 15) {
      for (x <- 0 to 16) {
        if (!gameBoard.board(x)(y).isFreeSpace()) {
          gameBoard.board(x)(y).asInstanceOf[Field].avariable = false
        }
      }
    }

  }

  def fieldChange(x1: Int, y1:Int, x2:Int, y2:Int): Boolean = {
    if (!gameBoard.board(x1)(y1).isFreeSpace() || !gameBoard.board(x2)(y2).isFreeSpace()) {
      gameBoard.changeTwoStones(gameBoard.board(x1)(y1).asInstanceOf[Field],
        gameBoard.board(x2)(y2).asInstanceOf[Field]).sort match {
        case 'f' => true
        case 'p' => true
        case 'b' => false
      }
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
