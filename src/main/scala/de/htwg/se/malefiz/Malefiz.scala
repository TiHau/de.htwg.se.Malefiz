package de.htwg.se.malefiz

import com.google.inject.{Guice, Injector}
import de.htwg.se.malefiz.controller.{Controller, ControllerInterface}
import de.htwg.se.malefiz.model.gameboard.{GameBoard, GameBoardInterface}
import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.aview.GUI

object Malefiz {
  def main(args: Array[String]): Unit = {
    val injector: Injector = Guice.createInjector(new MalefizModule)
    val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
    val tui = new TUI(controller)
    val gui = new GUI(controller)
    while (true) {
      tui.readLine()
    }
  }
}
