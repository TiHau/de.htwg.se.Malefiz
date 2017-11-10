package de.htwg.se.malefiz.model
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GameBoardSpec extends WordSpec with Matchers{
  "A GameBoard" when {
    "initialized" should {
      InitializeMalefiz.apply().buildMalefitz(GameBoard.apply().getBoard())

      "have empty space" in {
        GameBoard.apply().getBoard()(0)(0).isFreeSpace() should be(true)
      }
      "have no field with null" in {
        for (y <- 0 to 15) {
          for (x <- 0 to 16) {
            GameBoard.apply().getBoard()(x)(y) shouldNot be(null)
          }
        }
      }
    }
  }
}
