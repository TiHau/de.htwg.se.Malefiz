package de.htwg.se.malefiz.model

import de.htwg.se.malefiz.controller._
import de.htwg.se.malefiz.controller.State.{
  SetPlayerCount, Print, SetBlockStone
  , ChoosePlayerStone, ChooseTarget, BeforeEndOfTurn, PlayerWon
}
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ControllerSpec extends WordSpec with Matchers {
  "A Controller" when {
    "new set" should {
      val controller = new Controller(new GameBoard(4))
      val player = Player(1)
      "can change player count specific" in {
        controller.setPlayerCount(2) equals (controller.gameBoard.playerCount)
      }

    }
  }
  "A Controller" when {
    "reset" should {
      val controller = new Controller(new GameBoard(4))
      "can reset" in {
        controller.reset()
        controller.state should be(SetPlayerCount)
      }

    }
  }

  "A Controller" when {
    "started" should {
      val controller = new Controller(new GameBoard(4))
      "when set Target" in {
        val ret = controller.setTarget(3, 14)
        ret shouldBe (false)
      }
      "take input" in {
        controller.takeInput(3, 14)
        controller.state shouldBe (Print)
      }
      "redo does nothing" in {
        controller.takeInput(3, 14)
        controller.redo()
        controller.state shouldBe (Print)
      }

      "chose Player" in {
        controller.state = ChoosePlayerStone
        controller.diced = 1
        controller.takeInput(10, 14)
        controller.undo()
        controller.state shouldBe (ChoosePlayerStone)
        controller.redo()
        controller.state shouldBe (ChooseTarget)
      }
      "choose Target" in {
        controller.takeInput(10, 13)
        controller.undo()
        controller.state shouldBe (ChooseTarget)
        controller.redo()
        controller.state shouldBe (BeforeEndOfTurn)
      }
      "chose Player not start" in {
        controller.state = ChoosePlayerStone
        controller.diced = 1
        controller.takeInput(10, 13)
        controller.undo()
        controller.state shouldBe (ChoosePlayerStone)
        controller.redo()
        controller.state shouldBe (ChooseTarget)
      }
      "choose Target not start" in {
        controller.takeInput(11, 13)
        controller.undo()
        controller.state shouldBe (ChooseTarget)
        controller.redo()
        controller.state shouldBe (BeforeEndOfTurn)
      }
      "before end of turn" in {
        controller.state = BeforeEndOfTurn
        controller.takeInput(3, 14)
        controller.state shouldBe (BeforeEndOfTurn)
      }
      "end move" in {
        controller.endTurn()
        controller.state shouldBe (ChoosePlayerStone)
      }
      "print" in {
        controller.state = Print
        controller.takeInput(3, 14)
        controller.state shouldBe (Print)
      }
      "setBlockStone" in {
        controller.state = SetBlockStone
        controller.takeInput(4, 1)
        controller.undo()
        controller.state shouldBe (SetBlockStone)
        controller.redo()
        controller.state shouldBe (BeforeEndOfTurn)
      }
      "befor end of turn" in {
        controller.state = BeforeEndOfTurn
        controller.takeInput(4, 1)
        controller.state shouldBe (BeforeEndOfTurn)
      }

      "change player from 3 to 4" in {
        controller.endTurn()
        controller.activePlayer shouldBe (controller.gameBoard.player4)
      }
      "change player from 4 to 2" in {
        controller.endTurn()
        controller.activePlayer shouldBe (controller.gameBoard.player2)
      }
      "change player from 1 to 3" in {
        controller.endTurn()
        controller.activePlayer shouldBe (controller.gameBoard.player3)
      }
      "change player from 2 to 1" in {
        controller.endTurn()
        controller.activePlayer shouldBe (controller.gameBoard.player1)
      }
      "change player from 4 to 1 with Playercount = 2" in {
        controller.setPlayerCount(2)
        controller.activePlayer = controller.gameBoard.player4
        controller.endTurn()
        controller.activePlayer shouldBe (controller.gameBoard.player1)

      }
      "check win" in {
        controller.gameBoard.board(8)(0).asInstanceOf[Field].stone =
          PlayerStone(controller.gameBoard.board(8)(0), controller.gameBoard.board(8)(0), 1)
        controller.endTurn()
        controller.state shouldBe (PlayerWon)
      }
      "invalid PlayerStone" in {
        controller.state = ChoosePlayerStone
        controller.takeInput(0, 0)
        controller.state shouldBe (ChoosePlayerStone)
      }
      "redo with empty redoStack" in {
        controller.setPlayerCount(4)
        controller.redo()
        controller.state shouldBe (ChoosePlayerStone)
      }
      "undo with empty undoStack" in {
        controller.setPlayerCount(4)
        controller.undo()
        controller.state shouldBe (ChoosePlayerStone)
      }
    }
  }


}

