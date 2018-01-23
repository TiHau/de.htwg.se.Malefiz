package de.htwg.se.malefiz.util

import com.google.inject.{Guice, Injector}
import de.htwg.se.malefiz.MalefizModule
import de.htwg.se.malefiz.aview.TUI
import de.htwg.se.malefiz.controller.{Controller, ControllerInterface}
import de.htwg.se.malefiz.model.gameboard.GameBoard
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}
@RunWith(classOf[JUnitRunner])
class ObserverSpec extends WordSpec with Matchers {
  val injector: Injector = Guice.createInjector(new MalefizModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val gui = new TUI(controller)
  controller.notifyObservers()
  controller.remove(gui)
  "A Observer" when {
    "added notifyed and removed" should {
      "have a EmptyList" in {
        controller.subscribers.isEmpty shouldBe (true)
      }

    }
  }

}
