# Tic-Tac-Toe
## Description 
This is a console-based Tic-Tac-Toe game implemented in Java. Players can compete against each other or against a computer opponent. The computer can play at two difficulty levels: easy and hard.
## Features 
- Play Against another Player or Ai on the same device. 
- Choose the level of difficulty against the Computer.
- Handle all the necessary exceptions, like square full or out of bounds moves. 
- Keep  the track of the points on the score board. 
## Installation 
1. Clone the repository:
   git clone https://github.com/chloee345/Tic-Tac-Toe.git
2. Navigate to the project directory:
   cd Tic-Tac-Toe
3. Compile the java files
   javac *.java

## Usage 
1. Run the main class
2. follow all the necessary indications to play against a player or computer, choose the level of difficulty and symbol. 
3. Input the row number (1-3) and the column character (A-C), to be able the place your symbol on the board.

## Game Rules 
1. The Random generator choose a player to start. 
2. Each turn a player should put their symbol on one the 9 squares of the board (unless it's already full). 
3. The first player to align a vertical, horizontal or diagonal line of their symbol wins. 
4. If no player win, it's a tie. 
5. Each turn the score board will be updated depending on the result of each game.  

## Main Classes
1. **`Main`**: The entry point of the application, contains necessary methods to control the flow of game. 
2. **`Helper`**: A class that has some additional methods, to add some important functionalities. 
3. **`Board`**: Represent the Game Board, A 3x3 grid, containing each a square. 
4. **`Square`**: Represent a single square that needs to be place on the board, it can be empty or full. 
5. **`Player`**: Abstract class representing a player that has the ability to play tic-tac-toe.
6. **`Human`**: Class that extends Player, meaning that a human player has the ability to play manually.
7. **`Computer`**: Class that extends Player, meaning that a computer player is controlled by Artificial Intelligence and has the ability to play internally. 
8. **`GameExceptions`**: Class that handles wrong input and full squares exceptions.  




