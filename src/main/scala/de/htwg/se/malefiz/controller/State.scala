package de.htwg.se.malefiz.controller

object State extends Enumeration {
  type currentState = Value
  val Print, SetPlayerCount, ChoosePlayerStone, ChooseTarget, SetBlockStone, PlayerWon, EndTurn= Value
}
