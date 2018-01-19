package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.util.Observable
import de.htwg.se.malefiz.controller.State.Print
import de.htwg.se.malefiz.model.gameboard._

import scala.swing.Publisher

trait ControllerInterface extends Observable with Publisher{
  def activePlayer:Player
  def undo() : Unit
  def redo(): Unit
  def diced:Int
  def setPlayerCount(countPlayer: Int): Unit
  def gameBoard:GameBoardInterface
  def endTurn(): Unit
  def takeInput(X:Int,Y:Int): Unit
  def reset():Unit
  var state: State.Value = Print
  var needToSetBlockStone = false
  var commandNotExecuted = true

}
