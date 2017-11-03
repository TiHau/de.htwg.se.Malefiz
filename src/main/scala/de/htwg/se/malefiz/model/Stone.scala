package de.htwg.se.malefiz.model

abstract class Stone(field:Field, isStoneType:Char){
  def getStoneType(): Char ={
    isStoneType
  }
}
