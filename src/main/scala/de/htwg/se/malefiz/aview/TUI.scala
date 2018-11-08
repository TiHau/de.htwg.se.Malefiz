package de.htwg.se.malefiz.aview

import com.typesafe.scalalogging.Logger
import de.htwg.se.malefiz.util.Observer
import de.htwg.se.malefiz.controller.{ ControllerInterface, State }

case class TUI(controller: ControllerInterface) extends Observer {
  private val logger = Logger(classOf[TUI])
  var checkFirst: Boolean = true
  logger.info("TUI Malefiz\n")
  logger.info("Welcome!!!\n")
  controller.add(this)

  def printGameBoard(): Unit = {
    logger.info("\nGameboard: \n" +
      controller.gameBoard.toString +
      "\nPlayer: " + controller.activePlayer.color +
      "\nDiced: " + controller.diced
      + "\n")
  }

  def readLine(): Unit = {
    val x = readInput match {
      case Some(i) => i
      case None => 0
    }
    val y = readInput match {
      case Some(i) => i
      case None => 0
    }
    controller.takeInput(x, y)

  }

  private def readInput: Option[Int] = {
    val line = scala.io.StdIn.readLine()
    line match {
      case "count" =>
        controller.newGame(4)
        None
      case "exit" => sys.exit(0)
      case "restart" =>
        checkFirst = true
        controller.reset()
        None
      case "undo" =>
        controller.undo()
        printGameBoard()
        None
      case "redo" =>
        controller.redo()
        printGameBoard()
        None
      case "enter" =>
        controller.endTurn()
        None
      case _ =>
        try {
          Some(line.toInt)
        } catch {
          case _: Throwable => None
        }
    }
  }

  override def update(): Unit = {
    controller.state match {
      case State.Print => printGameBoard()
      case State.SetBlockStone =>
        logger.info("Set destination for hit Blockstone(First Input X then Y)\n")
        printGameBoard()
      case State.ChoosePlayerStone =>
        logger.info("Type in Coordinates of your PlayerStone(First Input X then Y)\n")
        printGameBoard()
      case State.SetPlayerCount =>
      case State.ChooseTarget =>
        logger.info("Set destination for your Stone(First Input X then Y)\n")
        printGameBoard()
      case State.PlayerWon => logger.info("Player: " + controller.activePlayer.color + " Won the Game\n")
      case State.BeforeEndOfTurn => logger.info("Please type \"enter\" to go to next Move ore \"undo\" to revert\n")
      case State.EndTurn =>
    }
  }
}
