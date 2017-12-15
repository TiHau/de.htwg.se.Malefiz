package de.htwg.se.malefiz.model

case class GameBoard(howManyPlayer: Int) {
  private val x = 17
  private val y = 16
  val board = Array.ofDim[AbstractField](x, y)
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
  private val nu16 = 16
  if (howManyPlayer > 4) {
    setPlayerStones(setBlockStones(buildMalefitzGameBoard(board)), nu4)
  } else if (howManyPlayer < 2) {
    setPlayerStones(setBlockStones(buildMalefitzGameBoard(board)), nu2)
  } else {
    setPlayerStones(setBlockStones(buildMalefitzGameBoard(board)), howManyPlayer)
  }

  private def buildMalefitzGameBoard(board: Array[Array[AbstractField]]): Array[Array[AbstractField]] = {
    val empty = EmptySpace()
    for (y <- nu0 to nu15) {
      for (x <- nu0 to nu16) {
        board(x)(y) = empty
      }
    }
    for (y <- nu0 to nu15) {

      y match {
        case 0 => board(nu8)(y) = Field(nu8, y, FreeStone())
        case 1 => for (i <- nu0 to nu16) {
          board(i)(y) = Field(i, y, FreeStone())
        }
        case 2 => {
          board(nu0)(y) = Field(nu0, y, FreeStone())
          board(nu16)(y) = Field(nu16, y, FreeStone())
        }
        case 3 => for (i <- nu0 to nu16) {
          board(i)(y) = Field(i, y, FreeStone())
        }
        case 4 => board(nu8)(y) = Field(nu8, y, FreeStone())
        case 5 => for (i <- nu0 to nu16) {
          if (i >= nu6 && i <= nu10) {
            board(i)(y) = Field(i, y, FreeStone())
          }
        }
        case 6 =>
          board(nu6)(y) = Field(nu6, y, FreeStone())
          board(nu10)(y) = Field(nu10, y, FreeStone())


        case 7 => for (i <- nu0 to nu16) {
          if (i >= nu4 && i <= nu12) {
            board(i)(y) = Field(i, y, FreeStone())
          }
        }
        case 8 =>
          board(nu12)(y) = Field(nu12, y, FreeStone())
          board(nu4)(y) = Field(nu4, y, FreeStone())


        case 9 => for (i <- nu0 to nu16) {
          if (i >= nu2 && i <= nu14) {
            board(i)(y) = Field(i, y, FreeStone())
          }
        }
        case 10 =>
          board(nu2)(y) = Field(nu2, y, FreeStone())
          board(nu6)(y) = Field(nu6, y, FreeStone())
          board(nu10)(y) = Field(nu10, y, FreeStone())
          board(nu14)(y) = Field(nu14, y, FreeStone())

        case 11 => for (i <- nu0 to nu16) {
          board(i)(y) = Field(i, y, FreeStone())
        }
        case 12 => for (i <- nu0 to nu16) {
          if (i % nu4 == nu0) {
            board(i)(y) = Field(i, y, FreeStone())
          }
        }
        case 13 => for (i <- nu0 to nu16) {
          board(i)(y) = Field(i, y, FreeStone())
        }
        case 14 => for (i <- nu0 to nu16) {
          if ((i >= nu1 && i <= nu3) || (i >= nu5 && i <= nu7) || (i >= nu9 && i <= nu11) || (i >= nu13 && i <= nu15)) {
            board(i)(y) = Field(i, y, FreeStone())
          }
        }
        case 15 => for (i <- nu0 to nu16) {
          if (!(i % nu2 == nu0)) {
            board(i)(y) = Field(i, y, FreeStone())
          }
        }

      }
    }
    board
  }

  override def toString(): String = {
    import scala.collection.mutable.StringBuilder
    val jsb = new StringBuilder()
    for (y <- nu0 to nu15) {
      if(y<10){
        jsb.append(y + "  ")
      } else{
        jsb.append(y + " ")
      }

      for (i <- nu0 to nu16) {
        if (board(i)(y).isFreeSpace()) {
          jsb.append("   ")
        } else {
          val s: Field = board(i)(y).asInstanceOf[Field]
          s.stone.sort match {
            case 'f' => if(s.avariable==false){jsb.append("|o|")}else{jsb.append("|x|")}
            case 'p' => jsb.append("|" + s.stone.asInstanceOf[PlayerStone].player.color + "|")
            case 'b' => jsb.append("|-|")
            case _ => jsb.append("|x|")
          }
        }
      }
      jsb.append("\n")
    }
    jsb.append("    ")
    (0 to 9).addString(jsb, "  ")
    jsb.append(" ")
    (10 to 16).addString(jsb, " ")
    jsb.toString()
  }

  private def setBlockStones(board: Array[Array[AbstractField]]): Array[Array[AbstractField]] = {
    board(nu8)(nu0) = Field(nu8, nu0, BlockStone())
    board(nu8)(nu1) = Field(nu8, nu1, BlockStone())
    board(nu8)(nu3) = Field(nu8, nu3, BlockStone())
    board(nu8)(nu4) = Field(nu8, nu4, BlockStone())
    board(nu8)(nu5) = Field(nu8, nu5, BlockStone())
    board(nu6)(nu7) = Field(nu6, nu7, BlockStone())
    board(nu10)(nu7) = Field(nu10, nu7, BlockStone())
    board(nu0)(nu11) = Field(nu0, nu11, BlockStone())
    board(nu4)(nu11) = Field(nu4, nu11, BlockStone())
    board(nu8)(nu11) = Field(nu8, nu11, BlockStone())
    board(nu12)(nu11) = Field(nu12, nu11, BlockStone())
    board(nu16)(nu11) = Field(nu16, nu11, BlockStone())
    board
  }

  private def setPlayerStones(board: Array[Array[AbstractField]], howMany: Int): Array[Array[AbstractField]] = {
    val player1: Player = Player(nu1)
    val player2: Player = Player(nu2)
    val player3: Player = Player(nu3)
    val player4: Player = Player(nu4)

    board(nu1)(nu14) = Field(nu1, nu14, PlayerStone(board(nu1)(nu14), player1))
    board(nu1)(nu15) = Field(nu1, nu15, PlayerStone(board(nu1)(nu15), player1))
    board(nu2)(nu14) = Field(nu2, nu14, PlayerStone(board(nu2)(nu14), player1))
    board(nu3)(nu14) = Field(nu3, nu14, PlayerStone(board(nu3)(nu14), player1))
    board(nu3)(nu15) = Field(nu3, nu15, PlayerStone(board(nu3)(nu15), player1))
    if (howMany >= 3) {
      board(nu5)(nu14) = Field(nu5, nu14, PlayerStone(board(nu5)(nu14), player2))
      board(nu5)(nu15) = Field(nu5, nu15, PlayerStone(board(nu5)(nu15), player2))
      board(nu6)(nu14) = Field(nu6, nu14, PlayerStone(board(nu6)(nu14), player2))
      board(nu7)(nu14) = Field(nu7, nu14, PlayerStone(board(nu7)(nu14), player2))
      board(nu7)(nu15) = Field(nu7, nu15, PlayerStone(board(nu7)(nu15), player2))
      if (howMany == 4) {
        board(nu9)(nu14) = Field(nu9, nu14, PlayerStone(board(nu9)(nu14), player3))
        board(nu9)(nu15) = Field(nu9, nu15, PlayerStone(board(nu9)(nu15), player3))
        board(nu10)(nu14) = Field(nu10, nu14, PlayerStone(board(nu10)(nu14), player3))
        board(nu11)(nu14) = Field(nu11, nu14, PlayerStone(board(nu11)(nu14), player3))
        board(nu11)(nu15) = Field(nu11, nu15, PlayerStone(board(nu11)(nu15), player3))
      }
    }
    board(nu13)(nu14) = Field(nu13, nu14, PlayerStone(board(nu13)(nu14), player4))
    board(nu13)(nu15) = Field(nu13, nu15, PlayerStone(board(nu13)(nu15), player4))
    board(nu14)(nu14) = Field(nu14, nu14, PlayerStone(board(nu14)(nu14), player4))
    board(nu15)(nu14) = Field(nu15, nu14, PlayerStone(board(nu15)(nu14), player4))
    board(nu15)(nu15) = Field(nu15, nu15, PlayerStone(board(nu15)(nu15), player4))
    board
  }


  def changeTwoStones(f1: Field, f2: Field): Option[Stone] = {
    if (f1.stone.sort == 'p') {
      val save = f2.stone
      if (f2.stone.sort=='p') {
        resetPlayerStone(f2)
      }
      f2.stone=f1.stone
      f2.stone.asInstanceOf[PlayerStone].field.asInstanceOf[Field].x= f2.x
      f2.stone.asInstanceOf[PlayerStone].field.asInstanceOf[Field].y= f2.y
      f1.stone = FreeStone()
      Some(save)

    } else{
      None
    }
  }

  private def resetPlayerStone(field: Field): Unit = {
    val player = field.stone.asInstanceOf[PlayerStone]
    val playerField = player.field.asInstanceOf[Field]
    board(playerField.x)(playerField.y).asInstanceOf[Field].stone = field.stone
    field.stone = FreeStone()

  }
}
