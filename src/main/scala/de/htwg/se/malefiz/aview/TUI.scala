package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.Util.Observer
import de.htwg.se.malefiz.controller.{Controller, State}

case class TUI(controller: Controller) extends Observer {
  private val four: Int = 4
  var checkNotFinished: Boolean = true
  print("TUI Malefiz\n")
  print("Welcome!!!\n")
  controller.add(this)
  controller.runGame
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
    if(!controller.reset) {
      checkNotFinished = true
    }
    while (checkNotFinished) {
      print("Set destination for hit Blockstone\n")
      print("X: ")
      val x = readInput match {
        case Some(i) => i
        case None =>
          print("wrong argument\n")
          0
      }
      if(controller.reset){
        print("Pleas press Enter to restart Game!!!")
      } else {
        print("Y: ")
      }
      val y = readInput match {
        case Some(i) => i
        case None =>
          print("wrong argument\n")
          0
      }
      if (controller.isChosenBlockStone(x, y)) {
        checkNotFinished = false
      }
    }
  }

  private def getChosenPlayerStone(): Unit = {
    if(!controller.reset) {
      checkNotFinished = true
    }
    while (checkNotFinished) {
      print("Type in Coordinates of your PlayerStone\n")
      print("X: ")
      val x = readInput match {
        case Some(i) => i
        case None =>
          print("wrong argument\n")
          0
      }
      if(controller.reset){
        print("Pleas press Enter to restart Game!!!")
      }else {
        print("Y: ")
      }
      val y = readInput match {
        case Some(i) => i
        case None =>
          print("wrong argument\n")
          0
      }

      if (controller.checkValidPlayerStone(x, y)) {
        checkNotFinished = false
      } else {
        print("its not your stone\n")
      }
    }
  }

  private def askDestination(): Unit = {
    if(!controller.reset) {
      checkNotFinished = true
    }
    while (checkNotFinished) {
      print("Set destination for your Stone\n")
      print("X: ")
      val x = readInput match {
        case Some(i) => i
        case None =>
          print("wrong argument\n")
          0
      }
      if(controller.reset){
        print("Pleas press Enter to restart Game!!!")
      } else {
        print("Y: ")
      }
      val y = readInput match {
        case Some(i) => i
        case None =>
          if(!controller.reset) {
            print("wrong argument\n")
          }
          0
      }
      if (controller.makeAmove(x, y)) {
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
        controller.reset=true
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
      case State.Print => printGameBoard()
      case State.SetBlockStone => getBlockStoneDest()
      case State.ChosePlayerStone => getChosenPlayerStone()
      case State.SetPlayerCount => askForNewPlayerCount()
      case State.SetTarget => askDestination()
      case State.PlayerWon => println("Player: "+ controller.activePlayer.color+" Won the Game")
    }
  }
}
