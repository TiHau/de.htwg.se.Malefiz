package de.htwg.se.malefiz.model

case class Player(color: Int){
  private val six = 6
  def dice(): Int ={
    val diced = scala.util.Random.nextInt(six) + 1
    diced
  }
}

