package de.htwg.se.malefitz.controller

import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.model.GameBoard.GameBoard
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}
@RunWith(classOf[JUnitRunner])
class ObserverSpec  extends WordSpec with Matchers{
  val controller = Controller(GameBoard(4))
  val gui = new TUI(controller)
  controller.notifyObservers()
  controller.remove(gui)
  "A Observer" when { "added notifyed and removed" should {
    "have a EmptyList"  in {
      controller.subscribers.isEmpty shouldBe(true)
    }

  }}

}
