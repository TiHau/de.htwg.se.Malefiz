package de.htwg.se.malefiz

import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.model.GameBoard
import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.aview.GUI

object Malefiz {
  def main(args: Array[String]): Unit = {
    val controller = Controller(GameBoard(4))
    val gui = new GUI(controller)
    val tui = new TUI(controller)
    while(true){
      tui.readLine()
    }
  }
}
