package de.htwg.se.malefiz.controller

import de.htwg.se.malefiz.util.Observable
import de.htwg.se.malefiz.controller.State.Print
import de.htwg.se.malefiz.model.gameboard._

import scala.swing.Publisher

trait ControllerInterface extends Observable with Publisher {

  private val six = 6

  var state: State.Value = Print
  var needToSetBlockStone = false
  var activePlayer: Player = new Player(six)
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
  def newGame(countPlayer: Int): Unit
  def setPlayerCount(playerCount: Int): Unit
  def gameBoard: GameBoardInterface

  /**
    * beendet den Zug und startet den nächsten
    */
  def endTurn(): Unit
  def takeInput(x: Int, y: Int): Unit
  def reset(): Unit

  /**
    * Setzt das Zielfeld auf die Übergebenen Koordinaten, übeprüft ab es ein gültiges Zielfeld ist
    * @param x
    * @param y
    * @return true, wenn das Zielfeld gültig ist
    */
  def setTargetForPlayerStone(x: Int, y: Int): Boolean

  /**
    * Setzt den ausgwählten Spielerstein
    * @param newStone
    */
  def setChoosenPlayerStone(newStone: PlayerStone): Unit

  /**
    * Liefert den ausgewählten Spielerstein
    * @return
    */
  def getChoosenPlayerStone(): PlayerStone

  /**
    * Setzt das Zielfeld
    * @param newField
    */
  def setDestField(newField: Field): Unit

  /**
    * Liefert das Zielfeld
    * @return
    */
  def getDestField(): Field
}
