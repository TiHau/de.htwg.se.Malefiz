package de.htwg.se.malefiz.model
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
@RunWith(classOf[JUnitRunner])
class FieldSpec extends WordSpec with Matchers {
  "A Field" when { "new" should {
    val field = Field(1,1,true,Player(1))
    "have a Player"  in {
      field.player should be(Player(1))
    }
    "have a x coordinate" in {
      field.x should be(1)
    }
    "have a y coordinate" in {
      field.y should be(1)
    }
    "is the field Empty" in {
      field.isEmpty should be(true)
    }

  }}
}
