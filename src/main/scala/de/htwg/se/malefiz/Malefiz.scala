package de.htwg.se.malefiz

import de.htwg.se.malefiz.model.GameBoard;
object Malefiz {
  def main(args: Array[String]): Unit = {
    val gameBoard = GameBoard(4)
    print(gameBoard.toString())

  }
}
