package de.htwg.se.malefiz.model
object GameBoard {
  var board = Array.ofDim[AbstractField]((InitializeMalefiz.apply().nu16) + 1,InitializeMalefiz.apply().nu16)
}
