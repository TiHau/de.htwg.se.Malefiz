package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.controller.{Controller, Observer}

case class TUI(controller: Controller)extends Observer{
  private val four = 4

  print("TUI Malefiz\n")
  print("Welcome!!!\n")
  controller.add(this)
  def printGameBoard: Unit = {
    print(controller.gameBoard.toString() +
      "\nPlayer: " + controller.activePlayer.color +
      "\nDiced: " + controller.diced
      +"\n")
  }

  private def askForNewPlayerCount: Unit ={
    var input: Int = four
    print("Pleas type in how many Players wan't to play:\n")
    try {
      input = scala.io.StdIn.readInt()
    } catch {
      case _=> print("wrong insert set to 4\n")
    }
      controller.setPlayerCount(input)
      print("Tanks, Malefitz is starting now :) \n")

  }

  private def getBlockStoneDest: Unit = {
    var checkNotFinished: Boolean = true
    while(checkNotFinished){
      print("Set destination for hit Blockstone\n")
      print("X: ")
      val xS = scala.io.StdIn.readLine()
      var x = 0
      xS match {
        case "exit"=> sys.exit(0)
        case _=> {
          try {
            x = xS.toInt
          } catch {
            case _ => print("wrong argument\n")
          }
        }
      }
      print("Y: ")
      val yS = scala.io.StdIn.readLine()
      var y = 0
      yS match {
        case "exit"=> sys.exit(0)
        case _=> {
          try {
            y = yS.toInt
          } catch {
            case _ => print("wrong argument\n")
          }
        }
      }
      if(controller.isChosenBlockStone(x,y)){
        checkNotFinished=false
      }
    }
  }

  private def getChosenPlayerStone: Unit ={
    var checkNotFinished: Boolean = true
    while(checkNotFinished){
      print("Type in Coordinates of your PlayerStone\n")
      print("X: ")
      val xS = scala.io.StdIn.readLine()
      var x = 0
      xS match {
        case "exit"=> sys.exit(0)
        case _=> {
          try {
            x = xS.toInt
          } catch {
            case _ => print("wrong argument\n")
          }
        }
      }
      print("Y: ")
      val yS = scala.io.StdIn.readLine()
      var y = 0
      yS match {
        case "exit"=> sys.exit(0)
        case _=> {
          try {
            y = yS.toInt
          } catch {
            case _ => print("wrong argument\n")
          }
        }
      }


      if(controller.checkValidPlayerStone(x,y)){
        checkNotFinished=false
      }else {
        print("its not your stone\n")
      }
    }
  }
  private def askDestination: Unit ={
    var checkNotFinished: Boolean = true
    while(checkNotFinished){
      print("Set destination for your Stone\n")
      print("X: ")
      val xS = scala.io.StdIn.readLine()
      var x = 0
      xS match {
        case "exit"=> sys.exit(0)
        case _=> {
          try {
            x = xS.toInt
          } catch {
            case _ => print("wrong argument\n")
          }
        }
      }
      print("Y: ")
      val yS = scala.io.StdIn.readLine()
      var y = 0
      yS match {
        case "exit"=> sys.exit(0)
        case _=> {
          try {
            y = yS.toInt
          } catch {
            case _ => print("wrong argument\n")
          }
        }
      }
      if(controller.makeAmove(x,y)){
          checkNotFinished=false
      }

    }
  }
  override def update: Unit = {
    printGameBoard
  }

  override def setBlockstone: Unit = {
    getBlockStoneDest
  }

  override def choseAPlayerstone: Unit = {
    getChosenPlayerStone
  }

  override def setPlayerCountNew: Unit = {
    askForNewPlayerCount
  }

  override def askTarget: Unit = {
    askDestination
  }
}
