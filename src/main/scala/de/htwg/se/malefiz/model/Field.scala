package de.htwg.se.malefiz.model

abstract class AbstractField() {
  def isFreeSpace(): Boolean = false
}

case class EmptySpace () extends AbstractField() {
  override def isFreeSpace(): Boolean = true
}

case class Field (x:Int, y:Int, stone:Stone) extends AbstractField(){
  def isEmpty(): Boolean = {
    stone.getStoneType() == 'f'
  }
}