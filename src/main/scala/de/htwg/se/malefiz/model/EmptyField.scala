package de.htwg.se.malefiz.model

case class EmptyField (x:Int, y:Int ,isEmpty:Boolean) extends Field(x, y, isEmpty, isFieldType = 'e') {

}
