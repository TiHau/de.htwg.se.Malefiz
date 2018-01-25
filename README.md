Malefiz
========================
[![Build Status](https://travis-ci.org/HuntedHunter/de.htwg.se.Malefiz.svg?branch=master)](https://travis-ci.org/HuntedHunter/de.htwg.se.Malefiz) [![Coverage Status](https://coveralls.io/repos/github/HuntedHunter/de.htwg.se.Malefiz/badge.svg?branch=master)](https://coveralls.io/github/HuntedHunter/de.htwg.se.Malefiz?branch=master)

# Table of Contents
1. [About the Project](#about-the-project)
2. [Purpose of the Project](#purpose-of-the-project)
3. [Usability and Humanity Requirements](#usability-and-humanity-requirements)
4. [Performance Requirements](#performance-requirements)
5. [How to Play](#how-to-play)  
  5.1 [Explanation of the user interface](#explanation-of-the-user-interface)  
  5.2 [Game Instructions](#game-instructions)




## About the Project
A Scala implementation of the board-game **Malefiz**  
Malefiz is a widely used board game by [Ravensburger](https://www.ravensburger.de/start/index.html) and was created in 1959 by Werner Schöppner

## Purpose of the Project
The project was developed in the context of the lecture "Software Engineering" at the University of Applied Science Konstanz, Germany. (WS 17/18)  
The goal of this project was to learn and apply these following components:

* Version control with git
* Layered architecture (MVC)
* Design patterns
* Timemanagement with Scrum
* Code-Coverage using [coveralls.io](https://coveralls.io/)
* Learning the scala programming language
* Logging
* Event oriented parallel running GUI & TUI
* Continuous integration using [Travis-CI](https://travis-ci.org/)
* Dependency injection

## Usability and Humanity Requirements
For the use of the created game only the handling of computers and necessarily the communication with at least one other person as an opponent is required.

## Performance Requirements
The game should run on any computer used today without problems

## How to Play
### Explanation of the user interface
The user interface was kept very simple and should be self-explanatory. When the game is launched, you will first be asked for the number of players. Then you can start playing immediately. The top right corner shows the active player, the upper left corner shows the roll of the dice and the middle corner shows what to do. In the dropdown menus you can save the game, load an old score or start a new game. As well as undoing and redoing a move.  
The keyboard shortcut for undoing is **backspace**

### Game Instructions
#### Goal of the game
The first player who moves one of his pieces with an exact roll onto the top most field wins!

#### Course of the game
The first Player starts. Whose turn it is, dices and draws accordingly with one of his piece. The number rolled must always be drawn completely. You can't draw into one's base. It is sometimes not possible to draw with a piece.  In this case, the player will skip one round. The player on the turn decides which piece to draw with.
#### The blocking stones
Blocking stones are obstacles that cannot be jumped over. In order to clear them out of the way, a figure with an exact roll must land on a field occupied by a boulder. The player at the turn takes the boulder and places it, with the exception of the lowest row, on any square of the board. 
#### Hitting another player's pieces
Only one piece may be placed on each square. If a figure with an exact throw hits a field that is already occupied by another figure, it is hit and reset to one of the starting fields of its color. From here it can be brought back into play.

This implementation comes along with a *TUI* (textual user interface) and *GUI* (graphical user interface).
