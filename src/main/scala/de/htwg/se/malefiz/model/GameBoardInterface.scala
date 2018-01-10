package de.htwg.se.malefiz.model

import scala.swing.Publisher

trait GameBoardInterface extends Publisher {
  private val one = 1
  private val two = 2
  private val three = 3
  private val four = 3
  def setBlockStoneOnField(field: Field): Unit
  def resetPlayerStone(stone: PlayerStone): Unit
  def changeTwoStones(f1: Field, f2: Field): Stone
  val player1 = Player(one)
  val player2 = Player(two)
  val player3 = Player(three)
  val player4 = Player(four)
  def playerCount: Int
  private val x = 17
  private val y = 16
  val board = Array.ofDim[AbstractField](x, y)

}
