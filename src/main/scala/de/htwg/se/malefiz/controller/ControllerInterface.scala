package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.util.Observable
import de.htwg.se.malefiz.controller.State.Print
import de.htwg.se.malefiz.model.gameboard._

import scala.swing.Publisher

trait ControllerInterface extends Observable with Publisher{
  private val six = 6
  def undo() : Unit
  def redo(): Unit
  def newGame(countPlayer: Int): Unit
  def setPlayerCount(playerCount: Int): Unit
  def gameBoard:GameBoardInterface
  def endTurn(): Unit
  def takeInput(X:Int,Y:Int): Unit
  def reset():Unit
  def setChoosenPlayerStone(newStone: PlayerStone): Unit
  def getChoosenPlayerStone(): PlayerStone
  def setDestField(newField: Field): Unit
  def getDestField(): Field
  var state: State.Value = Print
  var needToSetBlockStone = false
  var commandNotExecuted = true
  var activePlayer: Player = gameBoard.player3
  var diced: Int = six
}
