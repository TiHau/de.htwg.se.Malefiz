package de.htwg.se.malefiz

import de.htwg.se.malefiz.model.{GameBoard}
import de.htwg.se.malefiz.controller.{InitializeMalefiz}

object Malefiz {
  def main(args: Array[String]): Unit = {
    InitializeMalefiz.apply().pri(InitializeMalefiz.apply().buildMalefitz(GameBoard.board))

  }
}
