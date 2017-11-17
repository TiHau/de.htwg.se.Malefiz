package de.htwg.se.malefiz.aview
import de.htwg.se.malefiz.model.GameBoard
case class TUI (gameBoard: GameBoard){
  private var runs:Boolean = true
  private var input:String=""
  print("TUI Malefiz\n")
  print("Herzlich Willkommen!!!\n")
  while(runs){
    input=scala.io.StdIn.readLine()
     input match {
      case "print"=> print(gameBoard.toString())
      case "exit"=> scala.sys.exit(0)
      case _=>print("Not a Command\n")
    }
  }
}
