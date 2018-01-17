package de.htwg.se.malefiz.model

import de.htwg.se.malefiz.controller._
import de.htwg.se.malefiz.controller.State.SetPlayerCount
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
@RunWith(classOf[JUnitRunner])
class ControllerSpec extends WordSpec with Matchers {
  "A Controller" when { "new" should {
    val controller = new Controller(new GameBoard(4))
    val player = Player(1)
    "can change player count"  in {
      controller.setPlayerCount(2) equals(controller.gameBoard.playerCount)
    }
    "can reset" in {
      controller.reset()
      controller.state should be(SetPlayerCount)
    }


  }}
}

