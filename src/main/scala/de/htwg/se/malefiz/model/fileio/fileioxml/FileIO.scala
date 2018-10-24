package de.htwg.se.malefiz.model.fileio.fileioxml

import java.nio.file.{ Files, Paths }

import de.htwg.se.malefiz.controller.{ ControllerInterface, State }
import de.htwg.se.malefiz.model.fileio.FileIOInterface
import de.htwg.se.malefiz.model.gameboard._

import scala.xml.NodeSeq

class FileIO extends FileIOInterface {
  override def load(controller: ControllerInterface): Unit = {
    if (Files.exists(Paths.get("saveFile.xml"))) {
      val file = xml.XML.loadFile("saveFile.xml")
      val boardNode: NodeSeq = file \\ "board"
      loadBoard(boardNode, controller)
      val controllerNode = file \\ "controller"
      loadController(controllerNode, controller)

      controller.notifyObservers()
    }
  }

  private def loadBoard(boardNode: NodeSeq, controller: ControllerInterface): Unit = {
    val playerCount = boardNode \\ "playerCount"
    controller.setPlayerCount(playerCount.text.trim.toInt)

    val fieldNodes = boardNode \\ "field"
    for (fieldNode <- fieldNodes) {
      val x = (fieldNode \\ "x").text.trim.toInt
      val y = (fieldNode \\ "y").text.trim.toInt
      controller.gameBoard.board(x)(y).asInstanceOf[Field].avariable = (fieldNode \\ "avariable").text.trim.toBoolean

      (fieldNode \\ "sort").text.trim.last match {
        case 'p' =>

          val startFieldX = (fieldNode \\ "startFieldX").text.trim.toInt
          val startFieldY = (fieldNode \\ "startFieldY").text.trim.toInt

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

  private def loadController(controllerNode: NodeSeq, controller: ControllerInterface): Unit = {
    controller.needToSetBlockStone = (controllerNode \\ "needToSetBlockStone").text.trim.toBoolean
    controller.diced = (controllerNode \\ "diced").text.trim.toInt

    controller.activePlayer = (controllerNode \\ "activePlayer").text.trim.toInt match {
      case 1 =>
        controller.gameBoard.player1
      case 2 =>
        controller.gameBoard.player2
      case 3 =>
        controller.gameBoard.player3
      case 4 =>
        controller.gameBoard.player4
    }
    controller.state = State.fromString((controllerNode \\ "state").text.trim).get

    val startFieldX = (controllerNode \\ "choosenPlayerStone" \ "startX").text.trim.toInt
    val startFieldY = (controllerNode \\ "choosenPlayerStone" \ "startY").text.trim.toInt
    val playerStones =
      controller.gameBoard.player1.stones ++
        controller.gameBoard.player2.stones ++
        controller.gameBoard.player3.stones ++
        controller.gameBoard.player4.stones

    for (playerStone <- playerStones) {
      if (playerStone.startField.asInstanceOf[Field].x == startFieldX && playerStone.startField.asInstanceOf[Field].y == startFieldY) {
        controller.setChoosenPlayerStone(playerStone)
      }
    }

    controller.setDestField(controller.gameBoard.board(
      (controllerNode \\ "destField" \ "x").text.trim.toInt)(
        (controllerNode \\ "destField" \ "y").text.trim.toInt).asInstanceOf[Field])
  }

  override def save(controller: ControllerInterface): Unit = {
    xml.XML.save("saveFile.xml", gameToXml(controller))
  }

  private def gameToXml(controller: ControllerInterface) = {
    <game>
      <controller>
        <activePlayer>
          { controller.activePlayer.color }
        </activePlayer>
        <diced>
          { controller.diced }
        </diced>
        <state>
          { controller.state }
        </state>
        <choosenPlayerStone>
          <startX>
            { controller.getChoosenPlayerStone.startField.asInstanceOf[Field].x }
          </startX>
          <startY>
            { controller.getChoosenPlayerStone.startField.asInstanceOf[Field].x }
          </startY>
        </choosenPlayerStone>
        <destField>
          <x>
            { controller.getDestField.x }
          </x>
          <y>
            { controller.getDestField.y }
          </y>
        </destField>
        <needToSetBlockStone>
          { controller.needToSetBlockStone }
        </needToSetBlockStone>
      </controller>{ boardToXml(controller.gameBoard) }
    </game>

  }

  private def boardToXml(board: GameBoardInterface) = {
    <board>
      <playerCount>
        { board.playerCount }
      </playerCount>{
        for {
          x <- 0 to 16
          y <- 0 to 15
        } yield fieldToXml(board, x, y)
      }
    </board>
  }

  private def fieldToXml(board: GameBoardInterface, x: Int, y: Int) = {
    if (!board.board(x)(y).isFreeSpace()) {
      val field = board.board(x)(y).asInstanceOf[Field]
      val avariable = field.avariable
      val sort = field.stone.sort
      if (sort == 'p') {
        val startFieldX = field.stone.asInstanceOf[PlayerStone].startField.asInstanceOf[Field].x
        val startFieldY = field.stone.asInstanceOf[PlayerStone].startField.asInstanceOf[Field].y
        <field>
          <x>
            { x }
          </x>
          <y>
            { y }
          </y>
          <sort>
            { sort }
          </sort>
          <avariable>
            { avariable }
          </avariable>
          <startFieldX>
            { startFieldX }
          </startFieldX>
          <startFieldY>
            { startFieldY }
          </startFieldY>
        </field>
      } else {
        <field>
          <x>
            { x }
          </x>
          <y>
            { y }
          </y>
          <sort>
            { sort }
          </sort>
          <avariable>
            { avariable }
          </avariable>
        </field>
      }
    }
  }
}
