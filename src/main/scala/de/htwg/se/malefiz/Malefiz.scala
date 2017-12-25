package de.htwg.se.malefiz
import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.model.GameBoard
import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.aview.GUI
object Malefiz {
  def main(args: Array[String]): Unit ={
    var gameBoard = GameBoard(4)
    val controller = Controller(gameBoard)
    //val tui = new TUI(controller)
    val gui = new GUI(controller)
    controller.runGame


  }
}
