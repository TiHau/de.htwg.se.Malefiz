package de.htwg.se.malefiz
import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.model.GameBoard
import de.htwg.se.malefiz.aview.TUI
object Malefiz {
  def main(args: Array[String]): Unit ={
    var gameBoard = GameBoard(4)
    val controller = Controller(gameBoard)
    val tui = TUI(controller)
    var runs:Boolean = true
    var in:String=""
    while(runs){
      in=scala.io.StdIn.readLine()
      in match {
        case "print"=> tui.printGameBoard
        case "exit"=> runs = false
        case "change"=> tui.changeStones
        case _=>print("Not a Command\n")
      }
    }

  }
}
