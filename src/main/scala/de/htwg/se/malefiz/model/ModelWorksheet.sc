case class Player(color:Int)
case class Stone(p:Player)
abstract class Field(  x:Int, y:Int, isEmpty:Boolean)
case class EmptyField (x:Int, y:Int ,isEmpty:Boolean) extends Field(x, y, isEmpty){


}
case class StoneField (x:Int, y:Int ,isEmpty:Boolean, s:Stone) extends Field(x, y, isEmpty){

}



object GameBoard {
  val x=17
  val y=16
  var board: Array[Array[Field]] = Array.ofDim[Field](x,y)
}

for(y <- 0 until 16) {
  for (i <- 0 until 17) {
    GameBoard.board(i)(y) = StoneField(i, y,true, Stone(Player(1)))
  }
}
val s:StoneField= GameBoard.board(0)(0).asInstanceOf[StoneField]

for(y <- 0 until 16) {
  for (i <- 0 until 17) {
    if (GameBoard.board(i)(y).asInstanceOf[StoneField].isEmpty) print("|0|")
  }
  println()
}

var p =new StoneField(1,1,true,Stone(Player(1)))

case class Dice(){
  var diced:Int = 0
  def dice(): Unit ={
    diced = scala.util.Random.nextInt(6)+1
  }
}

val k = Dice()
k.dice()
k.diced
for(x<- 0 until 600) {
  k.dice()
  println(k.diced)
}
val j = "aview-tui-gui,controller,model,util"


import scala.collection.mutable.StringBuilder
val sb = new StringBuilder()
sb.append("h")
sb.append("\n")
sb.append("k")

sb.toString()