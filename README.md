Malefiz
========================
[![Build Status](https://travis-ci.org/HuntedHunter/de.htwg.se.Malefiz.svg?branch=master)](https://travis-ci.org/HuntedHunter/de.htwg.se.Malefiz) [![Coverage Status](https://coveralls.io/repos/github/HuntedHunter/de.htwg.se.Malefiz/badge.svg?branch=master)](https://coveralls.io/github/HuntedHunter/de.htwg.se.Malefiz?branch=master)

# Table of Contents
1. [About the Project](#about-the-project)
1. [Purpose of the Project](#purpose-of-the-project)
1. [Game Instructions](#game-instructions)



## About the Project
A Scala implementation of the board-game **Malefiz**  
Malefiz is a widely used board game by [Ravensburger](https://www.ravensburger.de/start/index.html) and was created in 1959 by Werner Schöppner

## Purpose of the Project
The project was developed in the context of the lecture "Software Engineering" at the University of Applied Science Konstanz, Germany. (WS 17/18)  
The goal of this project was to learn and apply these following components:

* version control with git
* layered architecture
* design patterns

## Game Instructions
### Goal of the game
The first player who moves one of his pieces with an exact roll onto the top most field wins!

### Course of the game
The first Player starts. Whose turn it is, dices and draws accordingly with one of his piece. The number rolled must always be drawn completely. You can't draw into one's base. It is sometimes not possible to draw with a piece.  In this case, the player will skip one round. The player on the turn decides which piece to draw with.
### The blocking stones
Blocking stones are obstacles that cannot be jumped over. In order to clear them out of the way, a figure with an exact roll must land on a field occupied by a boulder. The player at the turn takes the boulder and places it, with the exception of the lowest row, on any square of the board. 
### Hitting another player's pieces
Only one piece may be placed on each square. If a figure with an exact throw hits a field that is already occupied by another figure, it is hit and reset to one of the starting fields of its color. From here it can be brought back into play.

This implementation comes along with a *TUI* (textual user interface) and *GUI* (graphical user interface).
