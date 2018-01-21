import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.model.gameboard.GameBoard

var controller:Controller = Controller(GameBoard(4))

controller.gameBoard.board.foreach(a=>println(a))