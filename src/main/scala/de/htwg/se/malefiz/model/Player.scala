package de.htwg.se.malefiz.model

case class Player(color: Int){
  private val numberOfStones = 5
  val stones: Array[PlayerStone] = Array.ofDim[PlayerStone](numberOfStones)
}

