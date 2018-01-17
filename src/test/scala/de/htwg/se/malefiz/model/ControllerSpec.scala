package de.htwg.se.malefiz.model

import de.htwg.se.malefiz.controller._
import de.htwg.se.malefiz.controller.State.{SetPlayerCount,Print}
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
@RunWith(classOf[JUnitRunner])
class ControllerSpec extends WordSpec with Matchers {
  "A Controller" when { "new set" should {
    val controller = new Controller(new GameBoard(4))
    val player = Player(1)
    "can change player count"  in {
      controller.setPlayerCount(2) equals(controller.gameBoard.playerCount)
    }

  }}
  "A Controller" when { "reset" should {
    val controller = new Controller(new GameBoard(4))
    "can reset" in {
      controller.reset()
      controller.state should be(SetPlayerCount)
    }

  }}

  "A Controller" when { "started" should {
    val controller = new Controller(new GameBoard(4))
    "when set Target" in{
      val ret =controller.setTarget(3,14)
      ret shouldBe(false)
    }
    "take input" in{
      controller.takeInput(3,14)
      controller.state shouldBe(Print)
    }
    "redo does nothing" in{
      controller.takeInput(3,14)
      controller.redo()
      controller.state shouldBe(Print)
    }

  }}


}

