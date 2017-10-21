package de.htwg.se.malefiz.model
object GameBoard {
    val x=17
    val y=16
    var board = Array.ofDim[Field](x,y)
    def build(): Unit ={
      val k = true
      for( i <- 0 to 7 ){
        board(i)(0)= EmptyField(i,0,k)
        board(i)(4)= EmptyField(i,4,k)
      }
      board(8)(0)= StoneField(8,0,k,null)
      for( i <- 9 to 16 ){
        board(i)(0)= EmptyField(i,0,k)
        board(i)(4)= EmptyField(i,4,k)
      }
      for (i <- 0 to 16) {
        board(i)(1)=StoneField(i,1,k,null)
        board(i)(11)=StoneField(i,11,k,null)
        board(i)(13)=StoneField(i,13,k,null)
        if(i%4==0) {
          board(i)(12)=StoneField(i,12,k,null)
          board(i)(14) = EmptyField(i, 14, k)
        }
        if(i%2==0) {
          board(i)(15) = EmptyField(i, 15, k)
        }else {
          board(i)(15) = StoneField(i, 15, k, null)
        }
      }
      board(0)(2)=StoneField(0,2,k,null)
      for(i<-1 to 15 ){
        board(i)(2)=EmptyField(i,2,k)
      }
      board(16)(2)=StoneField(16,2,k,null)
      for (i <- 0 to 16) {
        board(i)(3)=StoneField(i,3,k,null)
      }
      board(8)(4)= StoneField(8,4,k,null)
      for( i <- 0 to 5 ){
        board(i)(5)= EmptyField(i,5,k)
        board(i)(6)= EmptyField(i,6,k)
      }
      for(i<- 6 to 10) {
        board(i)(5) = StoneField(i, 5, k, null)
      }
      for( i <- 11 to 16 ){
        board(i)(5)= EmptyField(i,5,k)
        board(i)(6)= EmptyField(i,6,k)
      }
      board(6)(6)=StoneField(6,6,k,null)
      for(i<- 7 to 9) {
        board(i)(6) = EmptyField(i, 6, k)
      }
      board(10)(6)=StoneField(10,6,k,null)
      for( i <- 0 to 3 ){
        board(i)(7)= EmptyField(i,7,k)
        board(i)(8)= EmptyField(i,8,k)
      }
      for(i<- 4 to 12) {
        board(i)(7) = StoneField(i, 7, k, null)
      }
      for( i <- 13 to 16 ){
        board(i)(7)= EmptyField(i,7,k)
        board(i)(8)= EmptyField(i,8,k)
      }
      board(4)(8)=StoneField(6,8,k,null)
      for(i<- 5 to 11) {
        board(i)(8) = EmptyField(i, 8, k)
      }
      board(12)(8)=StoneField(10,8,k,null)
      for( i <- 0 to 1 ){
        board(i)(9)= EmptyField(i,9,k)
        board(i)(10)= EmptyField(i,10,k)
      }
      for(i<- 2 to 14) {
        board(i)(9) = StoneField(i, 9, k, null)
      }
      for( i <- 15 to 16 ) {
        board(i)(9) = EmptyField(i, 9, k)
        board(i)(10)= EmptyField(i,10,k)
      }
      board(2)(10)=StoneField(2,10,k,null)
      for( i <- 3 to 5 ){
        board(i)(10)= EmptyField(i,10,k)
      }
      board(6)(10)=StoneField(6,10,k,null)
      for( i <- 7 to 9 ){
        board(i)(10)= EmptyField(i,10,k)
      }
      board(10)(10)=StoneField(10,10,k,null)
      for( i <- 11 to 13 ){
        board(i)(10)= EmptyField(i,10,k)
      }
      board(14)(10)=StoneField(14,10,k,null)
      for (i <- 1 to 3) {
        board(i)(12)=EmptyField(i,12,k)
        board(i)(14)=StoneField(i,14,k,null)
      }
      for (i <- 5 to 7) {
        board(i)(12)=EmptyField(i,12,k)
        board(i)(14)=StoneField(i,14,k,null)
      }
      for (i <- 9 to 11) {
        board(i)(12)=EmptyField(i,12,k)
        board(i)(14)=StoneField(i,14,k,null)
      }
      for (i <- 13 to 15) {
        board(i)(12)=EmptyField(i,12,k)
        board(i)(14)=StoneField(i,14,k,null)
      }
    }
    def pri(): Unit ={
      for(y<-0 to 15) {
        for (i <- 0 to 16) {
          if (board(i)(y).toString.charAt(0) == "E".charAt(0)) {
            print("|0|")
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
