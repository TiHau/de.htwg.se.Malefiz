case class Player(nr:Int)
case class Stone(art:Int, p:Player)

case class Field(x:Int, y:Int, s:Stone)

val f = Field(4,5, Stone(1,Player(0)))
f.x
f.y
f.s




object GameBoard {
  val x=16
  val y=17
  var board = Array.ofDim[Field](x,y)
}
GameBoard.board(0)(0)= Field(0,0,Stone(0,Player(0)))
