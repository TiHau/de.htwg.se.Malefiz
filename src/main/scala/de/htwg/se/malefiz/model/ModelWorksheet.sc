case class Player(color:Int)
case class Stone(p:Player)
abstract class Field( val x:Int,val y:Int,var isEmpty:Boolean)
case class EmptyField (override val x:Int, override val y:Int ,override val isEmpty:Boolean) extends Field(x, y, isEmpty)
case class StoneField (override val x:Int, override val y:Int ,override val isEmpty:Boolean, s:Stone) extends Field(x, y, isEmpty)



object GameBoard {
  val x=17
  val y=16
  var board = Array.ofDim[Field](x,y)
}

for(y <- 0 until 16) {
  for (i <- 0 until 17) {
    GameBoard.board(i)(y) = StoneField(i, y,true, Stone(Player(1)))
  }
}

for(y <- 0 until 16) {
  for (i <- 0 until 17) {
    if (GameBoard.board(i)(y).isEmpty) print("|0|")
  }
  println()
}
