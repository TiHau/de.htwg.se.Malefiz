package de.htwg.se.malefiz.model

case class InitializeMalefiz() {
  def buildMalefitzGameBoard(board:Array[Array[Field]]): Array[Array[Field]] ={
    val k = true
    for (i <- 0 to 16) {
      board(i)(1)=StoneField(i,1,k,PlaceholderStone(board(i)(1)))
      board(i)(11)=StoneField(i,11,k,PlaceholderStone(board(i)(11)))
      board(i)(13)=StoneField(i,13,k,PlaceholderStone(board(i)(13)))
      board(i)(3)=StoneField(i,3,k,PlaceholderStone(board(i)(3)))
      if(i%4==0)
        board(i)(12)=StoneField(i,12,k,PlaceholderStone(board(i)(12)))
      if(!(i%2==0))
        board(i)(15) = StoneField(i, 15, k, PlaceholderStone(board(i)(15)))
      if(i>=6&&i<=10)
        board(i)(5) = StoneField(i, 5, k, PlaceholderStone(board(i)(5)))
      if(i>=4&&i<=12)
        board(i)(7) = StoneField(i, 7, k, PlaceholderStone(board(i)(7)))
      if(i>=2&&i<=14)
        board(i)(9) = StoneField(i, 9, k, PlaceholderStone(board(i)(9)))
      if(i>=1&&i<=3)
        board(i)(14)=StoneField(i,14,k,PlaceholderStone(board(i)(14)))
      if(i>=5&&i<=7)
        board(i)(14)=StoneField(i,14,k,PlaceholderStone(board(i)(14)))
      if(i>=9&&i<=11)
        board(i)(14)=StoneField(i,14,k,PlaceholderStone(board(i)(14)))
      if(i>=13&&i<=15)
        board(i)(14)=StoneField(i,14,k,PlaceholderStone(board(i)(14)))
    }
    board(8)(0)= StoneField(8,0,k,PlaceholderStone(board(8)(0)))
    board(12)(8)=StoneField(12,8,k,PlaceholderStone(board(12)(8)))
    board(0)(2)=StoneField(0,2,k,PlaceholderStone(board(0)(2)))
    board(4)(8)=StoneField(4,8,k,PlaceholderStone(board(4)(8)))
    board(16)(2)=StoneField(16,2,k,PlaceholderStone(board(16)(2)))
    board(8)(4)= StoneField(8,4,k,PlaceholderStone(board(8)(4)))
    board(6)(6)=StoneField(6,6,k,PlaceholderStone(board(6)(6)))
    board(10)(6)=StoneField(10,6,k,PlaceholderStone(board(10)(6)))
    board(2)(10)=StoneField(2,10,k,PlaceholderStone(board(2)(10)))
    board(6)(10)=StoneField(6,10,k,PlaceholderStone(board(6)(10)))
    board(10)(10)=StoneField(10,10,k,PlaceholderStone(board(10)(10)))
    board(14)(10)=StoneField(14,10,k,PlaceholderStone(board(14)(10)))
    for(y<-0 to 15) {
      for (x <- 0 to 16) {
        if (board(x)(y)==null) {
          board(x)(y)=EmptyField(x,y,k)
        }
      }
    }
    return board
  }
  def pri(board:Array[Array[Field]]): Unit ={
    for(y<-0 to 15) {
      for (i <- 0 to 16) {
        if (board(i)(y).toString.charAt(0) == "E".charAt(0)) {
          print("   ")
        } else if (board(i)(y).toString.charAt(0) == "S".charAt(0)) {
          print("|1|")
        } else {
          print("|y|")
        }
      }
      println()
    }
  }

}
