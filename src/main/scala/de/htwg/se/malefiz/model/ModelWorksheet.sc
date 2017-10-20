case class Player(nr:Int)
case class Stone(art:Int)

case class Field(x:Int, y:Int, s:Stone)

val f = Field(4,5, Stone(1))
f.x
f.y
f.s


case class PlayBoard(cells: Array[Field])

object GameBoard {
  var board = PlayBoard(Array.ofDim[Field](2))
}