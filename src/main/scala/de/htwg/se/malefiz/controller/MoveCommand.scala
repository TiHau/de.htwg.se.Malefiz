package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.Util.Command
import de.htwg.se.malefiz.model.{Field, PlayerStone, Stone}

class MoveCommand(stone: PlayerStone, destField: Field, controller: ControllerInterface) extends Command {

  private val xStone = stone.actualField.asInstanceOf[Field].x
  private val yStone = stone.actualField.asInstanceOf[Field].y
  private val currentField = controller.gameBoard.board(xStone)(yStone).asInstanceOf[Field]
  private var hitStone = new Stone('f')

  override def doStep(): Unit =   {
    val hitStoneOption = controller.gameBoard.moveStone(currentField, destField)
      if (hitStoneOption.isDefined) {
        hitStone = hitStoneOption.get
        hitStone.sort match {
          case 'p' => controller.gameBoard.resetPlayerStone(hitStone.asInstanceOf[PlayerStone])
          case 'f' =>
          case 'b' => controller.needToSetBlockStone = true
        }
        controller.gameBoard.unmarkPossibleMoves()
        controller.moveDone = true
      } else {
        controller.moveDone = false
      }
  }

  override def undoStep(): Unit = {
    controller.gameBoard.forceMoveStone(destField, currentField)
    if (hitStone.sort == 'p') {
      destField.stone = hitStone
      hitStone.asInstanceOf[PlayerStone].actualField = destField
    }

    if (stone.actualField == stone.startField) {
      val x = controller.activePlayer.stones(0).startField.asInstanceOf[Field].x
      val y = controller.activePlayer.stones(0).startField.asInstanceOf[Field].y
      controller.gameBoard.markPossibleMovesR(x, y, controller.diced, ' ', controller.activePlayer.color)
    } else {
      val x = stone.actualField.asInstanceOf[Field].x
      val y = stone.actualField.asInstanceOf[Field].y
      controller.gameBoard.markPossibleMovesR(x, y, controller.diced, ' ', controller.activePlayer.color)
    }
    controller.moveDone = false
    controller.needToSetBlockStone = false
  }

}
