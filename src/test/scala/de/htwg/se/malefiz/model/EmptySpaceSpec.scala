package de.htwg.se.malefiz.model.gameboard

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
@RunWith(classOf[JUnitRunner])
class EmptySpaceSpec extends WordSpec with Matchers {
  "A EmptySpace" when {
    "new" should {
      val field = EmptySpace()
      "freespace set true" in {
        field.isFreeSpace() should be(true)
      }
    }
  }
}
