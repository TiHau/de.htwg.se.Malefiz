package de.htwg.se.malefiz.model.fileio.fileioJson

import de.htwg.se.malefiz.controller.{Controller, ControllerInterface}
import de.htwg.se.malefiz.model.fileio.FileIOInterface
import de.htwg.se.malefiz.model.gameboard.{Field, GameBoardInterface, PlayerStone}
import play.api.libs.json._
import de.htwg.se.malefiz.controller.State._

import scala.io.Source


class FileIO extends FileIOInterface {
  override def load(controller: ControllerInterface): Unit = {
    val source: String = Source.fromFile("saveFile.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    restoreGameBoard(json, controller)
    restoreController(json, controller)
    controller.notifyObservers()
  }

  private def restoreController(json: JsValue, controller: ControllerInterface): Unit = {
    val activeColor = (json \ "controller" \ "activeColor").get.toString().toInt
    val diced = (json \ "controller" \ "diced").get.toString().toInt
    val state = (json \ "controller" \ "state").get.toString()
    val needToSetBlockStone = (json \ "controller" \ "needToSetBlockStone").get.toString().toBoolean
    val commandNotExecuted = (json \ "controller" \ "commandNotExecuted").get.toString().toBoolean
    if (activeColor == 1) {
      controller.activePlayer = controller.gameBoard.player1
    } else if (activeColor == 2) {
      controller.activePlayer = controller.gameBoard.player2
    } else if (activeColor == 3) {
      controller.activePlayer = controller.gameBoard.player3
    } else {
      controller.activePlayer = controller.gameBoard.player4
    }

    controller.diced = diced
    state match {
      case "\"Print\"" => controller.state = Print
      case "\"SetPlayerCount\"" => controller.state = SetPlayerCount
      case "\"ChoosePlayerStone\"" => controller.state = ChoosePlayerStone
      case "\"ChooseTarget\"" => controller.state = ChooseTarget
      case "\"SetBlockStone\"" => controller.state = SetBlockStone
      case "\"PlayerWon\"" => controller.state = PlayerWon
      case "\"BeforeEndOfTurn\"" => controller.state = BeforeEndOfTurn
      case "\"EndTurn\"" => controller.state = EndTurn
    }
    controller.needToSetBlockStone = needToSetBlockStone
    controller.commandNotExecuted = commandNotExecuted
  }


  private def restoreGameBoard(json: JsValue, controller: ControllerInterface): Unit = {
    val playerCount = (json \ "gameBoard" \ "playerCount").get.toString().toInt
    controller.setPlayerCount(playerCount)
    var i = 0
    for (y <- 0 to 15) {
      for (x <- 0 to 16) {
        val isFreeSpace = (json \ "gameBoard" \ "fields" \ "isFreeSpace").get.toString().toBoolean
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
        i += 1
      }
    }


  }

  override def save(controller: ControllerInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("saveFile.json"))
    pw.write(Json.prettyPrint(gameToJson(controller)))
    pw.close()
  }

  def gameToJson(controller: ControllerInterface): JsObject = {
    Json.obj(
      "controller" -> Json.obj(
        "activePlayer" -> JsNumber(controller.activePlayer.color),
        "diced" -> JsNumber(controller.diced),
        "state" -> JsString(controller.state.toString),
        "needToSetBlockStone" -> JsBoolean(controller.needToSetBlockStone)
      ),
      "board" -> Json.obj(
        "fields" -> Json.toJson(
          for {
            x <- 0 to 16
            y <- 0 to 15
          } yield fieldToJson(controller.gameBoard, x, y)
        ),
        "playCount" -> JsNumber(controller.gameBoard.playerCount)
      )
    )
  }

  def fieldToJson(gameBoard: GameBoardInterface, x: Int, y: Int): JsObject = {
    if (!gameBoard.board(x)(y).isFreeSpace()) {

      val field = gameBoard.board(x)(y).asInstanceOf[Field]
      val sort = field.stone.sort
      if (sort == 'p') {
        val startFieldX = field.stone.asInstanceOf[PlayerStone].startField.asInstanceOf[Field].x
        val startFieldY = field.stone.asInstanceOf[PlayerStone].startField.asInstanceOf[Field].y
        Json.obj(
          "x" -> JsNumber(x),
          "y" -> JsNumber(y),
          "sort" -> JsString(sort.toString),
          "avariable" -> JsBoolean(field.avariable),
          "startFieldX" -> JsNumber(startFieldX),
          "startFieldY" -> JsNumber(startFieldY)
        )
      } else {
        Json.obj(
          "x" -> JsNumber(x),
          "y" -> JsNumber(y),
          "sort" -> JsString(sort.toString),
          "avariable" -> JsBoolean(field.avariable)
        )
      }
    } else {
      Json.obj(
        "isFreeSpace" -> JsBoolean(gameBoard.board(x)(y).isFreeSpace())
      )
    }
  }
}