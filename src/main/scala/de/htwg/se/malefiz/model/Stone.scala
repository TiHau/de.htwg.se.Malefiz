package de.htwg.se.malefiz.model

abstract class Stone(isStoneType:Char){
  def getStoneType(): Char ={
    isStoneType
  }
}

case class BlockStone() extends Stone(isStoneType = 'b') {

}

case class PlayerStone(field:AbstractField,player:Player) extends Stone(isStoneType = 'p') {

}

case class FreeStone() extends Stone(isStoneType = 'f') {

}