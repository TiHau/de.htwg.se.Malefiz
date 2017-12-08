package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.model.GameBoard

case class Controller(var gameBoard: GameBoard) {

  def setPlayerCount(countPlayer:Int): Unit ={
    gameBoard = GameBoard(countPlayer)
  }
  def getGameboardToPrint():String={
    gameBoard.toString()
  }


}
