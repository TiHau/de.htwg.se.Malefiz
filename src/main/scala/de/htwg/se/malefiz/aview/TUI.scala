package de.htwg.se.malefiz.aview

import de.htwg.se.malefiz.controller.Controller

case class TUI(controller: Controller) {

  private var input: Int = 4
  print("TUI Malefiz\n")
  print("Wellcome!!!\n")
  print("Pleas type in how many Players wan't to play:\n")
  input = scala.io.StdIn.readInt()
  controller.setPlayerCount(input)
  print("Tanks, Malefitz is starting now :) \n")

  def printGameBoard: Unit = {
    print(controller.printGameBoard() + "\n")
  }

  def changeStones: Unit = {
    controller.markAllPossibleMoves
    print(controller.printGameBoard() + "\n")

  }

  def geBlockStoneDest: (Int, Int) = {
    println("Set destination for hit Blockstone")
    print("X: ")
    val x = scala.io.StdIn.readInt()
    print("Y: ")
    val y = scala.io.StdIn.readInt()
    (x, y)
  }


}
