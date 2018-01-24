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
  controller.setPlayerCount(2)
  "A FileIO" when {
    "gameStart" should {

      "have no existing File" in {
        Files.exists(Paths.get("saveFile.json")) && Files.exists(Paths.get("saveFile.xml")) shouldBe false
      }

      "should can save and load then file exists and restore count" in {
        fileIO.save(controller)
        fileIO.load(controller)
        Files.exists(Paths.get("saveFile.json")) || Files.exists(Paths.get("saveFile.xml")) shouldBe true
        controller.gameBoard.playerCount shouldBe 2

      }

    }
  }
}
