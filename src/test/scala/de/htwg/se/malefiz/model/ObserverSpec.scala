package de.htwg.se.malefiz.model

import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.controller.Controller
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner
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
