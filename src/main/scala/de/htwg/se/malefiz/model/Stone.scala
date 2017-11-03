package de.htwg.se.malefiz.model

abstract class Stone(field:Field, isStoneType:Char){
  def getStoneType(): Char ={
    isStoneType
  }
}

case class BlockStone(field:Field) extends Stone(field, isStoneType = 'b') {

}

case class PlayerStone(field:Field,player:Player) extends Stone(field,isStoneType = 'p') {

}

case class FreeStone(field:Field) extends Stone(field,isStoneType = 'f') {

}