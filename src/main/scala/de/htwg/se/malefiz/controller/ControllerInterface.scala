package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.util.Observable
import de.htwg.se.malefiz.controller.State.Print
import de.htwg.se.malefiz.model.gameboard._

import scala.swing.Publisher

trait ControllerInterface extends Observable with Publisher {

  private val six = 6

  /**
    * Der aktuelle Spielzustand
    */
  var state: State.Value = Print
  /**
    * Boolen wert der festlegt ob ein Blockstein gesetzt werden muss
    */
  var needToSetBlockStone = false
  /**
    * Der Spieler der aktuell am Zug ist
    */
  var activePlayer: Player = Player(six)
  /**
    * Die gewürfelte Zahl
    */
  var diced: Int = six

  /**
   * läd den Gespeicherten Spielstand
   */
  def loadSavedGame(): Unit

  /**
   * Speichert den aktuellen Spielstand ab
   */
  def saveGame(): Unit

  /**
   * Macht den letzten Click rückgängig
   * Setzt den Spielstatus auf den Wert, der vor dem letzten Zug aktiv war
   */
  def undo(): Unit

  /**
   * Wenn ein Click rückgängig gemacht wurde wird diese wiederholt.
   * Aktualisiert den Spielstatus
   */
  def redo(): Unit

  /**
    * Erstellt ein neues Spiel
    * @param countPlayer Spieleranzahl
    */
  def newGame(countPlayer: Int): Unit

  /**
    * Setzt die Spieleranzahl
    * @param playerCount Spieleranzahl
    */
  def setPlayerCount(playerCount: Int): Unit

  /**
    * Liefert das GameBoard
    * @return GameBoard
    */
  def gameBoard: GameBoardInterface

  /**
   * beendet den Zug und startet den nächsten
   */
  def endTurn(): Unit

  /**
    * Bekommt Koordinaten eines Felds und führt in Abhängigkeit des aktuellen Spielstatuses
    * die jeweilige Operationen aus
    * @param x X-Koordinate
    * @param y Y-Koordinate
    */
  def takeInput(x: Int, y: Int): Unit

  /**
    * Setzt Spiel zurück
    */
  def reset(): Unit

  /**
   * Setzt das Zielfeld auf die Übergebenen Koordinaten, übeprüft ab es ein gültiges Zielfeld ist
   * @param x X-Koordinate
   * @param y y-Koordinate
   * @return true, wenn das Zielfeld gültig ist
   */
  def setTargetForPlayerStone(x: Int, y: Int): Boolean

  /**
   * Setzt den ausgwählten Spielerstein
   * @param newStone neuer Spielerstein
   */
  def setChoosenPlayerStone(newStone: PlayerStone): Unit

  /**
   * Liefert den ausgewählten Spielerstein
   * @return ausgewählter Spielerstein
   */
  def getChoosenPlayerStone: PlayerStone

  /**
   * Setzt das Zielfeld
   * @param newField neues Zielfeld
   */
  def setDestField(newField: Field): Unit

  /**
   * Liefert das Zielfeld
   * @return
   */
  def getDestField: Field
}
