package de.htwg.se.malefiz.model

case class InitializeMalefiz() {
  val k = true
  val l = false
  val nu0 = 0
  val nu1 = 1
  val nu2 = 2
  val nu3 = 3
  val nu4 = 4
  val nu5 = 5
  val nu6 = 6
  val nu7 = 7
  val nu8 = 8
  val nu9 = 9
  val nu10 = 10
  val nu11 = 11
  val nu12 = 12
  val nu13 = 13
  val nu14 = 14
  val nu15 = 15
  val nu16 = 16

  def buildMalefitzGameBoard(board: Array[Array[Field]]): Array[Array[Field]] = {
    for (i <- nu0 to nu16) {
      board(i)(nu1) = StoneField(i, nu1, k, FreeStone(board(i)(nu1)))
      board(i)(nu11) = StoneField(i, nu11, k, FreeStone(board(i)(nu11)))
      board(i)(nu13) = StoneField(i, nu13, k, FreeStone(board(i)(nu13)))
      board(i)(nu3) = StoneField(i, nu3, k, FreeStone(board(i)(nu3)))
      if (i % nu4 == nu0) {
        board(i)(nu12) = StoneField(i, nu12, k, FreeStone(board(i)(nu12)))
      }
      if (!(i % nu2 == nu0)) {
        board(i)(nu15) = StoneField(i, nu15, k, FreeStone(board(i)(nu15)))
      }
      if (i >= nu6 && i <= nu10) {
        board(i)(nu5) = StoneField(i, nu5, k, FreeStone(board(i)(nu5)))
      }
      if (i >= nu4 && i <= nu12) {
        board(i)(nu7) = StoneField(i, nu7, k, FreeStone(board(i)(nu7)))
      }
      if (i >= nu2 && i <= nu14) {
        board(i)(nu9) = StoneField(i, nu9, k, FreeStone(board(i)(nu9)))
      }
      if ((i >= nu1 && i <= nu3)||(i >= nu5 && i <= nu7)||(i >= nu9 && i <= nu11)||(i >= nu13 && i <= nu15)) {
        board(i)(nu14) = StoneField(i, nu14, k, FreeStone(board(i)(nu14)))
      }
    }
    board(nu8)(nu0) = StoneField(nu8, nu0, k, FreeStone(board(nu8)(nu0)))
    board(nu12)(nu8) = StoneField(nu12, nu8, k, FreeStone(board(nu12)(nu8)))
    board(nu0)(nu2) = StoneField(nu0, nu2, k, FreeStone(board(nu0)(nu2)))
    board(nu4)(nu8) = StoneField(nu4, nu8, k, FreeStone(board(nu4)(nu8)))
    board(nu16)(nu2) = StoneField(nu16, nu2, k, FreeStone(board(nu16)(nu2)))
    board(nu8)(nu4) = StoneField(nu8, nu4, k, FreeStone(board(nu8)(nu4)))
    board(nu6)(nu6) = StoneField(nu6, nu6, k, FreeStone(board(nu6)(nu6)))
    board(nu10)(nu6) = StoneField(nu10, nu6, k, FreeStone(board(nu10)(nu6)))
    board(nu2)(nu10) = StoneField(nu2, nu10, k, FreeStone(board(nu2)(nu10)))
    board(nu6)(nu10) = StoneField(nu6, nu10, k, FreeStone(board(nu6)(nu10)))
    board(nu10)(nu10) = StoneField(nu10, nu10, k, FreeStone(board(nu10)(nu10)))
    board(nu14)(nu10) = StoneField(nu14, nu10, k, FreeStone(board(nu14)(nu10)))
    for (y <- nu0 to nu15) {
      for (x <- nu0 to nu16) {
        if (board(x)(y) == null) {
          board(x)(y) = EmptyField(x, y, k)
        }
      }
    }
    board
  }

  def pri(board: Array[Array[Field]]): Unit = {
    for (y <- nu0 to nu15) {
      for (i <- nu0 to nu16) {
        if (board(i)(y).getFieldType() == 'e') {
          print("    ")
        } else if (board(i)(y).getFieldType() == 's') {
            val s: StoneField = board(i)(y).asInstanceOf[StoneField]
            if(s.stone.getStoneType()=='f'){
              print("|0 |")
            }
            if(s.stone.getStoneType()=='p'){
              val stone:PlayerStone = s.stone.asInstanceOf[PlayerStone]

                print("|P" + stone.player.color + "|")

            }
            if(s.stone.getStoneType()=='b'){
              print("|b |")
            }
        }

        }
      println()
    }
  }

  def setBlockStones(board: Array[Array[Field]]): Array[Array[Field]] = {
    board(nu8)(nu0) = StoneField(nu8, nu0, l, BlockStone(board(nu8)(nu0)))
    board(nu8)(nu1) = StoneField(nu8, nu1, l, BlockStone(board(nu8)(nu1)))
    board(nu8)(nu3) = StoneField(nu8, nu3, l, BlockStone(board(nu8)(nu3)))
    board(nu8)(nu4) = StoneField(nu8, nu4, l, BlockStone(board(nu8)(nu4)))
    board(nu8)(nu5) = StoneField(nu8, nu5, l, BlockStone(board(nu8)(nu5)))
    board(nu6)(nu7) = StoneField(nu6, nu7, l, BlockStone(board(nu6)(nu7)))
    board(nu10)(nu7) = StoneField(nu10, nu7, l, BlockStone(board(nu10)(nu7)))
    board(nu0)(nu11) = StoneField(nu0, nu11, l, BlockStone(board(nu0)(nu11)))
    board(nu4)(nu11) = StoneField(nu4, nu11, l, BlockStone(board(nu4)(nu11)))
    board(nu8)(nu11) = StoneField(nu8, nu11, l, BlockStone(board(nu8)(nu11)))
    board(nu12)(nu11) = StoneField(nu12, nu11, l, BlockStone(board(nu12)(nu11)))
    board(nu16)(nu11) = StoneField(nu16, nu11, l, BlockStone(board(nu16)(nu11)))
    board
  }

  def setPlayerStones(board: Array[Array[Field]]): Array[Array[Field]] = {
    val player1: Player = Player(nu1)
    val player2: Player = Player(nu2)
    val player3: Player = Player(nu3)
    val player4: Player = Player(nu4)
    board(nu1)(nu14) = StoneField(nu1, nu14, l, PlayerStone(board(nu1)(nu14), player1))
    board(nu1)(nu15) = StoneField(nu1, nu15, l, PlayerStone(board(nu1)(nu15), player1))
    board(nu2)(nu14) = StoneField(nu2, nu14, l, PlayerStone(board(nu2)(nu14), player1))
    board(nu3)(nu14) = StoneField(nu3, nu14, l, PlayerStone(board(nu3)(nu14), player1))
    board(nu3)(nu15) = StoneField(nu3, nu15, l, PlayerStone(board(nu3)(nu15), player1))
    board(nu5)(nu14) = StoneField(nu5, nu14, l, PlayerStone(board(nu5)(nu14), player2))
    board(nu5)(nu15) = StoneField(nu5, nu15, l, PlayerStone(board(nu5)(nu15), player2))
    board(nu6)(nu14) = StoneField(nu6, nu14, l, PlayerStone(board(nu6)(nu14), player2))
    board(nu7)(nu14) = StoneField(nu7, nu14, l, PlayerStone(board(nu7)(nu14), player2))
    board(nu7)(nu15) = StoneField(nu7, nu15, l, PlayerStone(board(nu7)(nu15), player2))
    board(nu9)(nu14) = StoneField(nu9, nu14, l, PlayerStone(board(nu9)(nu14), player3))
    board(nu9)(nu15) = StoneField(nu9, nu15, l, PlayerStone(board(nu9)(nu15), player3))
    board(nu10)(nu14) = StoneField(nu10, nu14, l, PlayerStone(board(nu10)(nu14), player3))
    board(nu11)(nu14) = StoneField(nu11, nu14, l, PlayerStone(board(nu11)(nu14), player3))
    board(nu11)(nu15) = StoneField(nu11, nu15, l, PlayerStone(board(nu11)(nu15), player3))
    board(nu13)(nu14) = StoneField(nu13, nu14, l, PlayerStone(board(nu13)(nu14), player4))
    board(nu13)(nu15) = StoneField(nu13, nu15, l, PlayerStone(board(nu13)(nu15), player4))
    board(nu14)(nu14) = StoneField(nu14, nu14, l, PlayerStone(board(nu14)(nu14), player4))
    board(nu15)(nu14) = StoneField(nu15, nu14, l, PlayerStone(board(nu15)(nu14), player4))
    board(nu15)(nu15) = StoneField(nu15, nu15, l, PlayerStone(board(nu15)(nu15), player4))
    board
  }

}
