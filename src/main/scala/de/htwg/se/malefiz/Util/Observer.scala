package de.htwg.se.malefiz.Util

trait Observer {
  def update()
}

class Observable {
  var subscribers: Vector[Observer] = Vector()
  def add(s: Observer): Unit = subscribers = subscribers :+ s
  def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)
  def notifyObservers(): Unit = subscribers.foreach(o => o.update)
}
