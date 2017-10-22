package de.htwg.se.malefiz

import de.htwg.se.malefiz.model.{GameBoard, Player}

object Malefiz {
  def main(args: Array[String]): Unit = {
    GameBoard.build()
    GameBoard.pri()
  }
}
