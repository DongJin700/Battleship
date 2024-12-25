# Battleship
A console-based implementation of the classic Battleship game, built using Java. This project demonstrates core programming concepts such as file I/O, arrays, and turn-based logic in a multiplayer game.

Features
Dynamic Board Creation: Flexible board size initialized from input files.
Turn-Based Gameplay: Supports two players with alternating turns to guess and attack.
Ship Placement: Ships are read from configuration files for a predefined setup.
Missile Targeting: Logic to handle hits, misses, and repeated guesses.
Console Visualization: Clear output of the board state after each turn.
Error Handling: Robust checks for file input and invalid coordinates.
How to Play
Clone the repository:

bash
Copy code
git clone https://github.com/your-username/battleship-java.git
cd battleship-java
Compile the Java program:

bash
Copy code
javac Battleship.java
Run the program with a game configuration file:

bash
Copy code
java Battleship 0
Replace 0 with the game file index (e.g., 0, 1, 2, etc.).

Game Configuration File
The game requires a .txt file as input to set up the board and ships. Each file follows this format:

Line 1: Board size (integer, e.g., 5 for a 5x5 grid).
Line 2: Player 1 ship locations (space-separated, e.g., A1 B2 C3).
Line 3: Player 2 ship locations (space-separated, e.g., D4 E5 A2).
Example file (game0.txt):

Copy code
5
A1 B2 C3
D4 E5 A2
Gameplay Rules
Each player has a turn to target a location (e.g., A1).
A hit is marked as X, and a miss is marked as O.
The game continues until all ships of one player are destroyed.
The first player to destroy all opponent ships wins.
