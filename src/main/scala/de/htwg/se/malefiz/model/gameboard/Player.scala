package de.htwg.se.malefiz.model.gameboard

case class Player(color: Int){
  private val numberOfStones = 5
  val stones: Array[PlayerStone] = Array.ofDim[PlayerStone](numberOfStones)
}

