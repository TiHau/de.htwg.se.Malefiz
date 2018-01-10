package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.Util.Command
import de.htwg.se.malefiz.model.{Field, PlayerStone}

class MoveCommand(stone: PlayerStone, destField: Field, controller: Controller) extends Command {

  val xStone = stone.actualField.asInstanceOf[Field].x
  val yStone = stone.actualField.asInstanceOf[Field].y
  val currentField = controller.gameBoard.board(xStone)(yStone).asInstanceOf[Field]

  override def doStep: Unit =   controller.gameBoard.changeTwoStones(currentField, destField)

  override def undoStep: Unit = controller.gameBoard.changeTwoStones(destField, currentField)

  override def redoStep: Unit = controller.gameBoard.changeTwoStones(currentField, destField)
  
}
