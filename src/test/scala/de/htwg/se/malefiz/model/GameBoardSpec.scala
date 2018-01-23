package de.htwg.se.malefiz.model.gameboard
import org.junit.runner.RunWith
import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GameBoardSpec extends WordSpec with Matchers {
  /*
  "A GameBoard" when {
    "initialized" should {
      val board = GameBoard(4)

      "have empty space" in {
        board.board(0)(0).isFreeSpace() should be(true)
      }
      "have no field with null" in {
        for (y <- 0 to 15) {
          for (x <- 0 to 16) {
            board.board(x)(y) shouldNot be(null)
          }
        }
      }
    }
  }
  "A GameBoard" when {
    "seted all" should {
      val board = GameBoard(4)

      "have 20 Player Stones" in {
        var count = 0
        for (y <- 0 to 15) {
          for (x <- 0 to 16) {
            if (!board.board(x)(y).isFreeSpace()) {
              val field = board.board(x)(y).asInstanceOf[Field]
              if (field.stone.sort == 'p') {
                count += 1
              }
            }
          }
        }
        count shouldBe (20)
      }
    }
  }
  "A GameBoard" when {
    "toSting" should {
      val board = GameBoard(4)

      "return a  which is not empty" in {

        board.toString().isEmpty shouldBe (false)
      }
    }
  }

  "A GameBoard" when {
    "not win" should {
      val board = GameBoard(4)

      "should return false" in {

        board.checkWin.shouldBe(false)
      }
    }
  }
  "A GameBoard" when {
    "wrong Parameter" should {
      val board = GameBoard(10)
      "should have playercount 4" in {
        board.playerCount shouldBe (4)
      }
      val board2 = GameBoard(1)
      "should have playercount 2" in {
        board2.playerCount shouldBe (2)
      }
    }
  }
  "A GameBoard" when {
    "move Stone" should {
      val board = GameBoard(10)
      "Returns Option None" in {
        board.moveStone(board.board(3)(14).asInstanceOf[Field], board.board(8)(0).asInstanceOf[Field]) should be(None)
      }
    }
  }
*/
}

