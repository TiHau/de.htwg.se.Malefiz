package de.htwg.se.malefiz.aview


import java.awt.{Color, Font, Toolkit}
import de.htwg.se.malefiz.Util.Observer
import de.htwg.se.malefiz.controller.{ControllerInterface, State}
import scala.swing.event._
import scala.swing._
import de.htwg.se.malefiz.controller.State._


class GUI(controller: ControllerInterface) extends Frame with Observer {
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
        val rectX = posX / ((size.width - 50) / 17)
        val rectY = posY / ((size.height - 110) / 16)
        controller.state match {
          case SetPlayerCount =>
          case ChosePlayerStone =>

            if (controller.checkValidPlayerStone(rectX, rectY)) {
              commandNotExecuted = false
            } else {
              message = "You have to Chose one of your Stones"
            }


          case ChooseTarget =>
            controller.makeAMove(rectX, rectY)
            if (controller.moveDone) {
            commandNotExecuted = false
          } else {
            message = "You have to chose a valid Target"
          }
          case SetBlockStone =>
            controller.setBlockStone(rectX,rectY)
            if (controller.blockStoneSet) {
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
      val activePlayerColorasInt = controller.activePlayer.color
      if (activePlayerColorasInt == 1) {
        activePlayerColorString = "Red"
      } else if (activePlayerColorasInt == 2) {
        activePlayerColorString = "Green"
      } else if (activePlayerColorasInt == 3) {
        activePlayerColorString = "Yellow"
      } else {
        activePlayerColorString = "Blue"
      }

      g.setFont(new Font("TimesRoman", Font.BOLD, size.width / 60))
      g.drawString("Player: " + activePlayerColorString, 40, 40)
      g.drawString("" + message, size.width / 3, 40)
      g.drawString("Diced: " + controller.diced.toString, size.width - size.width / 6, 40)
      //Playground
      g.setColor(Color.LIGHT_GRAY)
      g.fillRect(10, 80, size.width - 20, size.height - 90)
      printingGameboard(g)
    }

    private def printingGameboard(g: Graphics2D): Unit = {
      var x: Int = 0
      var y: Int = 0
      val currentGB = controller.gameBoard.toString.replace(" ", "#").replace("###", "   ").trim
      var check = 0
      var count = 0
      for (c <- currentGB) {
        c match {
          case '|' =>
            check += 1
            if (check == 3) {
              drawOvalSmall()
              check = 0
              x += 1
            } else if (check == 1) {
              drawRect(new Color(244, 164, 96))
              drawOvalNormal(Color.BLACK)
            }
          case '\n' =>
            y += 1
            x = 0
            check = 0
          case ' ' =>
            check += 1
            if (check == 3) {
              drawRect(new Color(244, 164, 96))
              check = 0
              x += 1
            }
          case '-'
          => setStoneColorWithoutBackground(Color.WHITE)
          case 'o'
          => setStoneColorWithoutBackground(Color.BLACK)
          case '1'
          => setStoneColorWithoutBackground(Color.RED)
          case '2'
          => setStoneColorWithoutBackground(Color.GREEN)
          case '3'
          => setStoneColorWithoutBackground(Color.YELLOW)
          case '4'
          => setStoneColorWithoutBackground(Color.BLUE)
          case 'x'
          => setStoneColorWithBackgroundPainting(new Color(238, 118, 0))
          case 'P'
          => setStoneColorWithBackgroundPainting(Color.MAGENTA)
          case 'B'
          => setStoneColorWithBackgroundPainting(Color.WHITE)
          case _ =>
        }
      }

      def setStoneColorWithoutBackground(color: Color): Unit = {
        if (check == 1) {
          check += 1
          g.setColor(color)
        }
      }

      def setStoneColorWithBackgroundPainting(color: Color): Unit = {
        if (check == 1) {
          g.setColor(new Color(238, 118, 0))
          g.fillOval(20 + ((size.width - 50) / 17) * x, 100 + ((size.height - 110) / 16) * y,
            ((size.width - 50) / 17) - 2, ((size.height - 110) / 16) - 2)
          check += 1
          g.setColor(color)
        }
      }

      def drawOvalSmall(): Unit = {
        g.fillOval(20 + ((size.width - 50) / 17) * x, 100 + ((size.height - 110) / 16) * y,
          ((size.width - 50) / 17) - 6, ((size.height - 110) / 16) - 6)
      }

      def drawOvalNormal(color: Color): Unit = {
        g.setColor(color)
        g.fillOval(20 + ((size.width - 50) / 17) * x, 100 + ((size.height - 110) / 16) * y,
          ((size.width - 50) / 17) - 2, ((size.height - 110) / 16) - 2)
      }

      def drawRect(color: Color): Unit = {
        if (count < 272) {
          g.setColor(color)
          g.fillRect(20 + ((size.width - 50) / 17) * x, 100 + ((size.height - 110) / 16) * y,
            (size.width - 50) / 17, (size.height - 110) / 16)
          count += 1
        }
      }
    }
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("Empty") {})
      contents += new MenuItem(Action("New") {
        controller.reset = true
        commandNotExecuted = false
      })
      contents += new MenuItem(Action("Save") {})
      contents += new MenuItem(Action("Load") {})
      contents += new MenuItem(Action("Quit") {
        sys.exit(0)
      })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") {
        controller.undo()
        repaint()
      })
      contents += new MenuItem(Action("Redo") {})
    }


  }

  size = dim
  visible = true
  resizable = true
  title = "Malefitz"

  override def closeOperation(): Unit = sys.exit(0)

  controller.runGame()

  override def update(): Unit = {
    controller.state match {
      case State.Print => repaint()
      case State.SetBlockStone =>
        if (!controller.reset) {
          commandNotExecuted = true
        }
        message = "Set a BlockStone"
        repaint()
        mWait()

      case State.ChosePlayerStone =>
        if (!controller.reset) {
          commandNotExecuted = true
        }
        message = "Chose one of your Stones"
        mWait()

      case State.ChooseTarget =>
        if (!controller.reset) {
          commandNotExecuted = true
        }
        message = "Chose a Target Field"
        mWait()

      case State.PlayerWon =>
        ifWon()

      case State.SetPlayerCount =>
        commandNotExecuted = true
        val countUI = new CountUI
        countUI.visible = true
        mWait()

    }
  }

  private def mWait(): Unit = {
    while (commandNotExecuted) {
      Thread.sleep(400)
    }
  }

  private def ifWon(): Unit = {

    commandNotExecuted = true
    val wonUI = new WinUI
    wonUI.visible = true
    mWait()
  }

  private class CountUI extends MainFrame {
    title = "Playercount"
    preferredSize = new Dimension(320, 70)
    location = new Point(screenX / 3, screenY / 3)
    contents = new FlowPanel() {
      contents += Button("2 Player") {
        controller.setPlayerCount(2)
        commandNotExecuted = false
        dispose
      }
      contents += Button("3 Player") {
        controller.setPlayerCount(3)
        commandNotExecuted = false
        dispose
      }
      contents += Button("4 Player") {
        controller.setPlayerCount(4)
        commandNotExecuted = false
        dispose
      }
    }
  }

  private class WinUI extends MainFrame {
    var activePlayerColorString = ""
    var activePlayerColorasInt: Int = controller.activePlayer.color
    if (activePlayerColorasInt == 1) {
      activePlayerColorString = "Red"
    } else if (activePlayerColorasInt == 2) {
      activePlayerColorString = "Green"
    } else if (activePlayerColorasInt == 3) {
      activePlayerColorString = "Yellow"
    } else {
      activePlayerColorString = "Blue"
    }
    title = "Victory"
    preferredSize = new Dimension(400, 120)
    location = new Point(screenX / 3, screenY / 3)
    contents = new FlowPanel() {
      contents += new Label("Player " + activePlayerColorString + " Won the Game!")
      contents += Button("Exit") {
        sys.exit(0)
      }
      contents += Button("New Game") {
        controller.reset = true
        commandNotExecuted = false
        dispose()
      }
    }
  }

}

