package de.htwg.se.malefiz.model.fileio.fileioJson

import de.htwg.se.malefiz.controller.ControllerInterface
import de.htwg.se.malefiz.model.fileio.FileIOInterface
import de.htwg.se.malefiz.model.gameboard.{Field, PlayerStone}
import play.api.libs.json._

import scala.collection.immutable.TreeMap

class FileIO extends FileIOInterface{
  //override def load: Option[ControllerInterface] = ???

  override def save(controller: ControllerInterface): Unit ={
    import  java.io._
    val pw = new PrintWriter(new File("gameBoard.json"))
    pw.write(Json.prettyPrint(gameBoardToJson(controller)))
    pw.close()
  }

  def gameBoardToJson(controller: ControllerInterface): JsObject = {
    var jsObjectFields = JsObject(Seq("test"-> JsString("test")))

      for (y <- 0 to 15) {
        for (x <- 0 to 16) {
          val abstractField = controller.gameBoard.board(x)(y)
          val isfreeSpace = abstractField.isFreeSpace()
          "isFreeSpace" -> JsBoolean(isfreeSpace)
          if (!isfreeSpace) {
            val field = abstractField.asInstanceOf[Field]
            val avariable = field.avariable
            "avariable" -> JsBoolean(avariable)
            val x = field.x
            "x" -> JsNumber(x)
            val y = field.y
            "y" -> JsNumber(y)
            val stone = field.stone
            val sort = stone.sort
            "sort" -> JsString(sort.toString)
            if (stone.sort == 'p') {
              val playerStone = stone.asInstanceOf[PlayerStone]
              val startFieldX = playerStone.startField.asInstanceOf[Field].x
              "startX" -> JsNumber(startFieldX)
              val startFieldY = playerStone.startField.asInstanceOf[Field].y
              "startY" -> JsNumber(startFieldY)
              val playerColor = playerStone.playerColor
              "playerColor" -> JsNumber(playerColor)
            }
          }
        }
      }

    val playerCount = controller.gameBoard.playerCount
    var jsObjectBoard =JsObject(Seq(
    "playerCount"->JsNumber(playerCount),
      "fields" -> jsObjectFields

    )
    )
    val activeColor  =controller.activePlayer.color
    val diced = controller.diced
    val state = controller.state
    val needToSetBlockStone = controller.needToSetBlockStone
    val commandNotExecuted = controller.commandNotExecuted
    var jsObjectController = JsObject(Seq(
    "activeColor"->JsNumber(activeColor),
    "diced"->JsNumber(diced),
    "state"->JsString(state.toString),
    "needToSetBlockStone"->JsBoolean(needToSetBlockStone),
    "commandNotExecuted"->JsBoolean(commandNotExecuted)
    )
    )
    var jsObject:JsObject= JsObject(Seq(
      "gameBoard" -> jsObjectBoard,
      "controller"-> jsObjectController
    ))
    jsObject
  }


}
