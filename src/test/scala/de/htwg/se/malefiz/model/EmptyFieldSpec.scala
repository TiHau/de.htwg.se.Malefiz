package de.htwg.se.malefiz.model
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
@RunWith(classOf[JUnitRunner])
class EmptyFieldSpec extends WordSpec with Matchers {
  "A Field" when {
    "new" should {
      val field = EmptyField()


    }
  }
}

