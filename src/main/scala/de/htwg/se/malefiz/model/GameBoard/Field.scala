package de.htwg.se.malefiz.model.GameBoard

abstract class AbstractField() {
  def isFreeSpace(): Boolean = false
}

case class EmptySpace () extends AbstractField() {
  override def isFreeSpace(): Boolean = true
}

case class Field (var x:Int,var y:Int, var stone:Stone) extends AbstractField(){
  var avariable = false
  def isEmpty: Boolean = {
    stone.sort == 'f'
  }
}