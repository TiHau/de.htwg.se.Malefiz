package de.htwg.se.malefiz.model

case class StoneField (x:Int,y:Int ,isEmpty:Boolean, stone:Stone) extends Field(x, y, isEmpty, isFieldType = 's'){

}
