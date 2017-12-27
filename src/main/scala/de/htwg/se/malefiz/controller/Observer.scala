package de.htwg.se.malefiz.controller

trait Observer {
  def update
  def setBlockstone
  def choseAPlayerstone
  def setPlayerCountNew
  def askTarget
  def sayWon
}

class Observable {
  var subscribers: Vector[Observer] = Vector()
  def add(s: Observer) = subscribers = subscribers :+ s
  def remove(s: Observer) = subscribers = subscribers.filterNot(o => o == s)
  def notifyObserversUpdate = subscribers.foreach(o => o.update)
  def notifyObserversSetBlock = subscribers.foreach(o => o.setBlockstone)
  def notifyObserversChosePlayer = subscribers.foreach(o => o.choseAPlayerstone)
  def notifyObserversSetPlayerCount = subscribers.foreach(o => o.setPlayerCountNew)
  def notifyObserversChoseTarget = subscribers.foreach(o=>o.askTarget)
  def notifyObserversSayWon = subscribers.foreach(o=>o.sayWon)
}
