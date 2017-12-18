package de.htwg.se.malefiz.aview
import de.htwg.se.malefiz.controller.Controller
case class TUI (controller: Controller){

  private var input:Int=4
  print("TUI Malefiz\n")
  print("Wellcome!!!\n")
  print("Pleas type in how many Players wan't to play:\n")
  input=scala.io.StdIn.readInt()
  controller.setPlayerCount(input)
  print("Tanks, Malefitz is starting now :) \n")

  def printGameBoard: Unit ={
    print(controller.getGameboardToPrint() + "\n")
  }

  def changeStones: Unit ={
    controller.markPossibleMoves

    print(controller.getGameboardToPrint() + "\n")

  }

}
