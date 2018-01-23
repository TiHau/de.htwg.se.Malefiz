package de.htwg.se.malefiz.model.fileio

import de.htwg.se.malefiz.controller.{ ControllerInterface }

trait FileIOInterface {
  def load(controller: ControllerInterface): Unit
  def save(controller: ControllerInterface): Unit
}
