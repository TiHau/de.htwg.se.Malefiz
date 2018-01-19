package de.htwg.se.malefiz.model.gameboard

class Stone(var sort:Char)

case class BlockStone() extends Stone(sort = 'b')

case class PlayerStone(startField:AbstractField, var actualField: AbstractField, playerColor:Int) extends Stone(sort = 'p')

case class FreeStone() extends Stone(sort = 'f')