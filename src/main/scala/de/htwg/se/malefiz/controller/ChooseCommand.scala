package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.util.Command
import de.htwg.se.malefiz.model.gameboard.{ AbstractField, Field, PlayerStone }

class ChooseCommand(stone: PlayerStone, controller: ControllerInterface) extends Command {

  override def doStep(): Unit = {
    if (stone.actualField == stone.startField) {
      val x = controller.activePlayer.stones(0).startField.asInstanceOf[Field].x
      val y = controller.activePlayer.stones(0).startField.asInstanceOf[Field].y
      controller.gameBoard.markPossibleMovesR(x, y, controller.diced, ' ', controller.activePlayer.color)
    } else {
      val x = stone.actualField.asInstanceOf[Field].x
      val y = stone.actualField.asInstanceOf[Field].y
      controller.gameBoard.markPossibleMovesR(x, y, controller.diced, ' ', controller.activePlayer.color)
    }
  }

  override def undoStep(): Unit = controller.gameBoard.unmarkPossibleMoves()

  override def redoStep(): Unit = doStep()

}
