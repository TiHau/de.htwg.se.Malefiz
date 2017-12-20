package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.controller.{Controller, Observer}

case class TUI(controller: Controller)extends Observer{
  private val four = 4

  print("TUI Malefiz\n")
  print("Welcome!!!\n")
  controller.add(this)
  def printGameBoard: Unit = {
    print(controller.gameBoard.toString() + "\n")
  }

  def startGame: Unit = {
    controller.runGame
  }
  private def askForNewPlayerCount: Unit ={
    var input: Int = four
    print("Pleas type in how many Players wan't to play:\n")
    input = scala.io.StdIn.readInt()
    controller.setPlayerCount(input)
    print("Tanks, Malefitz is starting now :) \n")
  }

  private def getBlockStoneDest: Unit = {
    var checkNotFinished: Boolean = true
    while(checkNotFinished){
      print("Set destination for hit Blockstone\n")
      print("X: ")
      val x = scala.io.StdIn.readInt()
      print("Y: ")
      val y = scala.io.StdIn.readInt()
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
      val x = scala.io.StdIn.readInt()
      print("Y: ")
      val y = scala.io.StdIn.readInt()
      if(controller.checkValidPlayerStone(x,y)){
        checkNotFinished=false
      }
      print("its not your stone")
    }
  }
  private def askDestination: Unit ={
    var checkNotFinished: Boolean = true
    while(checkNotFinished){
      print("Set destination for your Stone\n")
      print("X: ")
      val x = scala.io.StdIn.readInt()
      print("Y: ")
      val y = scala.io.StdIn.readInt()
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
