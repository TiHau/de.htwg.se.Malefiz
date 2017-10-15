package de.htwg.se.yourgame

import de.htwg.se.yourgame.model.Player

object Malefiz {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)
  }
}
