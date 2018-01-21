package de.htwg.se.malefiz.model.fileio.fileioJson

import de.htwg.se.malefiz.controller.ControllerInterface
import de.htwg.se.malefiz.model.fileio.FileIOInterface
import de.htwg.se.malefiz.model.gameboard.{Field, PlayerStone}
import play.api.libs.json._

class FileIO extends FileIOInterface{
  //override def load: Option[ControllerInterface] = ???

  override def save(controller: ControllerInterface): Unit ={
    import  java.io._
    val pw = new PrintWriter(new File("gameBoard.json"))
    pw.write(Json.prettyPrint(gameBoardToJson(controller)))
    pw.close()
  }

  def gameBoardToJson(controller: ControllerInterface): JsObject = {
    var jsObject = new JsObject()
    var jsObjectBoard = new JsObject()
    var jsObjectController = new JsObject()
    val playerCount = controller.gameBoard.playerCount
    jsObjectBoard.++(Json.obj("playerCount"->JsNumber(playerCount)))
    for (y <- 0 to 15) {
      for (x <- 0 to 16) {
        val abstractField = controller.gameBoard.board(x)(y)
        val isfreeSpace = abstractField.isFreeSpace()
        jsObjectBoard.++(Json.obj("isFreeSpace"->JsBoolean(isfreeSpace)))
        if (!isfreeSpace) {
          val field = abstractField.asInstanceOf[Field]
          val avariable=field.avariable
          jsObjectBoard.++(Json.obj("avariable"->JsBoolean(avariable)))
          val x = field.x
          jsObjectBoard.++(Json.obj("x"->JsNumber(x)))
          val y =field.y
          jsObjectBoard.++(Json.obj("y"->JsNumber(y)))
          val stone = field.stone
          val sort = stone.sort
          jsObjectBoard.++(Json.obj("sort"->JsString(sort.toString)))
          if (stone.sort == 'p') {
            val playerStone = stone.asInstanceOf[PlayerStone]
            val startFieldX = playerStone.startField.asInstanceOf[Field].x
            jsObjectBoard.++(Json.obj("startX"->JsNumber(startFieldX)))
            val startFieldY = playerStone.startField.asInstanceOf[Field].y
            jsObjectBoard.++(Json.obj("startY"->JsNumber(startFieldY)))
            val playerColor = playerStone.playerColor
            jsObjectBoard.++(Json.obj("playerColor"->JsNumber(playerColor)))
          }
        }
      }
    }

    val activeColor  =controller.activePlayer.color
    jsObjectController.++(Json.obj("activeColor"->JsNumber(activeColor)))
    val diced = controller.diced
    jsObjectController.++(Json.obj("diced"->JsNumber(diced)))
    val state = controller.state
    jsObjectController.++(Json.obj("state"->JsString(state.toString)))
    val needToSetBlockStone = controller.needToSetBlockStone
    jsObjectController.++(Json.obj("needToSetBlockStone"->JsBoolean(needToSetBlockStone)))
    val commandNotExecuted = controller.commandNotExecuted
    jsObjectController.++(Json.obj("commandNotExecuted"->JsBoolean(commandNotExecuted)))

    jsObject.++(jsObjectBoard)
    jsObject.++(jsObjectController)
    jsObject

  }


}
