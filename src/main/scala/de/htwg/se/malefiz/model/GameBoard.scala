package de.htwg.se.malefiz.model
case class GameBoard() {
  private val x = 17
  private val y = 16
  private val board = Array.ofDim[AbstractField](x,y)
  def getBoard():Array[Array[AbstractField]]  ={
    board
  }
}
