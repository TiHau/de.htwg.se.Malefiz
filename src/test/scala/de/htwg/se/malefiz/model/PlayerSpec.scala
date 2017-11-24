package de.htwg.se.malefiz.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player(1)
    "have a name"  in {
      player.color should be(1)
    }

  }}
  "A Player" when { "dice" should {
    val player = Player(1)
    "have number between 1 and 6"  in {
      val diced = player.dice()
      diced >=1 && diced <=6 shouldBe(true)
    }

  }}


}
