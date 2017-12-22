package de.htwg.se.malefiz.aview

import scala.swing.Frame


case class GUI() extends Frame {

  



  val sleepTime = 50
  val myThread = new Thread{
    override def run {
      repaint
      this.wait(sleepTime)
    }
  }
  myThread.start
}
