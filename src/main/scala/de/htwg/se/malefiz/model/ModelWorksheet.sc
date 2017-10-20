case class Player(nr:Int)
case class Stone(art:Int, p:Player, color:Int)

case class Field(x:Int, y:Int, empty:Boolean, s:Stone)


object GameBoard {
  val x=17
  val y=16
  var board = Array.ofDim[Field](x,y)
}

for(y <- 0 until 16) {
  for (i <- 0 until 17) {
    GameBoard.board(i)(y) = Field(i, y, false, Stone(0, Player(0),0))
  }
}

for(y <- 0 until 16) {
  for (i <- 0 until 17) {
    print("|0|")
  }
  println()
}