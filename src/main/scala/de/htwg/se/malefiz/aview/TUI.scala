package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.Util.Observer
import de.htwg.se.malefiz.controller.{ControllerInterface, State}

case class TUI(controller: ControllerInterface) extends Observer {
  private val four: Int = 4
  var checkFirst: Boolean = true
  print("TUI Malefiz\n")
  print("Welcome!!!\n")
  controller.add(this)

  def printGameBoard(): Unit = {
    print(controller.gameBoard.toString() +
      "\nPlayer: " + controller.activePlayer.color +
      "\nDiced: " + controller.diced
      + "\n")
  }
   def readLine(): Unit = {
      val x = readInput match {
        case Some(i) => i
        case None =>0
      }
      val y = readInput match {
        case Some(i) => i
        case None =>0
      }
      controller.takeInput(x,y)


  }

  private def readInput: Option[Int] = {
    val line = scala.io.StdIn.readLine()
    line match {
      case "exit" => sys.exit(0)
      case "restart" =>
        checkFirst=true
        controller.reset()
        None
      case "undo"=>
        controller.undo()
        printGameBoard()
        None
      case "redo"=>
        controller.redo()
        printGameBoard()
        None
      case "enter"=>
        controller.endTurn()
        None
      case _ =>
        try {
          Some(line.toInt)
        } catch {
          case _:Throwable => None
        }
    }
  }

  override def update: Unit = {
    controller.state match {
      case State.Print =>  printGameBoard()
      case State.SetBlockStone =>

        print("Set destination for hit Blockstone(First Input X then Y)\n")
      case State.ChoosePlayerStone =>

        print("Type in Coordinates of your PlayerStone(First Input X then Y)\n")
      case State.SetPlayerCount =>
      case State.ChooseTarget =>
        print("Set destination for your Stone(First Input X then Y)\n")
      case State.PlayerWon => print("Player: " + controller.activePlayer.color + " Won the Game\n")
      case State.BeforeEndOfTurn => print("please type enter to go to next move ore \"undo\" tu revert\n")
      case State.EndTurn=>
    }
  }
}
