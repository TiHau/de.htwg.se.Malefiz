package de.htwg.se.malefiz.model.fileio.fileioJson

import java.nio.file.{Files, Paths}

import de.htwg.se.malefiz.controller.{Controller, ControllerInterface, State}
import de.htwg.se.malefiz.model.fileio.FileIOInterface
import de.htwg.se.malefiz.model.gameboard._
import play.api.libs.json._
import de.htwg.se.malefiz.controller.State._

import scala.io.Source


class FileIO extends FileIOInterface {
  override def load(controller: ControllerInterface): Unit = {
    if(Files.exists(Paths.get("saveFile.json"))) {
      val source: String = Source.fromFile("saveFile.json").getLines.mkString
      val json: JsValue = Json.parse(source)
      loadBoard(json, controller)
      loadController(json, controller)
      controller.notifyObservers()
    }
  }

  private def loadController(json: JsValue, controller: ControllerInterface): Unit = {
    val activeColor = (json \ "controller" \ "activePlayer").get.toString().toInt
    if (activeColor == 1) {
      controller.activePlayer = controller.gameBoard.player1
    } else if (activeColor == 2) {
      controller.activePlayer = controller.gameBoard.player2
    } else if (activeColor == 3) {
      controller.activePlayer = controller.gameBoard.player3
    } else {
      controller.activePlayer = controller.gameBoard.player4
    }

    controller.diced = (json \ "controller" \ "diced").get.toString().toInt
    controller.state = State.fromString((json \ "controller" \ "state").get.toString().drop(1).dropRight(1)).get
    controller.needToSetBlockStone = (json \ "controller" \ "needToSetBlockStone").get.toString().toBoolean
    controller.commandNotExecuted = (json \ "controller" \ "commandNotExecuted").get.toString().toBoolean
  }


  private def loadBoard(json: JsValue, controller: ControllerInterface): Unit = {
    val playerCount = json \ "board" \ "playerCount"
    controller.setPlayerCount(playerCount.get.toString().toInt)
    val jsV:JsValue = Json.parse("" + (json \ "board" \\ "fields").head + "")
    val fieldNodes = jsV.validate[List[JsValue]].get
    for (fieldNode <- fieldNodes ){

      if (!(fieldNode \ "isFreeSpace").get.toString.toBoolean) { // ab hier Fehler weiß noch nicht welhalb 

        val x = (fieldNode \ "x").get.toString.toInt
        val y = (fieldNode \ "y").get.toString().toInt
        controller.gameBoard.board(x)(y).asInstanceOf[Field].avariable = (fieldNode \ "avariable").get.toString.toBoolean

        (fieldNode \ "sort").get.toString.last match {
          case 'p' =>

            val startFieldX = (fieldNode \ "startFieldX").get.toString.toInt
            val startFieldY = (fieldNode \ "startFieldY").get.toString.toInt

            val playerStones =
              controller.gameBoard.player1.stones ++
                controller.gameBoard.player2.stones ++
                controller.gameBoard.player3.stones ++
                controller.gameBoard.player4.stones

            for (playerStone <- playerStones) {
              if (playerStone.startField.asInstanceOf[Field].x == startFieldX && playerStone.startField.asInstanceOf[Field].y == startFieldY) {
                playerStone.actualField = controller.gameBoard.board(x)(y)
                controller.gameBoard.board(x)(y).asInstanceOf[Field].stone = playerStone
              }
            }
          case 'b' =>
            controller.gameBoard.board(x)(y).asInstanceOf[Field].stone = BlockStone()
          case 'f' =>
            controller.gameBoard.board(x)(y).asInstanceOf[Field].stone = FreeStone()
        }
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
        "needToSetBlockStone" -> JsBoolean(controller.needToSetBlockStone),
        "commandNotExecuted" -> JsBoolean(controller.commandNotExecuted)
      ),
      "board" -> Json.obj(
        "fields" -> Json.toJson(
          for {
            x <- 0 to 16
            y <- 0 to 15
          } yield JsObject(Seq("field"->fieldToJson(controller.gameBoard, x, y)))
        ),
        "playerCount" -> JsNumber(controller.gameBoard.playerCount)
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
          "isFreeSpace" -> JsBoolean(false),
          "x" -> JsNumber(x),
          "y" -> JsNumber(y),
          "sort" -> JsString(sort.toString),
          "avariable" -> JsBoolean(field.avariable),
          "startFieldX" -> JsNumber(startFieldX),
          "startFieldY" -> JsNumber(startFieldY)
        )
      } else {
        Json.obj(
          "isFreeSpace" -> JsBoolean(gameBoard.board(x)(y).isFreeSpace()),
          "x" -> JsNumber(x),
          "y" -> JsNumber(y),
          "sort" -> JsString(sort.toString),
          "avariable" -> JsBoolean(field.avariable)
        )
      }
    } else {
      Json.obj(
        "isFreeSpace" -> JsBoolean(true)
      )
    }
  }
}