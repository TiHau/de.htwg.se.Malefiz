package de.htwg.se.malefiz.model
object GameBoard {
  private val x = 17
  private val y = 16
  var board = Array.ofDim[AbstractField](x,y)
}
