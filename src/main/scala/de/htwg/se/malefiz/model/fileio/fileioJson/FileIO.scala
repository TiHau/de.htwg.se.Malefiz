package de.htwg.se.malefiz.model.fileio.fileioJson

import de.htwg.se.malefiz.controller.{Controller, ControllerInterface}
import de.htwg.se.malefiz.model.fileio.FileIOInterface
import de.htwg.se.malefiz.model.gameboard.{Field, PlayerStone}
import play.api.libs.json._
import de.htwg.se.malefiz.controller.State._

import scala.io.Source


class FileIO extends FileIOInterface{
  override def load(controller:ControllerInterface): Unit = {
    val source: String = Source.fromFile("gameSavedMalefiz.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    restoreController(json,controller)
    restoreGameBoard(json,controller)
  }
  private def restoreController(json: JsValue,controller:ControllerInterface): Unit ={
    val activeColor = (json \ "controller" \ "activeColor").get.toString().toInt
    val diced = (json \ "controller" \ "diced").get.toString().toInt
    val state = (json \ "controller" \ "state").get.toString()
    val needToSetBlockStone = (json \ "controller" \ "needToSetBlockStone").get.toString().toBoolean
    val commandNotExecuted = (json \ "controller" \ "commandNotExecuted").get.toString().toBoolean
    if(activeColor == 1) {
       controller.activePlayer = controller.gameBoard.player1
    } else if(activeColor==2) {
      controller.activePlayer = controller.gameBoard.player2
    } else if(activeColor==3) {
      controller.activePlayer = controller.gameBoard.player3
    } else {
      controller.activePlayer = controller.gameBoard.player4
    }

    controller.diced = diced
    state match{
      case "\"Print\"" => controller.state = Print
        controller.notifyObservers()
      case "\"SetPlayerCount\"" => controller.state = SetPlayerCount
        controller.notifyObservers()
      case "\"ChoosePlayerStone\"" => controller.state = ChoosePlayerStone
        controller.notifyObservers()
      case "\"ChooseTarget\"" => controller.state = ChooseTarget
        controller.notifyObservers()
      case "\"SetBlockStone\"" => controller.state = SetBlockStone
        controller.notifyObservers()
      case "\"PlayerWon\"" => controller.state = PlayerWon
        controller.notifyObservers()
      case "\"BeforeEndOfTurn\"" => controller.state = BeforeEndOfTurn
        controller.notifyObservers()
      case "\"EndTurn\"" => controller.state = EndTurn
        controller.notifyObservers()
    }
    controller.needToSetBlockStone = needToSetBlockStone
    controller.commandNotExecuted = commandNotExecuted
  }


  private def restoreGameBoard(json: JsValue,controller:ControllerInterface): Unit ={
    val playerCount = (json \ "gameBoard" \ "playerCount").get.toString().toInt
    controller.gameBoard.setPlayerCountByLoad(playerCount)
    var i = 0
    for (y <- 0 to 15) {
      for (x <- 0 to 16) {

       /* if (!isfreeSpace) {

            "avariable" + i
            "x" + i
            "y" + i
            "sort"
          if (stone.sort == 'p') {
            val playerStone = stone.asInstanceOf[PlayerStone]
            val startFieldX = playerStone.startField.asInstanceOf[Field].x
            val startFieldY = playerStone.startField.asInstanceOf[Field].y
            val playerColor = playerStone.playerColor

              "startX" + i
              "startY" + i
              "playerColor"
            ))
          }
        }*/
        i+=1
      }
    }


  }
  override def save(controller: ControllerInterface): Unit ={
    import  java.io._
    val pw = new PrintWriter(new File("gameSavedMalefiz.json"))
    pw.write(Json.prettyPrint(gameBoardToJson(controller)))
    pw.close()
  }

  def gameBoardToJson(controller: ControllerInterface): JsObject = {
    var i = 0
    var jsObjectFields:JsObject = JsObject(Seq(""-> JsString("")))
      for (y <- 0 to 15) {
        for (x <- 0 to 16) {
          val abstractField = controller.gameBoard.board(x)(y)
          val isfreeSpace = abstractField.isFreeSpace()
          jsObjectFields = jsObjectFields ++ JsObject(Seq("isFreeSpace" + i -> JsBoolean(isfreeSpace)))
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
              "sort" + i -> JsString(sort.toString)))
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
