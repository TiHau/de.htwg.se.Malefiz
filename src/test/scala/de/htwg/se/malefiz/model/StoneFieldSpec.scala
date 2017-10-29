package de.htwg.se.malefiz.model
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
@RunWith(classOf[JUnitRunner])
class StoneFieldSpec extends WordSpec with Matchers {
  "A Field" when {
    "new" should {
      val field = StoneField(1, 1, true, PlaceholderStone(field))

      "have a x coordinate" in {
        field.x should be(1)
      }
      "have a y coordinate" in {
        field.y should be(1)
      }
      "is the field Empty" in {
        field.isEmpty should be(true)
      }
      "have a stone" in {
        field.stone should be(PlaceholderStone(field))
      }
    }
  }
}
