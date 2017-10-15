case class Player(name:String)
case class Stone(name:String)
case class playerStone() extends Stone
case class NeutralStone() extends Stone
case class Cell(x:Int, y:Int,used:Stone)

val cell1 = Cell(4,5,Stone("yellow"))
cell1.x
cell1.y

case class Field(cells: Array[Cell])

val field1 = Field(Array.ofDim[Cell](1))
field1.cells(0)=cell1
field1.cells(0).x
field1.cells(0).y