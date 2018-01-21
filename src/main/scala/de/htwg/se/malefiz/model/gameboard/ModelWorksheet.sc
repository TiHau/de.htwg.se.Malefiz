import de.htwg.se.malefiz.controller.Controller
import de.htwg.se.malefiz.model.fileio.fileioJson.FileIO
import de.htwg.se.malefiz.model.gameboard.GameBoard

var controller:Controller = Controller(GameBoard(4))
var fileIo = new FileIO
fileIo.save(controller)