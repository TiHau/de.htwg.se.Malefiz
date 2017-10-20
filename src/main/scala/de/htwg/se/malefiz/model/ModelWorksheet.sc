case class Player(nr:Int)
case class Stone(art:Int, p:Player)

case class Field(x:Int, y:Int, empty:Boolean, s:Stone)


object GameBoard {
  val x=16
  val y=17
  var board = Array.ofDim[Field](x,y)
}
GameBoard.board(0)(0)= Field(0,0,false,Stone(0,Player(0)))
