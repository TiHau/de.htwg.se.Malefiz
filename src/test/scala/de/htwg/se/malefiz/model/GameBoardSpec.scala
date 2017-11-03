package de.htwg.se.malefiz.model
import org.junit.runner.RunWith
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.junit.JUnitRunner
@RunWith(classOf[JUnitRunner])
class GameBoardSpec extends WordSpec with Matchers{
  "A GameBoard" when {
    "initialized" should {
      InitializeMalefiz.apply().buildMalefitz(GameBoard.board)

      "have no null field" in {
        var x:Field=EmptyField(0,0)
        for(y<-0 to 15) {
          for (i <- 0 to 16) {
            if(GameBoard.board(i)(y)==null)
              x=null
          }
          println()
        }
        x shouldNot be(null)
      }
    }
  }
}
