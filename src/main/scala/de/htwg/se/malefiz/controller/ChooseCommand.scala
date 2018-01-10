package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.model.{Field, PlayerStone}

class ChooseCommand(stone: PlayerStone, controller: Controller) {

  val oldGameBord = controller.gameBoard

  override def doStep: Unit =   {
    if (stone.actualField == stone.startField) {
      val x = controller.activePlayer.stones(0).startField.asInstanceOf[Field].x
      val y = controller.activePlayer.stones(0).startField.asInstanceOf[Field].y
      markPossibleMovesR(x, y, controller.diced, ' ')
    } else {
      val x = stone.actualField.asInstanceOf[Field].x
      val y = stone.actualField.asInstanceOf[Field].y
      markPossibleMovesR(x, y, controller.diced, ' ')
    }
  }

  override def undoStep: Unit = controller.gameBoard = oldGameBord

  override def redoStep: Unit = controller.gameBoard.changeTwoStones(currentField, destField)

  private def markPossibleMovesR(x: Int, y: Int, depth: Int, cameFrom: Char): Unit = {
    if (depth == 0) {
      //Dont hit your own kind
      if (controller.gameBoard.board(x)(y).asInstanceOf[Field].stone.sort == 'p'&&controller.gameBoard.board(x)(y).asInstanceOf[Field].stone.asInstanceOf[PlayerStone].playerColor==activePlayer.color){
        return
      }
      controller.gameBoard.board(x)(y).asInstanceOf[Field].avariable = true
      return
    } else {
      // If there is a blocking stone on the way dont go on
      if (controller.gameBoard.board(x)(y).asInstanceOf[Field].stone.sort == 'b') {
        return
      }
      // up
      if (controller.validField(x, y - 1) && cameFrom != 'u') {
        markPossibleMovesR(x, y - 1, depth - 1, 'd')
      }
      // down
      if (controller.validField(x, y + 1) && cameFrom != 'd') {
        markPossibleMovesR(x, y + 1, depth - 1, 'u')
      }
      // left
      if (controller.validField(x - 1, y) && cameFrom != 'r') {
        markPossibleMovesR(x - 1, y, depth - 1, 'l')
      }
      // right
      if (controller.validField(x + 1, y) && cameFrom != 'l') {
        markPossibleMovesR(x + 1, y, depth - 1, 'r')
      }
    }
  }
}
