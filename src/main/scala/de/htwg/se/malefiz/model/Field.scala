package de.htwg.se.malefiz.model

abstract class Field(isFieldType:Char){
  def getFieldType(): Char ={
    isFieldType
  }
}

case class EmptyField () extends Field(isFieldType = 'e') {

}

case class StoneField (x:Int, y:Int, stone:Stone) extends Field(isFieldType = 's'){

}