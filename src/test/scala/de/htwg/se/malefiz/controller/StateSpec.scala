package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.controller.State._
import org.junit.runner.RunWith
import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StateSpec extends WordSpec with Matchers {
  "A State" when {
    "from Sting" should {
      "convert to state type" in {
        State.fromString("Print").get shouldBe (Print)
        State.fromString("SetPlayerCount").get shouldBe (SetPlayerCount)
        State.fromString("ChoosePlayerStone").get shouldBe (ChoosePlayerStone)
        State.fromString("ChooseTarget").get shouldBe (ChooseTarget)
        State.fromString("SetBlockStone").get shouldBe (SetBlockStone)
        State.fromString("PlayerWon").get shouldBe (PlayerWon)
        State.fromString("BeforeEndOfTurn").get shouldBe (BeforeEndOfTurn)
        State.fromString("EndTurn").get shouldBe (EndTurn)
        State.fromString("ThisGameRulesTheWorld") shouldBe (None)
      }

    }
  }
}
