package de.htwg.se.malefiz

import de.htwg.se.malefiz.model.{InitializeMalefiz, GameBoard}

object Malefiz {
  def main(args: Array[String]): Unit = {
    InitializeMalefiz.apply().pri(InitializeMalefiz.apply()
      .setPlayerStones(InitializeMalefiz.apply()
        .setBlockStones(InitializeMalefiz.apply()
         .buildMalefitzGameBoard(GameBoard.board))))

  }
}
