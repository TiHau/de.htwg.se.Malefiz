package de.htwg.se.malefiz.model

abstract class Stone(field:AbstractField, isStoneType:Char){
  def getStoneType(): Char ={
    isStoneType
  }
}

case class BlockStone(field:AbstractField) extends Stone(field, isStoneType = 'b') {

}

case class PlayerStone(field:AbstractField,player:Player) extends Stone(field,isStoneType = 'p') {

}

case class FreeStone(field:AbstractField) extends Stone(field,isStoneType = 'f') {

}