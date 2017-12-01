package de.htwg.se.malefiz.aview
import de.htwg.se.malefiz.controller.Controller
case class TUI (controller: Controller){
  private var runs:Boolean = true
  private var input:Int=4
  private var in:String=""
  print("TUI Malefiz\n")
  print("Wellcome!!!\n")
  print("Pleas type in how many Players wan't to play:\n")
  input=scala.io.StdIn.readInt()
  controller.setPlayerCount(input)
  print("Tanks, Malefitz is starting now :) \n")
  while(runs){
    in=scala.io.StdIn.readLine()
     in match {
      case "print"=> print(controller.getGameboardToPrint())
      case "exit"=> scala.sys.exit(0)
      case _=>print("Not a Command\n")
    }
  }
}
