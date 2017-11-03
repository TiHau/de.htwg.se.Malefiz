package de.htwg.se.malefiz.model

case class InitializeMalefiz() {
  private val nu0 = 0
  private val nu1 = 1
  private val nu2 = 2
  private val nu3 = 3
  private val nu4 = 4
  private val nu5 = 5
  private val nu6 = 6
  private val nu7 = 7
  private val nu8 = 8
  private val nu9 = 9
  private val nu10 = 10
  private val nu11 = 11
  private val nu12 = 12
  private val nu13 = 13
  private val nu14 = 14
  private val nu15 = 15
  val nu16 = 16
  def buildMalefitz(board: Array[Array[Field]]): Array[Array[Field]] ={
      setPlayerStones(setBlockStones(buildMalefitzGameBoard(board)))
  }

  private def buildMalefitzGameBoard(board: Array[Array[Field]]): Array[Array[Field]] = {
    for (i <- nu0 to nu16) {
      board(i)(nu1) = StoneField(i, nu1, FreeStone(board(i)(nu1)))
      board(i)(nu11) = StoneField(i, nu11, FreeStone(board(i)(nu11)))
      board(i)(nu13) = StoneField(i, nu13, FreeStone(board(i)(nu13)))
      board(i)(nu3) = StoneField(i, nu3, FreeStone(board(i)(nu3)))
      if (i % nu4 == nu0) {
        board(i)(nu12) = StoneField(i, nu12, FreeStone(board(i)(nu12)))
      }
      if (!(i % nu2 == nu0)) {
        board(i)(nu15) = StoneField(i, nu15, FreeStone(board(i)(nu15)))
      }
      if (i >= nu6 && i <= nu10) {
        board(i)(nu5) = StoneField(i, nu5, FreeStone(board(i)(nu5)))
      }
      if (i >= nu4 && i <= nu12) {
        board(i)(nu7) = StoneField(i, nu7, FreeStone(board(i)(nu7)))
      }
      if (i >= nu2 && i <= nu14) {
        board(i)(nu9) = StoneField(i, nu9, FreeStone(board(i)(nu9)))
      }
      if ((i >= nu1 && i <= nu3)||(i >= nu5 && i <= nu7)||(i >= nu9 && i <= nu11)||(i >= nu13 && i <= nu15)) {
        board(i)(nu14) = StoneField(i, nu14, FreeStone(board(i)(nu14)))
      }
    }
    board(nu8)(nu0) = StoneField(nu8, nu0, FreeStone(board(nu8)(nu0)))
    board(nu12)(nu8) = StoneField(nu12, nu8, FreeStone(board(nu12)(nu8)))
    board(nu0)(nu2) = StoneField(nu0, nu2, FreeStone(board(nu0)(nu2)))
    board(nu4)(nu8) = StoneField(nu4, nu8, FreeStone(board(nu4)(nu8)))
    board(nu16)(nu2) = StoneField(nu16, nu2, FreeStone(board(nu16)(nu2)))
    board(nu8)(nu4) = StoneField(nu8, nu4, FreeStone(board(nu8)(nu4)))
    board(nu6)(nu6) = StoneField(nu6, nu6, FreeStone(board(nu6)(nu6)))
    board(nu10)(nu6) = StoneField(nu10, nu6, FreeStone(board(nu10)(nu6)))
    board(nu2)(nu10) = StoneField(nu2, nu10, FreeStone(board(nu2)(nu10)))
    board(nu6)(nu10) = StoneField(nu6, nu10, FreeStone(board(nu6)(nu10)))
    board(nu10)(nu10) = StoneField(nu10, nu10, FreeStone(board(nu10)(nu10)))
    board(nu14)(nu10) = StoneField(nu14, nu10, FreeStone(board(nu14)(nu10)))
    for (y <- nu0 to nu15) {
      for (x <- nu0 to nu16) {
        if (board(x)(y) == null) {
          board(x)(y) = EmptyField(x, y)
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

  private def setBlockStones(board: Array[Array[Field]]): Array[Array[Field]] = {
    board(nu8)(nu0) = StoneField(nu8, nu0, BlockStone(board(nu8)(nu0)))
    board(nu8)(nu1) = StoneField(nu8, nu1, BlockStone(board(nu8)(nu1)))
    board(nu8)(nu3) = StoneField(nu8, nu3, BlockStone(board(nu8)(nu3)))
    board(nu8)(nu4) = StoneField(nu8, nu4, BlockStone(board(nu8)(nu4)))
    board(nu8)(nu5) = StoneField(nu8, nu5, BlockStone(board(nu8)(nu5)))
    board(nu6)(nu7) = StoneField(nu6, nu7, BlockStone(board(nu6)(nu7)))
    board(nu10)(nu7) = StoneField(nu10, nu7, BlockStone(board(nu10)(nu7)))
    board(nu0)(nu11) = StoneField(nu0, nu11, BlockStone(board(nu0)(nu11)))
    board(nu4)(nu11) = StoneField(nu4, nu11, BlockStone(board(nu4)(nu11)))
    board(nu8)(nu11) = StoneField(nu8, nu11, BlockStone(board(nu8)(nu11)))
    board(nu12)(nu11) = StoneField(nu12, nu11, BlockStone(board(nu12)(nu11)))
    board(nu16)(nu11) = StoneField(nu16, nu11, BlockStone(board(nu16)(nu11)))
    board
  }

  private def setPlayerStones(board: Array[Array[Field]]): Array[Array[Field]] = {
    val player1: Player = Player(nu1)
    val player2: Player = Player(nu2)
    val player3: Player = Player(nu3)
    val player4: Player = Player(nu4)
    board(nu1)(nu14) = StoneField(nu1, nu14, PlayerStone(board(nu1)(nu14), player1))
    board(nu1)(nu15) = StoneField(nu1, nu15, PlayerStone(board(nu1)(nu15), player1))
    board(nu2)(nu14) = StoneField(nu2, nu14, PlayerStone(board(nu2)(nu14), player1))
    board(nu3)(nu14) = StoneField(nu3, nu14, PlayerStone(board(nu3)(nu14), player1))
    board(nu3)(nu15) = StoneField(nu3, nu15, PlayerStone(board(nu3)(nu15), player1))
    board(nu5)(nu14) = StoneField(nu5, nu14, PlayerStone(board(nu5)(nu14), player2))
    board(nu5)(nu15) = StoneField(nu5, nu15, PlayerStone(board(nu5)(nu15), player2))
    board(nu6)(nu14) = StoneField(nu6, nu14, PlayerStone(board(nu6)(nu14), player2))
    board(nu7)(nu14) = StoneField(nu7, nu14, PlayerStone(board(nu7)(nu14), player2))
    board(nu7)(nu15) = StoneField(nu7, nu15, PlayerStone(board(nu7)(nu15), player2))
    board(nu9)(nu14) = StoneField(nu9, nu14, PlayerStone(board(nu9)(nu14), player3))
    board(nu9)(nu15) = StoneField(nu9, nu15, PlayerStone(board(nu9)(nu15), player3))
    board(nu10)(nu14) = StoneField(nu10, nu14, PlayerStone(board(nu10)(nu14), player3))
    board(nu11)(nu14) = StoneField(nu11, nu14, PlayerStone(board(nu11)(nu14), player3))
    board(nu11)(nu15) = StoneField(nu11, nu15, PlayerStone(board(nu11)(nu15), player3))
    board(nu13)(nu14) = StoneField(nu13, nu14, PlayerStone(board(nu13)(nu14), player4))
    board(nu13)(nu15) = StoneField(nu13, nu15, PlayerStone(board(nu13)(nu15), player4))
    board(nu14)(nu14) = StoneField(nu14, nu14,PlayerStone(board(nu14)(nu14), player4))
    board(nu15)(nu14) = StoneField(nu15, nu14, PlayerStone(board(nu15)(nu14), player4))
    board(nu15)(nu15) = StoneField(nu15, nu15, PlayerStone(board(nu15)(nu15), player4))
    board
  }

}
