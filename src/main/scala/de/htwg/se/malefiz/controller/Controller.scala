package de.htwg.se.malefiz.controller
import de.htwg.se.malefiz.model._
import de.htwg.se.malefiz.controller.State._
case class Controller(var gameBoard: GameBoard) extends Observable {
  private val six = 6
  var activePlayer = gameBoard.player3
  var diced = six
  private var chosenPlayerStone = gameBoard.player1.stones(0)
  var state = Print
  var currentReturnStone = 'f'
  var reset = true
  def setPlayerCount(countPlayer: Int): Unit = {
    gameBoard = GameBoard(countPlayer)
  }

  def runGame: Unit = {
    while(reset){
      activePlayer = gameBoard.player3
      currentReturnStone = 'f'
      executePreOrders()
      executeGameRoutine()
    }

  }

  private def executeGameRoutine():Unit ={
    reset =false
    while(!checkWin) {
      changePlayer
      dice
      state=Print
      notifyObservers//print maked GameBoard
      if(!takeUserChange()) {
        return
      }
    }
    state=PlayerWon
    notifyObservers
  }

  private def takeUserChange(): Boolean ={

    state=ChosePlayerStone
    notifyObservers
    markPossibleMovesOfStone(chosenPlayerStone)
    state=Print
    notifyObservers
    state=SetTarget
    notifyObservers
    unmarkPossibleMoves
    if(currentReturnStone=='b'){
      state=SetBlockStone
      notifyObservers
    }
    if(reset){
      false
    }else{
      true
    }
  }
 private def executePreOrders(): Unit ={
   state=SetPlayerCount
   notifyObservers
 }

  private def dice(): Unit = {
    diced = scala.util.Random.nextInt(six) + 1
  }

  private def changePlayer: Unit ={
    if(activePlayer.color==1){
      activePlayer = gameBoard.player4
    } else if(activePlayer.color==4&&gameBoard.playerCount>=3){
      activePlayer = gameBoard.player2
    } else if(activePlayer.color==2&&gameBoard.playerCount==4){
      activePlayer = gameBoard.player3
    }else if(activePlayer.color==3){
      activePlayer = gameBoard.player1
    } else{
      activePlayer = gameBoard.player1
    }
  }

  private def markPossibleMovesOfStone(stone: PlayerStone): Unit = {
    if (stone.actualField == stone.startField) {
        val x = activePlayer.stones(0).startField.asInstanceOf[Field].x
        val y = activePlayer.stones(0).startField.asInstanceOf[Field].y
        markPossibleMovesR(x, y, diced, ' ')
    } else {
      val x = stone.actualField.asInstanceOf[Field].x
      val y = stone.actualField.asInstanceOf[Field].y
      markPossibleMovesR(x, y, diced, ' ')
    }
  }

  private def markPossibleMovesR(x: Int, y: Int, depth: Int, cameFrom: Char): Unit = {
    if (depth == 0) {
      //Dont hit your own kind
      if (gameBoard.board(x)(y).asInstanceOf[Field].stone.sort == 'p'&&gameBoard.board(x)(y).asInstanceOf[Field].stone.asInstanceOf[PlayerStone].playerColor==activePlayer.color){
        return
      }
      gameBoard.board(x)(y).asInstanceOf[Field].avariable = true
      return
    } else {
      // If there is a blocking stone on the way dont go on
      if (gameBoard.board(x)(y).asInstanceOf[Field].stone.sort == 'b') {
        return
      }
      // up
      if (validField(x, y - 1) && cameFrom != 'u') {
        markPossibleMovesR(x, y - 1, depth - 1, 'd')
      }
      // down
      if (validField(x, y + 1) && cameFrom != 'd') {
        markPossibleMovesR(x, y + 1, depth - 1, 'u')
      }
      // left
      if (validField(x - 1, y) && cameFrom != 'r') {
        markPossibleMovesR(x - 1, y, depth - 1, 'l')
      }
      // right
      if (validField(x + 1, y) && cameFrom != 'l') {
        markPossibleMovesR(x + 1, y, depth - 1, 'r')
      }
    }
  }

  private def validField(x: Int, y: Int): Boolean = {
    // check for a vailid field
    if (y > 13 || y < 0) {
      false
    } else if (x > 16 || x < 0) {
      false
    } else if (gameBoard.board(x)(y).isFreeSpace()) {
      false
    } else {
      true
    }
  }

  private def unmarkPossibleMoves(): Unit = {
    for (y <- 0 to 15) {
      for (x <- 0 to 16) {
        if (!gameBoard.board(x)(y).isFreeSpace()) {
          gameBoard.board(x)(y).asInstanceOf[Field].avariable = false
        }
      }
    }
  }

  private def makeMove(stone: PlayerStone, destField: Field): Boolean = {
    val xStone = stone.actualField.asInstanceOf[Field].x
    val yStone = stone.actualField.asInstanceOf[Field].y
    val xDest = destField.x
    val yDest = destField.y
    if (validField(xDest, yDest) && validDestForMove(xDest, yDest)) {
      val hitStone = gameBoard.changeTwoStones(gameBoard.board(xStone)(yStone).asInstanceOf[Field], destField)
      hitStone.sort match {
        case 'p' => {
          gameBoard.resetPlayerStone(hitStone.asInstanceOf[PlayerStone])
          currentReturnStone = 'p'
        }
        case 'f' => currentReturnStone = 'f'
        case 'b' => currentReturnStone = 'b'
      }
      true
    } else {
      false
    }
  }

  private def validDestForMove(x: Int, y: Int): Boolean = {
    if (validField(x,y) && gameBoard.board(x)(y).asInstanceOf[Field].avariable) {
      true
    } else {
      false
    }
  }

  def isChosenBlockStone(x: Int,y: Int): Boolean = {
      if(validField(x,y)){
        if(gameBoard.board(x)(y).asInstanceOf[Field].stone.sort=='f'){
          gameBoard.setBlockStoneOnField(gameBoard.board(x)(y).asInstanceOf[Field])

          true
        }else {
          false
        }
      }else{
        false
      }
  }

  def checkValidPlayerStone(x: Int,y: Int): Boolean ={
    if(x>=0&&x<17&&y>=0&&y<16&&(!gameBoard.board(x)(y).isFreeSpace())&&gameBoard.board(x)(y).asInstanceOf[Field].stone.sort=='p'){
      var retBool:Boolean = false
      for(s <- activePlayer.stones){
        if((s.actualField.asInstanceOf[Field].x==gameBoard.board(x)(y).asInstanceOf[Field].x)
          &&(s.actualField.asInstanceOf[Field].y==gameBoard.board(x)(y).asInstanceOf[Field].y)){
          chosenPlayerStone=gameBoard.board(x)(y).asInstanceOf[Field].stone.asInstanceOf[PlayerStone]
          retBool=true
        }
      }
      retBool
    }else{
      false
    }
  }

  def makeAmove(x:Int,y:Int): Boolean ={
    if(validDestForMove(x,y) && makeMove(chosenPlayerStone,gameBoard.board(x)(y).asInstanceOf[Field])){
      true
    }else{
      false
    }

  }


  private def checkWin: Boolean = {
    val xWin = 8
    val yWin = 0
    if (gameBoard.board(xWin)(yWin).asInstanceOf[Field].stone.sort == 'p') {
      true
    } else {
      false
    }
  }

}
