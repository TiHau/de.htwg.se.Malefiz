package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.Util.Observable
import de.htwg.se.malefiz.controller.State.Print
import de.htwg.se.malefiz.model._

import scala.swing.Publisher

trait ControllerInterface extends Observable with Publisher{
  def activePlayer:Player
  def undo() : Unit
  def diced:Int
  def setPlayerCount(countPlayer: Int): Unit
  def gameBoard:GameBoardInterface
  def endTurn(): Unit
  def takeInput(X:Int,Y:Int): Unit
  def reset():Unit
  var state: State.Value = Print
  var needToSetBlockStone = false
  var moveDone = false
  var blockStoneSet = false
  var commandNotExecuted = true

}
