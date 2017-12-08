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

  def printGameBoard(): Unit ={
    print(controller.getGameboardToPrint())
  }

  def changeStones(): Unit ={
    var inS1=""
    var inS2=""
    print("Bitte Stein1 Koordinaten eingeben(Bsp: 18 für X =1 Y=8): \n")
    inS1 = scala.io.StdIn.readLine()
    print("Bitte Stein2 Koordinaten eingeben: \n")
    inS2 = scala.io.StdIn.readLine()
  }

}
