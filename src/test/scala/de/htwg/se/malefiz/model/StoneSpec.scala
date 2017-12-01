package de.htwg.se.malefiz.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
@RunWith(classOf[JUnitRunner])
class StoneSpec extends WordSpec with Matchers {
  "A PlayerStone" when { "new" should {
    val stone:PlayerStone = PlayerStone(Field(0,0, null),Player(1))
    "have a Player"  in {
      stone.player should be(Player(1))
    }
    "have a Field" in {
      stone.field should be(Field(0,0, null))
    }
    "have stonetype p" in {
      stone.getStoneType() should be('p')
    }
  }}
  "A BlockStone" when { "new" should {
    val stone:BlockStone = BlockStone()

    "have stonetype b" in {
      stone.getStoneType() should be('b')
    }
  }}
  "A FreeStone" when { "new" should {
    val stone:FreeStone = FreeStone()

    "have stonetype f" in {
      stone.getStoneType() should be('f')
    }

  }}
}
