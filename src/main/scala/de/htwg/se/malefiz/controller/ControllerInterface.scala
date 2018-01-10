package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.controller.State.Print
import de.htwg.se.malefiz.model._

import scala.swing.Publisher

trait ControllerInterface extends Observable with Publisher{
  def activePlayer:Player
  def diced:Int
  def runGame:Unit
  def isChosenBlockStone(x: Int,y: Int): Boolean
  def checkValidPlayerStone(x: Int,y: Int): Boolean
  def makeAmove(x:Int,y:Int): Boolean
  def setPlayerCount(countPlayer: Int): Unit
  def gameBoard:GameBoard
  var state = Print
  var reset = true

}
