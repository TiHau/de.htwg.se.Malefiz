package de.htwg.se.malefiz.model

import java.nio.file.{ Files, Paths }

import com.google.inject.name.Names
import com.google.inject.{ AbstractModule, Guice, Injector }
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.malefiz.controller.{ Controller, ControllerInterface }
import de.htwg.se.malefiz.model.fileio.{ FileIOInterface, fileioJson, fileioxml }
import de.htwg.se.malefiz.model.gameboard.{ GameBoard, GameBoardInterface }
import net.codingwell.scalaguice.ScalaModule
import org.junit.runner.RunWith
import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FileIOSpec extends WordSpec with Matchers {

  "A FileIO Json" when {
    val injector: Injector = Guice.createInjector(new MalefizModuleJsonSpec)
    val fileIO: FileIOInterface = injector.instance[FileIOInterface]
    val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
    "gameStart" should {
      if (Files.exists(Paths.get("saveFile.json"))) {
        Files.delete(Paths.get("saveFile.json"))
      } else if (Files.exists(Paths.get("saveFile.xml"))) { Files.delete(Paths.get("saveFile.xml")) }

      "have no existing File" in {
        Files.exists(Paths.get("saveFile.json")) && Files.exists(Paths.get("saveFile.xml")) shouldBe false
      }

      "should can save and load then file exists and restore count with player 3" in {
        controller.setPlayerCount(2)
        fileIO.save(controller)
        controller.setPlayerCount(4)
        fileIO.load(controller)
        Files.exists(Paths.get("saveFile.json")) || Files.exists(Paths.get("saveFile.xml")) shouldBe true
        controller.gameBoard.playerCount shouldBe 2

      }
      "should can save and load then file exists and restore count with player 2" in {
        controller.activePlayer = controller.gameBoard.player2
        controller.setPlayerCount(3)
        fileIO.save(controller)
        controller.setPlayerCount(4)
        fileIO.load(controller)
        Files.exists(Paths.get("saveFile.json")) || Files.exists(Paths.get("saveFile.xml")) shouldBe true
        controller.gameBoard.playerCount shouldBe 3

      }
      "should can save and load then file exists and restore count with player 1" in {
        controller.activePlayer = controller.gameBoard.player1
        controller.setPlayerCount(2)
        fileIO.save(controller)
        controller.setPlayerCount(4)
        fileIO.load(controller)
        Files.exists(Paths.get("saveFile.json")) || Files.exists(Paths.get("saveFile.xml")) shouldBe true
        controller.gameBoard.playerCount shouldBe 2

      }
      "should can save and load then file exists and restore count with player 4" in {
        controller.activePlayer = controller.gameBoard.player4
        controller.setPlayerCount(3)
        fileIO.save(controller)
        controller.setPlayerCount(4)
        fileIO.load(controller)
        Files.exists(Paths.get("saveFile.json")) || Files.exists(Paths.get("saveFile.xml")) shouldBe true
        controller.gameBoard.playerCount shouldBe 3

      }
      "non existing file" in {

        if (Files.exists(Paths.get("saveFile.json"))) {
          Files.delete(Paths.get("saveFile.json"))
        } else if (Files.exists(Paths.get("saveFile.xml"))) { Files.delete(Paths.get("saveFile.xml")) }
        controller.setPlayerCount(4)
        fileIO.load(controller)
        controller.gameBoard.playerCount shouldBe 4
      }
    }
  }
  "A FileIO Xml" when {
    val injector: Injector = Guice.createInjector(new MalefizModuleXmlSpec)
    val fileIO: FileIOInterface = injector.instance[FileIOInterface]
    val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
    "gameStart" should {
      if (Files.exists(Paths.get("saveFile.json"))) {
        Files.delete(Paths.get("saveFile.json"))
      } else if (Files.exists(Paths.get("saveFile.xml"))) { Files.delete(Paths.get("saveFile.xml")) }

      "have no existing File" in {
        Files.exists(Paths.get("saveFile.json")) && Files.exists(Paths.get("saveFile.xml")) shouldBe false
      }

      "should can save and load then file exists and restore count with player 3" in {
        controller.setPlayerCount(2)
        fileIO.save(controller)
        controller.setPlayerCount(4)
        fileIO.load(controller)
        Files.exists(Paths.get("saveFile.json")) || Files.exists(Paths.get("saveFile.xml")) shouldBe true
        controller.gameBoard.playerCount shouldBe 2

      }
      "should can save and load then file exists and restore count with player 2" in {
        controller.activePlayer = controller.gameBoard.player2
        controller.setPlayerCount(3)
        fileIO.save(controller)
        controller.setPlayerCount(4)
        fileIO.load(controller)
        Files.exists(Paths.get("saveFile.json")) || Files.exists(Paths.get("saveFile.xml")) shouldBe true
        controller.gameBoard.playerCount shouldBe 3

      }
      "should can save and load then file exists and restore count with player 1" in {
        controller.activePlayer = controller.gameBoard.player1
        controller.setPlayerCount(2)
        fileIO.save(controller)
        controller.setPlayerCount(4)
        fileIO.load(controller)
        Files.exists(Paths.get("saveFile.json")) || Files.exists(Paths.get("saveFile.xml")) shouldBe true
        controller.gameBoard.playerCount shouldBe 2

      }
      "should can save and load then file exists and restore count with player 4" in {
        controller.activePlayer = controller.gameBoard.player4
        controller.setPlayerCount(3)
        fileIO.save(controller)
        controller.setPlayerCount(4)
        fileIO.load(controller)
        Files.exists(Paths.get("saveFile.json")) || Files.exists(Paths.get("saveFile.xml")) shouldBe true
        controller.gameBoard.playerCount shouldBe 3

      }
      "non existing file" in {

        if (Files.exists(Paths.get("saveFile.json"))) {
          Files.delete(Paths.get("saveFile.json"))
        } else if (Files.exists(Paths.get("saveFile.xml"))) { Files.delete(Paths.get("saveFile.xml")) }
        controller.setPlayerCount(4)
        fileIO.load(controller)
        controller.gameBoard.playerCount shouldBe 4
      }
    }
  }
}

private class MalefizModuleJsonSpec extends AbstractModule with ScalaModule {
  val defaultSize: Int = 4
  val small: Int = 3
  val tiny: Int = 2

  def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
    bind[GameBoardInterface].to[GameBoard]
    bind[ControllerInterface].to[Controller]
    bind[FileIOInterface].to[fileioJson.FileIO]
    bind[GameBoardInterface].annotatedWithName("default").toInstance(GameBoard(defaultSize))
    bind[GameBoardInterface].annotatedWithName("tiny").toInstance(GameBoard(tiny))
    bind[GameBoardInterface].annotatedWithName("small").toInstance(GameBoard(small))

  }
}
private class MalefizModuleXmlSpec extends AbstractModule with ScalaModule {
  val defaultSize: Int = 4
  val small: Int = 3
  val tiny: Int = 2

  def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
    bind[GameBoardInterface].to[GameBoard]
    bind[ControllerInterface].to[Controller]
    bind[FileIOInterface].to[fileioxml.FileIO]
    bind[GameBoardInterface].annotatedWithName("default").toInstance(GameBoard(defaultSize))
    bind[GameBoardInterface].annotatedWithName("tiny").toInstance(GameBoard(tiny))
    bind[GameBoardInterface].annotatedWithName("small").toInstance(GameBoard(small))

  }
}

