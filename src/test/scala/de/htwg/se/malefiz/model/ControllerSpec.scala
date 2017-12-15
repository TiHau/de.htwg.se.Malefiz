package de.htwg.se.malefiz.model
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import de.htwg.se.malefiz.controller.Controller;
@RunWith(classOf[JUnitRunner])
class ControllerSpec extends WordSpec with Matchers {
    "A avariable set of Fields" when {
      val board = GameBoard(4)
      val control = Controller(board)
      "diced 6 and x y = 0714" should {
        control.setDicedFields("0714")
        "avariable set true" in {
          board.board(12)(13).asInstanceOf[Field].avariable should be(true)
          board.board(2)(13).asInstanceOf[Field].avariable should be(true)
          board.board(0)(13).asInstanceOf[Field].avariable should be(false)
          board.board(1)(13).asInstanceOf[Field].avariable should be(false)
          board.board(3)(13).asInstanceOf[Field].avariable should be(false)
          board.board(4)(13).asInstanceOf[Field].avariable should be(false)
          board.board(5)(13).asInstanceOf[Field].avariable should be(false)
          board.board(6)(13).asInstanceOf[Field].avariable should be(false)
          board.board(7)(13).asInstanceOf[Field].avariable should be(false)
          board.board(8)(13).asInstanceOf[Field].avariable should be(false)
          board.board(9)(13).asInstanceOf[Field].avariable should be(false)
          board.board(10)(13).asInstanceOf[Field].avariable should be(false)
          board.board(11)(13).asInstanceOf[Field].avariable should be(false)
          board.board(13)(13).asInstanceOf[Field].avariable should be(false)
          board.board(14)(13).asInstanceOf[Field].avariable should be(false)
          board.board(15)(13).asInstanceOf[Field].avariable should be(false)
          board.board(15)(13).asInstanceOf[Field].avariable should be(false)

        }
      }
    }

}
