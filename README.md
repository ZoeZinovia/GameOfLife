# GameOfLife

Java implementation of Conway's Game Of Life 

## Description

The objective of this project was to design a Game of Life program that takes an initial board (sequence of cells) as input and shows the development of the board after n number of transitions, where n is the number provided by the user. The evolution of the board depended on 4 transition rules:
  1. If a cell is alive and it has less than 2 living neighbours, it dies due to underpopulation in the next evolution of the board.
  2. If a cell is alive and has more than 3 living neighbours, it dies due to overpopulation in the next evolution.
  3. If a cell is dead and has exactly 3 living neighbours, it is born in the next evolution.
  4. The cell remains in the same state otherwise.

The first line of input is a single natural number, which specifies the number of transitions that needs to be affected. The next line/s represent rows of the Game of Life board. These lines only allow for 0 and 1 values as input. 0 represents a dead cell, whilst 1 represents a living cell. The output is a representation of the board, where each line shows a row in the board. There are as many output boards as there are transitions.

![Image showing steps of the Monte Carlo Tree Search algorithm](https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Game_of_life_Simkin_glider_gun.svg/749px-Game_of_life_Simkin_glider_gun.svg.png)

A number of approaches were possible for this project. A sparse matrix implementation was selected since it requires much less space than a 2-dimensional array. A sparse matrix is comprised of sentinel and data nodes, where the sentinel nodes form a circular linked list. All methods were programmed using a Test-Driven Approach. See [here](https://medium.com/@sidhantpanda/conways-game-of-life-explained-with-some-programming-a2970b468580) for more on the Game of Life.

## Getting Started

### Dependencies

* Java 8 is the only requirement. 

### Installing and using

* Simply clone the code from this repository. If using Eclipe, IntelliJ or another IDE, client.java can be run. 
* If running on terminal, src code needs to be compiled before running client. This can be done by navigating into the src directory of the cloned the repository. Then run: ```javac -d ./../bin -sourcepath . Client.java ```. Then the application can be run from the bin directory with the following command: ```java Client```
* The following command arguments are expected: An integer in the first line that represents the desired number of iterations. On the next line/s you can enter the initial configuration of the Game of Life board. If the initial configuration is empty, an empty board will be created. See below for an example:
  ```
  >> java Client
  >> 3  
  >> 101  
  >> 010  
  >> 111
  ```
 * This yields the following boards at each iteration:
  ```
  010
  000
  111
  010

  000
  101
  111
  111

  00000
  01010
  10001
  01010
  00100
  ```
   
