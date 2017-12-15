package de.htwg.se.malefiz.model

case class Player(color: Int){
  private val numberOfStones = 5
  private val six = 6
  def dice(): Int ={
    val diced = scala.util.Random.nextInt(six) + 1
    diced
  }

  val stones = Array.ofDim[PlayerStone](numberOfStones)
}

