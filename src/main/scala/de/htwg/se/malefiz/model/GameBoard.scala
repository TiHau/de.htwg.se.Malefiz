package de.htwg.se.malefiz.model
object GameBoard {
  var board = Array.ofDim[Field]((InitializeMalefiz.apply().nu16) + 1,InitializeMalefiz.apply().nu16)
}
