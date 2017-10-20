case class Player(color:Int)
case class Stone(p:Player)
abstract class Field( val x:Int,val y:Int,var isEmpty:Boolean)
case class EmptyField extends Field(x:Int, y:Int,isEmpty:Boolean)



object GameBoard {
  val x=17
  val y=16
  var board = Array.ofDim[Field](x,y)
}

for(y <- 0 until 16) {
  for (i <- 0 until 17) {
    GameBoard.board(i)(y) = EmptyField(i, y,true)
  }
}

for(y <- 0 until 16) {
  for (i <- 0 until 17) {
    if (GameBoard.board(i)(y).isEmpty) print("|0|")
  }
  println()
}