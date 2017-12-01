package de.htwg.se.malefiz.model

abstract class AbstractField() {
  def isFreeSpace(): Boolean = false
}

case class EmptySpace () extends AbstractField() {
  override def isFreeSpace(): Boolean = true
}

case class Field (var x:Int,var y:Int, var stone:Stone) extends AbstractField(){
  def isEmpty(): Boolean = {
    stone.getStoneType() == 'f'
  }
}