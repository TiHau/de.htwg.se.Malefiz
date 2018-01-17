package de.htwg.se.malefiz.model

case class Move(player: Player, diced: Int, stone: Stone, from: Field, to:Field, isResultMove: Boolean)