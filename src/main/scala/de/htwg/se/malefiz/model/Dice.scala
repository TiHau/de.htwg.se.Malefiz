package de.htwg.se.malefiz.model

object Dice {
  var diced:Int = 0
  private val six =6
  def dice(): Unit ={
    diced = scala.util.Random.nextInt(six) + 1
  }
}
