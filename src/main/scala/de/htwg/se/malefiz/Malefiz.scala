package de.htwg.se.malefiz

import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.model.gameboard.GameBoard
import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.aview.GUI

object Malefiz {
  def main(args: Array[String]): Unit = {
    val controller = Controller(GameBoard(4))
    val tui = new TUI(controller)
    val gui = new GUI(controller)
    while (true) {
      tui.readLine()
    }
  }
}
