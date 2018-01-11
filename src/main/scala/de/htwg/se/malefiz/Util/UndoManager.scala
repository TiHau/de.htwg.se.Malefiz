package de.htwg.se.malefiz.Util

class UndoManager {

  private var undoStack: List[Command]= Nil
  def doStep(command: Command): Unit = {
    undoStack = command::undoStack
    command.doStep()
  }
  def undoStep(): Unit = {
    undoStack match {
      case  Nil =>
      case head::stack =>
        head.undoStep()
        undoStack=stack
    }
  }
}
