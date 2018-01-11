package de.htwg.se.malefiz.model

import scala.swing.Publisher

trait GameBoardInterface extends Publisher {
  private val one = 1
  private val two = 2
  private val three = 3
  private val four = 3
  def setBlockStoneOnField(field: Field): Boolean
  def removeBlockStoneOnField(field: Field): Unit
  def resetPlayerStone(stone: PlayerStone): Unit
  def moveStone(f1: Field, f2: Field): Option[Stone]
  def forceMoveStone(current: Field, dest: Field): Unit
  def markPossibleMovesR(x: Int, y: Int, depth: Int, cameFrom: Char, playerColor: Int): Unit
  def unmarkPossibleMoves(): Unit
  def checkWin: Boolean
  def validDest(x: Int, y: Int): Boolean
  val player1 = Player(one)
  val player2 = Player(two)
  val player3 = Player(three)
  val player4 = Player(four)
  def playerCount: Int
  private val x = 17
  private val y = 16
  val board: Array[Array[AbstractField]] = Array.ofDim[AbstractField](x, y)

}
