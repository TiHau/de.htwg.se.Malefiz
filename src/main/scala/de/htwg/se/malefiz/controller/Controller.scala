package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.model._

case class Controller(var gameBoard: GameBoard) {
  var onTurn = 1
  var diced = 6

  def setPlayerCount(countPlayer: Int): Unit = {
    gameBoard = GameBoard(countPlayer)
  }

  def getGameboardToPrint(): String = {
    gameBoard.toString()
  }

  def validMove(f2: String): Boolean = {
    val x2 = (f2.charAt(0).toString + f2.charAt(1)).toInt
    val y2 = (f2.charAt(2).toString + f2.charAt(3)).toInt
    if (gameBoard.board(x2)(y2).asInstanceOf[Field].avariable) {
      true
    } else {
      false
    }
  }

  def setDicedFields(f1: String): Unit = {
    val x1 = (f1.charAt(0).toString + f1.charAt(1)).toInt
    val y1 = (f1.charAt(2).toString + f1.charAt(3)).toInt
    //hier werden alle verfügbaren Felder gesetzt

  }

  def unsetDicedFields(): Unit = {
    for (y <- 0 to 15) {
      for (x <- 0 to 16) {
          if(!gameBoard.board(x)(y).isFreeSpace()){
            gameBoard.board(x)(y).asInstanceOf[Field].avariable=false
          }
      }
    }

  }

  def fieldChange(f1: String, f2: String): Boolean = {
    val x1 = (f1.charAt(0).toString + f1.charAt(1)).toInt
    val y1 = (f1.charAt(2).toString + f1.charAt(3)).toInt
    val x2 = (f2.charAt(0).toString + f2.charAt(1)).toInt
    val y2 = (f2.charAt(2).toString + f2.charAt(3)).toInt
    if (!gameBoard.board(x1)(y1).isFreeSpace() || !gameBoard.board(x2)(y2).isFreeSpace()) {
      gameBoard.changeTwoStones(gameBoard.board(x1)(y1).asInstanceOf[Field], gameBoard.board(x2)(y2).asInstanceOf[Field]) match {
        case None => false
        case Some(l) => l.sort match {
          case 'f' => true
          case 'p' => true
          case 'b' => false
        }
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
