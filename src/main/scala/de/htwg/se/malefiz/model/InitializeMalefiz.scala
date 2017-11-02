package de.htwg.se.malefiz.model

case class InitializeMalefiz() {
  def buildMalefitzGameBoard(board: Array[Array[Field]]): Array[Array[Field]] = {
    val k = true
    for (i <- 0 to 16) {
      board(i)(1) = StoneField(i, 1, k, PlaceholderStone(board(i)(1)))
      board(i)(11) = StoneField(i, 11, k, PlaceholderStone(board(i)(11)))
      board(i)(13) = StoneField(i, 13, k, PlaceholderStone(board(i)(13)))
      board(i)(3) = StoneField(i, 3, k, PlaceholderStone(board(i)(3)))
      if (i % 4 == 0)
        board(i)(12) = StoneField(i, 12, k, PlaceholderStone(board(i)(12)))
      if (!(i % 2 == 0))
        board(i)(15) = StoneField(i, 15, k, PlaceholderStone(board(i)(15)))
      if (i >= 6 && i <= 10)
        board(i)(5) = StoneField(i, 5, k, PlaceholderStone(board(i)(5)))
      if (i >= 4 && i <= 12)
        board(i)(7) = StoneField(i, 7, k, PlaceholderStone(board(i)(7)))
      if (i >= 2 && i <= 14)
        board(i)(9) = StoneField(i, 9, k, PlaceholderStone(board(i)(9)))
      if (i >= 1 && i <= 3)
        board(i)(14) = StoneField(i, 14, k, PlaceholderStone(board(i)(14)))
      if (i >= 5 && i <= 7)
        board(i)(14) = StoneField(i, 14, k, PlaceholderStone(board(i)(14)))
      if (i >= 9 && i <= 11)
        board(i)(14) = StoneField(i, 14, k, PlaceholderStone(board(i)(14)))
      if (i >= 13 && i <= 15)
        board(i)(14) = StoneField(i, 14, k, PlaceholderStone(board(i)(14)))
    }
    board(8)(0) = StoneField(8, 0, k, PlaceholderStone(board(8)(0)))
    board(12)(8) = StoneField(12, 8, k, PlaceholderStone(board(12)(8)))
    board(0)(2) = StoneField(0, 2, k, PlaceholderStone(board(0)(2)))
    board(4)(8) = StoneField(4, 8, k, PlaceholderStone(board(4)(8)))
    board(16)(2) = StoneField(16, 2, k, PlaceholderStone(board(16)(2)))
    board(8)(4) = StoneField(8, 4, k, PlaceholderStone(board(8)(4)))
    board(6)(6) = StoneField(6, 6, k, PlaceholderStone(board(6)(6)))
    board(10)(6) = StoneField(10, 6, k, PlaceholderStone(board(10)(6)))
    board(2)(10) = StoneField(2, 10, k, PlaceholderStone(board(2)(10)))
    board(6)(10) = StoneField(6, 10, k, PlaceholderStone(board(6)(10)))
    board(10)(10) = StoneField(10, 10, k, PlaceholderStone(board(10)(10)))
    board(14)(10) = StoneField(14, 10, k, PlaceholderStone(board(14)(10)))
    for (y <- 0 to 15) {
      for (x <- 0 to 16) {
        if (board(x)(y) == null) {
          board(x)(y) = EmptyField(x, y, k)
        }
      }
    }
    board
  }

  def pri(board: Array[Array[Field]]): Unit = {
    for (y <- 0 to 15) {
      for (i <- 0 to 16) {
        if (board(i)(y).toString.charAt(0) == "E".charAt(0)) {
          print("   ")
        } else if (board(i)(y).toString.charAt(0) == "S".charAt(0)) {
          val s: StoneField = board(i)(y).asInstanceOf[StoneField]
          if (s.stone.toString.charAt(0) == "P".charAt(0))
            if (s.stone.toString.charAt(3) == "c".charAt(0)) {
              print("|0|")
            } else {
              print("|P|")
            }

          if (s.stone.toString.charAt(0) == "B".charAt(0))
            print("|B|")

        } else {
          print("|y|")
        }
      }
      println()
    }
  }

  def setBlockStones(board: Array[Array[Field]]): Array[Array[Field]] = {
    board(8)(0) = StoneField(8, 0, false, BlockStone(board(8)(0)))
    board(8)(1) = StoneField(8, 1, false, BlockStone(board(8)(1)))
    board(8)(3) = StoneField(8, 3, false, BlockStone(board(8)(3)))
    board(8)(4) = StoneField(8, 4, false, BlockStone(board(8)(4)))
    board(8)(5) = StoneField(8, 5, false, BlockStone(board(8)(5)))
    board(6)(7) = StoneField(6, 7, false, BlockStone(board(6)(7)))
    board(10)(7) = StoneField(10, 7, false, BlockStone(board(10)(7)))
    board(0)(11) = StoneField(0, 11, false, BlockStone(board(0)(11)))
    board(4)(11) = StoneField(4, 11, false, BlockStone(board(4)(11)))
    board(8)(11) = StoneField(8, 11, false, BlockStone(board(8)(11)))
    board(12)(11) = StoneField(12, 11, false, BlockStone(board(12)(11)))
    board(16)(11) = StoneField(16, 11, false, BlockStone(board(16)(11)))
    board
  }

  def setPlayerStones(board: Array[Array[Field]]): Array[Array[Field]] = {
    val player1:Player = Player(1)
    val player2:Player = Player(2)
    val player3:Player = Player(3)
    val player4:Player = Player(4)
    board(1)(14) = StoneField(1, 14, false, PlayerStone(board(1)(14), player1))
    board(1)(15) = StoneField(1, 15, false, PlayerStone(board(1)(15), player1))
    board(2)(14) = StoneField(2, 14, false, PlayerStone(board(2)(14), player1))
    board(3)(14) = StoneField(3, 14, false, PlayerStone(board(3)(14), player1))
    board(3)(15) = StoneField(3, 15, false, PlayerStone(board(3)(15), player1))
    board(5)(14) = StoneField(5, 14, false, PlayerStone(board(5)(14), player2))
    board(5)(15) = StoneField(5, 15, false, PlayerStone(board(5)(15), player2))
    board(6)(14) = StoneField(6, 14, false, PlayerStone(board(6)(14), player2))
    board(7)(14) = StoneField(7, 14, false, PlayerStone(board(7)(14), player2))
    board(7)(15) = StoneField(7, 15, false, PlayerStone(board(7)(15), player2))
    board(9)(14) = StoneField(9, 14, false, PlayerStone(board(9)(14), player3))
    board(9)(15) = StoneField(9, 15, false, PlayerStone(board(9)(15), player3))
    board(10)(14) = StoneField(10, 14, false, PlayerStone(board(10)(14), player3))
    board(11)(14) = StoneField(11, 14, false, PlayerStone(board(11)(14), player3))
    board(11)(15) = StoneField(11, 15, false, PlayerStone(board(11)(15), player3))
    board(13)(14) = StoneField(13, 14, false, PlayerStone(board(13)(14), player4))
    board(13)(15) = StoneField(13, 15, false, PlayerStone(board(13)(15), player4))
    board(14)(14) = StoneField(14, 14, false, PlayerStone(board(14)(14), player4))
    board(15)(14) = StoneField(15, 14, false, PlayerStone(board(15)(14), player4))
    board(15)(15) = StoneField(15, 15, false, PlayerStone(board(15)(15), player4))
    board
  }

}
