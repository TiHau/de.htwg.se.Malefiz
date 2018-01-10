package de.htwg.se.malefiz.Util

trait Command {

  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit

}
