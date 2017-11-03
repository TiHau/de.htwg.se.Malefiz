package de.htwg.se.malefiz.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
@RunWith(classOf[JUnitRunner])
class StoneSpec extends WordSpec with Matchers {
  "A Stone" when { "new" should {
    val stone:PlayerStone = PlayerStone(StoneField(1,1,false, null),Player(1))
    "have a Player"  in {
      stone.player should be(Player(1))
    }

  }}
}
