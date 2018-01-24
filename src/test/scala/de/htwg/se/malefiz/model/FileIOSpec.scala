package de.htwg.se.malefiz.model

import java.nio.file.{ Files, Paths }

import com.google.inject.{ Guice, Injector }
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.malefiz.MalefizModule
import de.htwg.se.malefiz.controller.ControllerInterface
import de.htwg.se.malefiz.model.fileio.FileIOInterface
import org.junit.runner.RunWith
import org.scalatest.{ Matchers, WordSpec }
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FileIOSpec extends WordSpec with Matchers {
  val injector: Injector = Guice.createInjector(new MalefizModule)
  val fileIO: FileIOInterface = injector.instance[FileIOInterface]
  var controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  "A FileIO" when {
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
        controller.setPlayerCount(3)
        fileIO.save(controller)
        controller.setPlayerCount(4)
        fileIO.load(controller)
        Files.exists(Paths.get("saveFile.json")) || Files.exists(Paths.get("saveFile.xml")) shouldBe true
        controller.gameBoard.playerCount shouldBe 3

      }
      "should can save and load then file exists and restore count with player 1" in {
        controller.setPlayerCount(2)
        fileIO.save(controller)
        controller.setPlayerCount(4)
        fileIO.load(controller)
        Files.exists(Paths.get("saveFile.json")) || Files.exists(Paths.get("saveFile.xml")) shouldBe true
        controller.gameBoard.playerCount shouldBe 2

      }
      "should can save and load then file exists and restore count with player 4" in {
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
