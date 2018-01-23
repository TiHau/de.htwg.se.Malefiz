package de.htwg.se.malefiz

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.malefiz.controller.{Controller, ControllerInterface}
import de.htwg.se.malefiz.model.fileio.{FileIOInterface, _}
import de.htwg.se.malefiz.model.gameboard.{GameBoard, GameBoardInterface}
import net.codingwell.scalaguice.ScalaModule

class MalefizModule extends AbstractModule with ScalaModule {
  val defaultSize:Int = 4
  override def configure(): Unit = {

    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
    bind[GameBoardInterface].annotatedWithName("two").toInstance(new GameBoard(2))
    bind[GameBoardInterface].annotatedWithName("three").toInstance(new GameBoard(3))
    bind[GameBoardInterface].annotatedWithName("four").toInstance(new GameBoard(4))

    bind[FileIOInterface].to[fileioxml.FileIO]
    bind[ControllerInterface].to[Controller]
  }
}
