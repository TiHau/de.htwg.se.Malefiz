package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.util.Command
import de.htwg.se.malefiz.model.gameboard.{ Field, FreeStone, PlayerStone, Stone }

class MoveCommand(stone: PlayerStone, destField: Field, controller: ControllerInterface) extends Command {

  private val xStone = stone.actualField.asInstanceOf[Field].x
  private val yStone = stone.actualField.asInstanceOf[Field].y
  private val currentField = controller.gameBoard.board(xStone)(yStone).asInstanceOf[Field]
  private var hitStone = new Stone('f')

  override def doStep(): Unit = {

    hitStone = controller.gameBoard.moveStone(currentField, destField).get

    hitStone.sort match {
      case 'p' => controller.gameBoard.resetPlayerStone(hitStone.asInstanceOf[PlayerStone])
      case 'f' =>
      case 'b' => controller.needToSetBlockStone = true
    }

    controller.gameBoard.unmarkPossibleMoves()
  }

  override def undoStep(): Unit = {

    controller.gameBoard.forceMoveStone(destField, currentField)

    destField.stone = hitStone

    if (hitStone.sort == 'p') {
      val x = hitStone.asInstanceOf[PlayerStone].startField.asInstanceOf[Field].x
      val y = hitStone.asInstanceOf[PlayerStone].startField.asInstanceOf[Field].y
      controller.gameBoard.board(x)(y).asInstanceOf[Field].stone = FreeStone()
      hitStone.asInstanceOf[PlayerStone].actualField = destField
    }

    controller.gameBoard.markPossibleMoves(stone, controller.activePlayer, controller.diced)

    controller.needToSetBlockStone = false
  }

  override def redoStep(): Unit = doStep()

}
