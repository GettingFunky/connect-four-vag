package connectfourvag;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConnectFourVag {

    static Scanner sc = new Scanner(System.in);
    static final int ROWS = 6;
    static final int COLS = 7;

    public static void main(String[] args) {

        char[][] connectFourBoard = new char[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                connectFourBoard[i][j] = ' ';
            }
        }
        boolean checkWin = false;
        int turnCount = 1;
        char currentPlayerSymbol = '●'; // First player always starts with '●'
        String winnerName = "";

        String namePlayer1 = " ";
        String namePlayer2 = " ";

        System.out.println("Let's play Connect4!");
        System.out.println(" ------------- ");
        System.out.println("|             |");
        System.out.println("|   CONNECT   |");
        System.out.println("|   F O U R   |");
        System.out.println("|             |");
        System.out.println("|   ● ● ● ●   |");
        System.out.println("|   ○ ○ ○ ○   |");
        System.out.println(" ------------- ");
        System.out.println();

        namePlayer1 = getValidName("Player 1, enter your name");
        System.out.printf("OK %s, you get the ● \n", namePlayer1);

        System.out.println();
        namePlayer2 = getValidName("Player 2, enter your name");
        System.out.printf("OK %s, you get the ○ \n", namePlayer2);

        System.out.println();
        System.out.println("●○●○●○●○●○●○●○●○●○●○●○●○●○●○●○●○●○●○●○●○");
        System.out.println("             Let's play!");
        System.out.println("○●○●○●○●○●○●○●○●○●○●○●○●○●○●○●○●○●○●○●○●");
        System.out.printf("%s, you go first! \n", namePlayer1);

        while (true) {

                printBoard(connectFourBoard);

                System.out.printf("In which column (1 - 7) would you like to place your %s? \n", currentPlayerSymbol);

            int[] playerPosition = getValidInteger(connectFourBoard);
            int row = playerPosition[0];
            int col = playerPosition[1];
            connectFourBoard[row][col] = currentPlayerSymbol;

            printBoard(connectFourBoard);

            // Check for a win condition only after turn 7 (minimum moves for a win)
            if (turnCount > ROWS) {
                checkWin = matchIsOver(connectFourBoard);
            }

            if (checkWin) {
                winnerName = currentPlayerSymbol == '●' ? namePlayer1 : namePlayer2;
                break; // Exit the loop when a winner is found
            }

            turnCount++;

            if (turnCount > 42) {
                break;
            }

            currentPlayerSymbol = switchPlayerSymbol(currentPlayerSymbol, namePlayer1, namePlayer2);

        }

        // Announce the game result
        if (checkWin) {
            printBoard(connectFourBoard);
            System.out.printf("Congratulations %s, you win in %d turns! Better luck next time %s!\n",
                    winnerName, turnCount, winnerName.equals(namePlayer1) ? namePlayer2 : namePlayer1);
        } else {
            printBoard(connectFourBoard);
            System.out.println("It's a draw! Congratulations to both of you");
        }

    }
    // Validates and retrieves player names
    public static String getValidName(String prompt) {
        String playerName;
        while (true) {
            try {
                System.out.println(prompt);
                playerName = sc.nextLine().trim();

                if (playerName.isEmpty()) {
                    System.out.println("Name cannot be blank. Please enter a valid name.");
                    continue;
                }
                break;

            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please try again.");
            }
        }
        return playerName;
    }

    // Ensures the entered number is between 1 and 7
    public static int[] getValidInteger(char[][] board) {
        int userInteger = 0;
        int row;
        while (true) {
            try {
                userInteger = sc.nextInt();
                if (userInteger > 7 || userInteger < 1) {
                    System.out.println("Please provide a number between 1 and 7");
                    continue;
                }
                row = findFreePosition(board, userInteger);
                if (row == -1) {
                    System.out.printf("Column %d is maxed out. Please choose another column. \n", userInteger);
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number between 1 and 7");
                sc.next();
                continue;
            }
            return new int[]{row, userInteger - 1}; // Return row and column indices
        }
    }

    // Checks if a position on the board is free
    public static int findFreePosition(char[][] board, int col) {

        for (int i = 0; i < ROWS; i++) {
            if (board[i][col - 1] == ' ') {
                return i;
            }
        }
        return -1;
    }

    // Prints the current state of the board
    public static void printBoard(char[][] board) {
        System.out.println(" 1 2 3 4 5 6 7 ");
        System.out.println("---------------");
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLS; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------------");
    }

    // Determines if the match has ended with a winner
    public static boolean matchIsOver(char[][] connectFourBoard) {
        // Check horizontal, vertical, and diagonal lines for a win
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                char currentSymbol = connectFourBoard[row][col];
                if (currentSymbol == ' ') {
                    continue; // Skip empty spaces
                }

                // Check horizontal (right)
                if (col <= 3 && // Ensure there are at least 3 columns to the right
                        currentSymbol == connectFourBoard[row][col + 1] &&
                        currentSymbol == connectFourBoard[row][col + 2] &&
                        currentSymbol == connectFourBoard[row][col + 3]) {
                    return true; // Found 4 in a row horizontally
                }

                // Check vertical (down)
                if (row <= 2 && // Ensure there are at least 3 rows below
                        currentSymbol == connectFourBoard[row + 1][col] &&
                        currentSymbol == connectFourBoard[row + 2][col] &&
                        currentSymbol == connectFourBoard[row + 3][col]) {
                    return true; // Found 4 in a row vertically
                }

                // Check diagonal (down-right)
                if (row <= 2 && col <= 3 && // Ensure there are at least 3 rows and 3 columns
                        currentSymbol == connectFourBoard[row + 1][col + 1] &&
                        currentSymbol == connectFourBoard[row + 2][col + 2] &&
                        currentSymbol == connectFourBoard[row + 3][col + 3]) {
                    return true; // Found 4 in a row diagonally
                }

                // Check diagonal (down-left)
                if (row <= 2 && col >= 3 && // Ensure there are at least 3 rows and 3 columns
                        currentSymbol == connectFourBoard[row + 1][col - 1] &&
                        currentSymbol == connectFourBoard[row + 2][col - 2] &&
                        currentSymbol == connectFourBoard[row + 3][col - 3]) {
                    return true; // Found 4 in a row anti-diagonally
                }
            }
        }

        return false; // No winner found
    }

    // Switches the current player's symbol for the next turn
    public static char switchPlayerSymbol(char currentPlayerSymbol, String namePlayer1, String namePlayer2) {
        if (currentPlayerSymbol == '●') {
            System.out.printf("%s, it's your turn \n", namePlayer2);
            System.out.println("Choose a column (1-7): ");
            return '○';
        } else {
            System.out.printf("%s, it's your turn \n", namePlayer1);
            System.out.println("Choose a column (1-7): ");
            return '●';
        }
    }


}