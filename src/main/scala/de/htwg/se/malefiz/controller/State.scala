package de.htwg.se.malefiz.controller

object State extends Enumeration {
  type currentState = Value
  val Print, SetPlayerCount, ChosePlayerStone, SetTarget, SetBlockStone= Value
}
