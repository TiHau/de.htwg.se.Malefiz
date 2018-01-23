package de.htwg.se.malefiz.model.gameboard

import scala.swing.Publisher

trait GameBoardInterface extends Publisher {
  private val one = 1
  private val two = 2
  private val three = 3
  private val four = 4

  /**
    * Überprüft ob an den übergebenen Koordinaten ein Blockstein gesetzt werden darf
    * @param x X-Koordinate des zu überprüfenden Feldes
    * @param y y-Koordinate des zu überprüfenden Feldes
    * @return Liefert true, wenn ein Blockstein auf dieses Feld gesetzt werden darf
    */
  def checkDestForBlockStone(x: Int, y: Int): Boolean

  /**
    * Setzt einen Blockstein auf ein übergebenes Feld. Das Feld sollte vorher mit
    * <code>checkDestForBlockStone(x: Int, y: Int): Boolean</code> überprüft werden.
    * @param field
    */
  def setBlockStoneOnField(field: Field): Unit

  /**
    * Setzt den Stein des Felds auf einen FreeStone
    * @param field
    */
  def removeStoneOnField(field: Field): Unit

  /**
    * Setzt den übergebenen Spielerstein wieder auf sein Startfeld zurück
    * @param stone
    */
  def resetPlayerStone(stone: PlayerStone): Unit

  /**
    * Überprüft ob an den übergebenen Koordinaten ein Spielerstein gesetzt werden darf
    * @param x X-Koordinate des zu überprüfenden Feldes
    * @param y y-Koordinate des zu überprüfenden Feldes
    * @return Liefert true, wenn ein Blockstein auf dieses Feld gesetzt werden darf
    */
  def checkDestForPlayerStone(x: Int, y: Int): Boolean

  /**
    * Versetzt den Stein von f1 nach f2. Überprüft ob das Zielfeld im Spielbereich liegt und ob avaribale= true ist
    * Der Stein auf f1 wird auf FreeStone gesetzt.
    * @param current
    * @param dest Zielfeld
    * @return Den geschlagenen Stein oder None
    */
  def moveStone(current: Field, dest: Field): Option[Stone]

  /**
    * Versetzt einene Stein.Ohne das Zielfeld zu überprüfen.
    * Der Stein auf f1 wird auf FreeStone gesetzt.
    * @param current
    * @param dest
    */
  def forceMoveStone(current: Field, dest: Field): Unit

  /**
    * Setzt avariable auf anwählbare Felder einens Steins in abhängigkeit von der gewürfelten Zahl
    * @param stone Der Stein
    * @param player Der besitzer des Steins(aktiver Spieler)
    * @param diced Die Distanz
    */
  def markPossibleMoves(stone: PlayerStone, player: Player, diced: Int): Unit

  /**
    * Setzt avariable von allen Feldern auf false
    */
  def unmarkPossibleMoves(): Unit

  /**
    * Überpüft ob ein Stein auf dem Zielfeld steht
    * @return true, wenn ein Spielerstein auf dem Zielfed steht
    */
  def checkWin: Boolean
  val player1 = Player(one)
  val player2 = Player(two)
  val player3 = Player(three)
  val player4 = Player(four)
  def playerCount: Int
  private val x = 17
  private val y = 16
  val board: Array[Array[AbstractField]] = Array.ofDim[AbstractField](x, y)

}
