package de.htwg.se.malefiz.model.fileio.fileioJson

import de.htwg.se.malefiz.controller.ControllerInterface
import de.htwg.se.malefiz.model.fileio.FileIOInterface
import de.htwg.se.malefiz.model.gameboard.{Field, PlayerStone}
import play.api.libs.json._


class FileIO extends FileIOInterface{
  //override def load: Option[ControllerInterface] = ???

  override def save(controller: ControllerInterface): Unit ={
    import  java.io._
    val pw = new PrintWriter(new File("gameSavedMalefiz.json"))
    pw.write(Json.prettyPrint(gameBoardToJson(controller)))
    pw.close()
  }

  def gameBoardToJson(controller: ControllerInterface): JsObject = {
    var i = 0
    var jsObjectFields:JsObject = JsObject(Seq("field" + i-> JsString("")))
      for (y <- 0 to 15) {
        for (x <- 0 to 16) {
          val abstractField = controller.gameBoard.board(x)(y)
          val isfreeSpace = abstractField.isFreeSpace()
          jsObjectFields = jsObjectFields ++ JsObject(Seq("isFreeSpace"+i -> JsBoolean(isfreeSpace)))
          if (!isfreeSpace) {
            val field = abstractField.asInstanceOf[Field]
            val avariable = field.avariable
            val x = field.x
            val y = field.y
            val stone = field.stone
            val sort = stone.sort
            jsObjectFields = jsObjectFields ++ JsObject(Seq(
              "avariable" + i -> JsBoolean(avariable),
              "x" + i -> JsNumber(x),
              "y" + i -> JsNumber(y),
              "sort" -> JsString(sort.toString)))
            if (stone.sort == 'p') {
              val playerStone = stone.asInstanceOf[PlayerStone]
              val startFieldX = playerStone.startField.asInstanceOf[Field].x
              val startFieldY = playerStone.startField.asInstanceOf[Field].y
              val playerColor = playerStone.playerColor
              jsObjectFields = jsObjectFields ++ JsObject(Seq(
                "startX" + i -> JsNumber(startFieldX),
                "startY" + i -> JsNumber(startFieldY),
                "playerColor" + i -> JsNumber(playerColor)
              ))
            }
          }
          i+=1
        }
      }

    val playerCount = controller.gameBoard.playerCount
    val jsObjectBoard =JsObject(Seq("playerCount"->JsNumber(playerCount), "fields" -> jsObjectFields))
    val activeColor  =controller.activePlayer.color
    val diced = controller.diced
    val state = controller.state
    val needToSetBlockStone = controller.needToSetBlockStone
    val commandNotExecuted = controller.commandNotExecuted
    val jsObjectController = JsObject(Seq("activeColor"->JsNumber(activeColor), "diced"->JsNumber(diced),
    "state"->JsString(state.toString), "needToSetBlockStone"->JsBoolean(needToSetBlockStone),
    "commandNotExecuted"->JsBoolean(commandNotExecuted)))
    val jsObject:JsObject= JsObject(Seq("gameBoard" -> jsObjectBoard, "controller"-> jsObjectController))
    jsObject
  }


}
