# connect-four-vag
## Connect Four (Java Console Game)

## Overview
This is a Java-based console implementation of the classic **Connect Four** game. Two players take turns dropping tokens into a 7-column, 6-row grid, attempting to connect four of their pieces in a row, column, or diagonal before their opponent.

## Features
- Turn-based gameplay for two players.
- Input validation to prevent invalid moves.
- Board display updates after each turn.
- Win detection (horizontal, vertical, diagonal).
- Option to replay after a game ends.

## How to Play
1. Run the Java program in a terminal.
2. Players take turns choosing a column (1-7) to drop their token ('●' or '○').
3. The first player to connect **four in a row, column, or diagonal** wins.
4. If the board fills up before a winner is found, the game ends in a **draw**.
5. Players are prompted to restart or exit after a game ends.

## How to Run
### Prerequisites
- Java Development Kit (JDK) installed (Java 8+ recommended).

### Steps
1. **Clone or Download** this repository.
2. **Compile the Java file**:
   javac ConnectFourVag.java
3. **Run the game**:
   java ConnectFourVag
   

## Code Structure
- `ConnectFourVag.java` - The main game logic and user interface.
- **Key Methods:**
  - `printBoard()` - Displays the game board.
  - `getValidInteger()` - Ensures valid player input.
  - `matchIsOver()` - Checks if a player has won.
  - `findFreePosition()` - Finds the lowest available row in a column.

## Author
**Vaggelis Theodorakis**  
Feel free to contribute or suggest improvements!

## License
This project is open-source and free to use.

