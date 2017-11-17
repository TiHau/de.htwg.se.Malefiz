package de.htwg.se.malefiz.model
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GameBoardSpec extends WordSpec with Matchers{
  "A GameBoard" when {
    "initialized" should {
      val board = GameBoard(4)

      "have empty space" in {
        board.getBoard()(0)(0).isFreeSpace() should be(true)
      }
      "have no field with null" in {
        for (y <- 0 to 15) {
          for (x <- 0 to 16) {
            board.getBoard()(x)(y) shouldNot be(null)
          }
        }
      }
    }
  }
}
