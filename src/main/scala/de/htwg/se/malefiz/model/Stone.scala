package de.htwg.se.malefiz.model

abstract class Stone(var sort:Char)

case class BlockStone() extends Stone(sort = 'b')

case class PlayerStone(field:AbstractField,player:Player) extends Stone(sort = 'p')

case class FreeStone() extends Stone(sort = 'f')