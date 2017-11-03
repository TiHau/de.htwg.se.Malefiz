package de.htwg.se.malefiz.model

abstract class Field(x:Int, y:Int, isEmpty:Boolean, isFieldType:Char){
  def getFieldType(): Char ={
    isFieldType
  }
}
