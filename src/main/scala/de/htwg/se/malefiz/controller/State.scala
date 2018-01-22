package de.htwg.se.malefiz.controller

object State extends Enumeration {
  type currentState = Value
  val Print, SetPlayerCount, ChoosePlayerStone, ChooseTarget, SetBlockStone, PlayerWon, BeforeEndOfTurn, EndTurn = Value

  def fromString(s: String): Option[currentState] = s.trim match {
    case "Print" =>
      Some(Print)
    case "SetPlayerCount" =>
      Some(SetPlayerCount)
    case "ChoosePlayerStone" =>
      Some(ChoosePlayerStone)
    case "ChooseTarget" =>
      Some(ChooseTarget)
    case "SetBlockStone" =>
      Some(SetBlockStone)
    case "PlayerWon" =>
      Some(PlayerWon)
    case "BeforeEndOfTurn" =>
      Some(BeforeEndOfTurn)
    case "EndTurn" =>
      Some(EndTurn)
    case _ =>
      None
  }
}
