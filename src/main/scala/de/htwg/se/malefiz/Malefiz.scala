package de.htwg.se.malefiz

import com.google.inject.{ Guice, Injector }
import de.htwg.se.malefiz.controller.ControllerInterface
import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.aview.GUI

object Malefiz {
  def main(args: Array[String]): Unit = {
    val injector: Injector = Guice.createInjector(new MalefizModule)
    val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
    val tui = TUI(controller)
    new GUI(controller)
    while (true) {
      tui.readLine()
    }
  }
}
