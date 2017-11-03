package de.htwg.se.malefiz.model

abstract class Field(x:Int, y:Int, isFieldType:Char){
  def getFieldType(): Char ={
    isFieldType
  }
}

case class EmptyField (x:Int, y:Int) extends Field(x, y, isFieldType = 'e') {

}

case class StoneField (x:Int, y:Int, stone:Stone) extends Field(x, y, isFieldType = 's'){

}