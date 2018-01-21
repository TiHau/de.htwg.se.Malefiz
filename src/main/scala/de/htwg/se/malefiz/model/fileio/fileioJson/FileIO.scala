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
    var jsObject= new JsObject(new TreeMap())
    var jsObjectBoard =new JsObject(new TreeMap())
    var jsObjectController =new JsObject(new TreeMap())
    val playerCount = controller.gameBoard.playerCount
    jsObjectBoard.+( "playerCount"->JsNumber(playerCount))
    for (y <- 0 to 15) {
      for (x <- 0 to 16) {
        val abstractField = controller.gameBoard.board(x)(y)
        val isfreeSpace = abstractField.isFreeSpace()
        jsObjectBoard.+("isFreeSpace"->JsBoolean(isfreeSpace))
        if (!isfreeSpace) {
          val field = abstractField.asInstanceOf[Field]
          val avariable=field.avariable
          jsObjectBoard.+("avariable"->JsBoolean(avariable))
          val x = field.x
          jsObjectBoard.+("x"->JsNumber(x))
          val y =field.y
          jsObjectBoard.+("y"->JsNumber(y))
          val stone = field.stone
          val sort = stone.sort
          jsObjectBoard.fields +"sort"->JsString(sort.toString)
          if (stone.sort == 'p') {
            val playerStone = stone.asInstanceOf[PlayerStone]
            val startFieldX = playerStone.startField.asInstanceOf[Field].x
            jsObjectBoard.fields +"startX"->JsNumber(startFieldX)
            val startFieldY = playerStone.startField.asInstanceOf[Field].y
            jsObjectBoard.fields +"startY"->JsNumber(startFieldY)
            val playerColor = playerStone.playerColor
            jsObjectBoard.fields +"playerColor"->JsNumber(playerColor)
          }
        }
      }
    }

    val activeColor  =controller.activePlayer.color
    jsObjectController.fields +"activeColor"->JsNumber(activeColor)
    val diced = controller.diced
    jsObjectController.fields +"diced"->JsNumber(diced)
    val state = controller.state
    jsObjectController.fields +"state"->JsString(state.toString)
    val needToSetBlockStone = controller.needToSetBlockStone
    jsObjectController.fields +"needToSetBlockStone"->JsBoolean(needToSetBlockStone)
    val commandNotExecuted = controller.commandNotExecuted
    jsObjectController.fields +"commandNotExecuted"->JsBoolean(commandNotExecuted)

    jsObject.+ ("gameBoard" -> jsObjectBoard)
    jsObject.+ ("controller"-> jsObjectController)
    jsObject

  }


}
