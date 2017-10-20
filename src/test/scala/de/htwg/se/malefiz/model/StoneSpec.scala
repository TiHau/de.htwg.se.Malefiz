package de.htwg.se.malefiz.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
@RunWith(classOf[JUnitRunner])
class StoneSpec extends WordSpec with Matchers {
  "A Stone" when { "new" should {
    val stone = Stone(Player(1))
    "have a Player"  in {
      stone.p should be(Player(1))
    }

  }}
}
