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
    "A GameBoard" when {
      "seted all" should {
        val board = GameBoard(4)

        "have 20 Player Stones" in {
          var count = 0
          for (y <- 0 to 15) {
            for (x <- 0 to 16) {
              if(!board.getBoard()(x)(y).isFreeSpace()){
                val field = board.getBoard()(x)(y).asInstanceOf[Field]
                if(field.stone.getStoneType()=='p'){
                  count+=1
                }
              }
            }
          }
          count shouldBe(20)
          }
        }
      }
    "A GameBoard" when {
      "toSting" should {
        val board = GameBoard(4)

        "return a  which is not empty" in {

          board.toString().isEmpty shouldBe(false)
        }
      }
    }
  }
}
