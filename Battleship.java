import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Random;

import java.util.Arrays;

public class Battleship {

  /**
   * This method returns a n * n player board
   * @param n the value that determines the size of the player board
   * @return the player's board
   */
    public static char[][] initBoard(int n) {
        char[][] board = new char[n][n];
        char x = '~';
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] =  x;
            }
        }
        return board;
    }

    /**
     * This method prints the player board as a grid
     * @param playerTurn the value for who's turn it is
     * @param board the 2D array that represent's the player's board
     * @param hitsLeft the number of the remaining ship targets in this board
     * @return nothing
     */
    public static void printBoard(int playerTurn, char[][] board,
        int hitsLeft) {
        System.out.println("Player" + playerTurn + " (" + hitsLeft
            + " hits left):");
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * This method places an O or an X at the target location based on if the
     target equals one of the ship locations
     * @param board the 2D array that represents the player board
     * @param target the String value of the player's selected target
     * @param shipLocations the array of the opponent's predetermined
     ship locations
     * @return the number of the remaining ship targets in this board
     */
    public static int fireMissile(char[][] board, String target,
        String[] shipLocations, int hitsLeft) {

        int[] cleantarget = convertLocation(target);
        int r = cleantarget[0];
        int c = cleantarget[1];

        if (board[r][c] == 'X') {
            System.out.println("target " + target + "has already been chosen!");
        } else if (isShip(target, shipLocations)) {
            System.out.println("Hit!");
            board[r][c] = 'X';
            hitsLeft--;
        } else {
            System.out.println("Miss!");
            board[r][c] = 'O';
        }

        return hitsLeft;
    }

    /**
     * This method checks if the target is equal to one of the ship locations
     * @param target the String value of the player's target
     * @param shipLocations the array of the opponent's predetermined
     ship locations
     * @return true or false whether the target is equal to one of the values
     in shipLocations
     */
    public static boolean isShip(String target, String[] shipLocations) {
        boolean isitShip = false;
        for (int i = 0; i < shipLocations.length; i++) {
            if (shipLocations[i].equals(target)) {
                isitShip = true;
            }
        }
        return isitShip;
    }

    /**
     * This method converts the input String into an int array to be used for
     the board
     * @param coordinate the String value of the player's guess
     * @return an array of the converted player's guess to be used for the board
     */
    public static int[] convertLocation(String coordinate) {
        String cleancoordinate = coordinate.toLowerCase();
        int row = (int) cleancoordinate.charAt(0) - (int) 'a';
        int column = (int) cleancoordinate.charAt(1) - 49;
        int[] rowColumn = {row, column};
        return rowColumn;
    }

    /**
     * This is the main method
     * @param args some value in the command line
     * @return nothing
     */
    public static void main(String[] args) {
        int fileInd = (args.length > 0) ? Integer.parseInt(args[0])
            : new Random().nextInt(4);

        String filename = "game" + fileInd + ".txt";

        try {
            Scanner fileReader = new Scanner(new File("C:\\Users\\2003j"
                + "\\cs1331\\gt hw\\gt2\\" + filename));
            Scanner inputScanner = new Scanner(System.in);

            // where he said write code
            char[][] player1board, player2board;
            String[] player1ships, player2ships;

            int side = Integer.parseInt(fileReader.nextLine());
            player1board = initBoard(side);
            player2board = initBoard(side);
            player1ships = (fileReader.nextLine()).split(" ");
            player2ships = (fileReader.nextLine()).split(" ");

            int playerturn = 1;
            int player1hitsLeft = player2ships.length;
            int player2hitsLeft = player1ships.length;
            boolean player1wins, player2wins;
            player1wins = false;
            player2wins = false;

            while (!player1wins && !player2wins) {
                if (playerturn % 2 != 0) {
                    printBoard(playerturn, player2board, player1hitsLeft);
                    System.out.println("Enter missile location: ");
                    String guess = inputScanner.nextLine();
                    fireMissile(player2board, guess, player2ships,
                        player1hitsLeft);
                    printBoard(playerturn, player2board, player1hitsLeft);
                    System.out.println("\n\n" + "-----------------------"
                        + "\n\n");

                    if (player1hitsLeft == 0) {
                        player1wins = true;
                    }
                    playerturn++;
                } else {
                    printBoard(playerturn, player1board, player2hitsLeft);
                    System.out.println("Enter missile location: ");
                    String guess2 = inputScanner.nextLine();
                    fireMissile(player1board, guess2, player1ships,
                        player2hitsLeft);
                    printBoard(playerturn, player1board, player2hitsLeft);
                    System.out.println("\n\n" + "-----------------------"
                        + "\n\n");

                    if (player2hitsLeft == 0) {
                        player2wins = true;
                    }
                    playerturn--;
                }
            }

            if (player1wins) {
                System.out.println("The winner is Player 1");
            } else {
                System.out.println("The winner is Player 2");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Make sure that " + filename
                + " is in the current directory!");
        }
    }
}