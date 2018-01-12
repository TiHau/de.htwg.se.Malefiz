package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.Util.Observer
import de.htwg.se.malefiz.controller.{ControllerInterface, State}

case class TUI(controller: ControllerInterface) extends Observer {
  private val four: Int = 4
  var checkNotFinished: Boolean = true
  print("TUI Malefiz\n")
  print("Welcome!!!\n")
  controller.add(this)

  def printGameBoard(): Unit = {
    print(controller.gameBoard.toString() +
      "\nPlayer: " + controller.activePlayer.color +
      "\nDiced: " + controller.diced
      + "\n")
  }

  private def askForNewPlayerCount(): Unit = {
    print("Pleas type in how many Players wan't to play:\n")
    val input = readInput match {
      case Some(x) => x
      case None =>
        print("Invalid number, number of players set to 4!\n")
        four
    }
    controller.setPlayerCount(input)
    print("Thanks, Malefitz is starting now :) \n")

  }

  private def getBlockStoneDest(): Unit = {

    while (checkNotFinished) {

      print("X: ")
      val x = readInput match {
        case Some(i) => i
        case None =>
          print("wrong argument\n")
          0
      }

      val y = readInput match {
        case Some(i) => i
        case None =>
          print("wrong argument\n")
          0
      }
      if (controller.needToSetBlockStone) {
        checkNotFinished = false
      }
    }
  }

  private def getChosenPlayerStone(): Unit = {
    while (checkNotFinished) {

      print("X: ")
      val x = readInput match {
        case Some(i) => i
        case None =>
          print("wrong argument\n")
          0
      }
       val y = readInput match {
        case Some(i) => i
        case None =>
          print("wrong argument\n")
          0
      }



    }
  }

  private def askDestination(): Unit = {
    while (checkNotFinished) {

      print("X: ")
      val x = readInput match {
        case Some(i) => i
        case None =>
          print("wrong argument\n")
          0
      }

      val y = readInput match {
        case Some(i) => i
        case None =>

          0
      }
      if (controller.moveDone) {
        checkNotFinished = false
      } else {
        print("Invalid Destination! Please try again.")
      }

    }
  }

  def readInput: Option[Int] = {
    val line = scala.io.StdIn.readLine()
    line match {
      case "exit" => sys.exit(0)
      case "restart" =>
        checkNotFinished=false
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

        print("Set destination for hit Blockstone\n")
      case State.ChoosePlayerStone =>

        print("Type in Coordinates of your PlayerStone\n")
      case State.SetPlayerCount =>
        controller.commandNotExecuted= true
        askForNewPlayerCount()
      case State.ChooseTarget =>

        print("Set destination for your Stone\n")
      case State.PlayerWon => print("Player: " + controller.activePlayer.color + " Won the Game\n")
    }
  }
}
