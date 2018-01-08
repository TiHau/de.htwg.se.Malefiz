package de.htwg.se.malefiz.aview


import java.awt.{Color, Font, Toolkit}

import de.htwg.se.malefiz.controller.{Controller, Observer, State}

import scala.swing.event._
import scala.swing._
import de.htwg.se.malefiz.controller.State._


class GUI(controller: Controller) extends Frame with Observer {
  private val dim = Toolkit.getDefaultToolkit.getScreenSize
  private val screenX = dim.width
  private val screenY = dim.height
  private var message = "test"
  private var commandNotExecuted = true
  controller.add(this)
  contents = new FlowPanel() {
    focusable = true
    listenTo(this.mouse.clicks)
    reactions += {
      case MouseClicked(_, point, _, _, _) =>
        val posX = point.x - 20
        val posY = point.y - 100
        val rectX = (posX / ((size.width-50)/17))
        val rectY = (posY / ((size.height-110)/16))
        controller.state match {
          case SetPlayerCount =>
          case ChosePlayerStone =>

            if (controller.checkValidPlayerStone(rectX, rectY)) {
              commandNotExecuted = false
            } else {
              message = "You have to Chose one of your Stones"
            }


          case SetTarget => if (controller.makeAmove(rectX, rectY)) {
            commandNotExecuted = false
          } else {
            message = "You have to chose a valid Target"
          }
          case SetBlockStone => if (controller.isChosenBlockStone(rectX, rectY)) {
            commandNotExecuted = false
          } else {
            message = "You have to chose a valid Field to set BlockStone"
          }

          case Print =>
          case _ =>
        }


    }


    override def paint(g: Graphics2D): Unit = {
      //Background
      background = Color.WHITE
      var activePlayerColorString = ""
      var activePlayerColorasInt = controller.activePlayer.color
      if (activePlayerColorasInt == 1) {
        activePlayerColorString = "Red"
      } else if (activePlayerColorasInt == 2) {
        activePlayerColorString = "Green"
      } else if (activePlayerColorasInt == 3) {
        activePlayerColorString = "Yellow"
      } else {
        activePlayerColorString = "Blue"
      }

      g.setFont(new Font("TimesRoman", Font.BOLD, size.width/60))
      g.drawString("Player: " + activePlayerColorString, 40, 40)
      g.drawString("" + message, size.width / 3, 40)
      g.drawString("Diced: " + controller.diced.toString, size.width - 120, 40)
      //Playground
      g.setColor(Color.LIGHT_GRAY)
      g.fillRect(10, 80, size.width - 20, size.height - 90)
      //Print Gameboard
      var x: Int = 0
      var y: Int = 0


      var currentGB = controller.gameBoard.toString().replace(" ", "#").replace("###", "   ").trim

      var check = 0
      var count = 0
      for (c <- currentGB) {
        c match {
          case '|' =>
            check += 1
            if (check == 3) {
              g.fillOval(20+((size.width-50)/17)*x,100+((size.height-110)/16)*y,((size.width-50)/17)-6,((size.height-110)/16)-6)
              check = 0
              x += 1
            } else if (check == 1) {
              if (count < 272) {
                g.setColor(new Color(244, 164, 96))
                g.fillRect(20+((size.width-50)/17)*x,100+((size.height-110)/16)*y,(size.width-50)/17,(size.height-110)/16)
                count += 1
              }
              g.setColor(Color.BLACK)
              g.fillOval(20+((size.width-50)/17)*x,100+((size.height-110)/16)*y,((size.width-50)/17)-2,((size.height-110)/16)-2)
            }


          case '\n' =>
            y += 1
            x = 0
            check = 0

          case ' ' =>
            check += 1
            if (check == 3) {
              if (count < 272) {
                g.setColor(new Color(244, 164, 96))
                g.fillRect(20+((size.width-50)/17)*x,100+((size.height-110)/16)*y,(size.width-50)/17,(size.height-110)/16)
                count += 1
              }
              check = 0
              x += 1
            }

          case '-'
          =>
            if (check == 1) {
              check += 1
              g.setColor(Color.WHITE)
            } else {
              check = 2
              g.setColor(Color.BLACK)
            }

          case 'o'
          =>
            if (check == 1) {
              check += 1
              g.setColor(Color.BLACK)
            }

          case '1'
          =>
            if (check == 1) {
              check += 1
              g.setColor(Color.RED)
            }

          case '2'
          =>
            if (check == 1) {
              check += 1
              g.setColor(Color.GREEN)
            }

          case '3'
          =>
            if (check == 1) {
              check += 1
              g.setColor(Color.YELLOW)
            }

          case '4'
          =>
            if (check == 1) {
              check += 1
              g.setColor(Color.BLUE)
            }

          case 'x'
          =>
            if (check == 1) {
              g.setColor(new Color(238, 118, 0))
              g.fillOval(20+((size.width-50)/17)*x,100+((size.height-110)/16)*y,((size.width-50)/17)-2,((size.height-110)/16)-2)
              check += 1
              g.setColor(new Color(238, 118, 0))
            }

          case 'P'
          =>
            if (check == 1) {
              g.setColor(Color.ORANGE)
              g.fillOval(20+((size.width-50)/17)*x,100+((size.height-110)/16)*y,((size.width-50)/17)-2,((size.height-110)/16)-2)
              check += 1
              g.setColor(Color.MAGENTA)
            }

          case 'B'
          =>
            if (check == 1) {
              g.setColor(Color.ORANGE)
              g.fillOval(20+((size.width-50)/17)*x,100+((size.height-110)/16)*y,((size.width-50)/17)-2,((size.height-110)/16)-2)
              check += 1
              g.setColor(Color.WHITE)
            }

          case _ =>
        }
      }
    }
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("Empty") {})
      contents += new MenuItem(Action("New") {})
      contents += new MenuItem(Action("Save") {})
      contents += new MenuItem(Action("Load") {})
      contents += new MenuItem(Action("Quit") {
        sys.exit(0)
      })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") {})
      contents += new MenuItem(Action("Redo") {})
    }


  }

  size=dim
  visible = true
  resizable = true
  title = "Malefitz"

  override def closeOperation(): Unit = sys.exit(0)

  override def update: Unit = {
    controller.state match {
      case State.Print => repaint()
      case State.SetBlockStone => {
        commandNotExecuted = true
        message = "Set a BlockStone"
        repaint()
        mwait
      }
      case State.ChosePlayerStone => {
        commandNotExecuted = true
        message = "Chose one of your Stones"
        mwait
      }
      case State.SetTarget => {
        commandNotExecuted = true
        message = "Chose a Target Field"
        mwait
      }
      case State.PlayerWon => {
        ifWon
      }
      case State.SetPlayerCount => {
        commandNotExecuted = true
        val countUI = new CountUI
        countUI.visible=true
        this.visible=false
        mwait
        this.visible=true
        this.repaint
      }
    }
  }

  private def mwait: Unit = {
    while (commandNotExecuted) {
      for (_ <- 0 to 100000) {} //short spin routine
    }
  }

  private def ifWon: Unit = {
    var activePlayerColorString = ""
    var activePlayerColorasInt = controller.activePlayer.color
    if (activePlayerColorasInt == 1) {
      activePlayerColorString = "Red"
    } else if (activePlayerColorasInt == 2) {
      activePlayerColorString = "Green"
    } else if (activePlayerColorasInt == 3) {
      activePlayerColorString = "Yellow"
    } else {
      activePlayerColorString = "Blue"
    }
    message = "Player "+activePlayerColorString+" Won the Game!"
    controller.unmarkPossibleMoves()
    repaint
    this.ignoreRepaint
  }
  private class CountUI extends MainFrame {
    title = "Playercount"
    preferredSize = new Dimension(320, 70)
    location=(new Point(screenX/3,screenY/3))
    contents = new FlowPanel() {
      contents += Button("2 Player") {
        controller.setPlayerCount(2)
        commandNotExecuted=false
        dispose
      }
      contents += Button("3 Player") {
        controller.setPlayerCount(3)
        commandNotExecuted=false
        dispose
      }
      contents += Button("4 Player") {
        controller.setPlayerCount(4)
        commandNotExecuted=false
        dispose
      }
    }
  }

}

