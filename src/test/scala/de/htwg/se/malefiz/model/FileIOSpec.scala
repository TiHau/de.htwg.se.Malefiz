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
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  "A FileIO" when {
    "gameStart" should {

      "have no existing File" in {
        Files.exists(Paths.get("saveFile.json")) && Files.exists(Paths.get("saveFile.xml")) shouldBe (false)
      }

      "should can save and then file exists" in {
        fileIO.save(controller)
        Files.exists(Paths.get("saveFile.json")) || Files.exists(Paths.get("saveFile.xml")) shouldBe (true)

      }

    }
  }
}
