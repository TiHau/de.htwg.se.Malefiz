package de.htwg.se.malefiz.model

abstract class Field(x:Int, y:Int, isEmpty:Boolean, isFieldType:Char){
  def getFieldType(): Char ={
    isFieldType
  }
}

case class EmptyField (x:Int, y:Int ,isEmpty:Boolean) extends Field(x, y, isEmpty, isFieldType = 'e') {

}

case class StoneField (x:Int,y:Int ,isEmpty:Boolean, stone:Stone) extends Field(x, y, isEmpty, isFieldType = 's'){

}