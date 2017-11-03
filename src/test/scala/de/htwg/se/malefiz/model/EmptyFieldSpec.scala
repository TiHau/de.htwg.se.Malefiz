package de.htwg.se.malefiz.model
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
@RunWith(classOf[JUnitRunner])
class EmptyFieldSpec extends WordSpec with Matchers {
  "A Field" when {
    "new" should {
      val field = EmptyField(1, 1)

      "have a x coordinate" in {
        field.x should be(1)
      }
      "have a y coordinate" in {
        field.y should be(1)
      }

    }
  }
}

