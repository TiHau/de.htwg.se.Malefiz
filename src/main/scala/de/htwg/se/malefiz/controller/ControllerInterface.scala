package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.Util.Observable
import de.htwg.se.malefiz.controller.State.Print
import de.htwg.se.malefiz.model._

import scala.swing.Publisher

trait ControllerInterface extends Observable with Publisher{
  def activePlayer:Player
  def undo() : Unit
  def diced:Int
  def runGame():Unit
  def setBlockStone(x: Int, y: Int): Unit
  def checkValidPlayerStone(x: Int,y: Int): Boolean
  def makeAMove(x:Int, y:Int): Unit
  def setPlayerCount(countPlayer: Int): Unit
  def gameBoard:GameBoardInterface
  var state: State.Value = Print
  var reset = true
  var needToSetBlockStone = false
  var moveDone = false
  var blockStoneSet = false

}
