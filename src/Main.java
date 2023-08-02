import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static char[][] board = new char[3][3];
    static int row = 0;
    static int col = 0;

    public static void main(String[] args) {
        boolean player1 = true;
        boolean gameOver = false;
        char c;

        //Intialise the 2d array board
        for (char[] strings : board) {
            Arrays.fill(strings, '-');
        }

        String[] names = assignNames();

        //Gameplay loop
        while (!gameOver) {

            //Check whose turn it is and print it
            if (player1) {
                System.out.println("Player 1's turn");
            } else {
                System.out.println("Player 2's turn");
            }

            prettyPrintBoard();

            //Assigning player to enter xs and player 2 to enter os
            if (player1) {
                c = 'x';
            } else {
                c = 'o';
            }

            //Entering position loop
            while (true) {
                //Allowing the user to enter the position they wish
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter row: 1, 2 or 3");

                while (!sc.hasNextInt()) sc.next();
                row = sc.nextInt();

                System.out.println("Enter column: 1, 2 or 3");
                while (!sc.hasNextInt()) sc.next();
                col = sc.nextInt();

                // Checking if the position is on the board
                if (row < 1 || col < 1 || row > 3 || col > 3) {
                    System.out.println("This position is invalid! Try again.");
                }
                //Check if it is empty
                else if (!isEmpty(row - 1, col - 1)) {
                    System.out.println("This position is already filled. Try again.");
                } else {
                    System.out.printf("Ok! You have selected row: %d, column: %d%n", row, col);
                    break;
                }
            }

            //Assign the position to the player
            board[row - 1][col - 1] = (c);

            //Checking if someone has won
            if (playerHasWon() == 'x') {
                System.out.println(names[0] + " has won!");
                gameOver = true;
            } else if (playerHasWon() == 'o') {
                System.out.println(names[1] + " has won!");
                gameOver = true;
            } else {
                //Check for a tie
                if (fullBoard()) {
                    System.out.println("It's a tie!");
                    gameOver = true;
                }
            }
            prettyPrintBoard();
            //Set player to the other player
            player1 = !player1;
        }
    }


    //Uses Scanner to allow players to enter their names
    public static String[] assignNames() {
        String[] playerNames = new String[2];
        Scanner myObj = new Scanner(System.in);


        System.out.println("Enter Player A's Name");
        String player1Username = myObj.nextLine();

        System.out.println("Enter Player B's Name");
        String player2Username = myObj.nextLine();


        playerNames[0] = player1Username;
        playerNames[1] = player2Username;
        System.out.printf("Player A: %s%n Player B: %s%n", player1Username, player2Username);
        return playerNames;
    }


    //PrettyPrints the board for the players
    public static void prettyPrintBoard() {
        String row1 = String.format("| %s | %s | %s |", board[0][0], board[0][1], board[0][2]);
        String row2 = String.format("| %s | %s | %s |", board[1][0], board[1][1], board[1][2]);
        String row3 = String.format("| %s | %s | %s |", board[2][0], board[2][1], board[2][2]);
        System.out.println("_____________");
        System.out.printf("%s%n%s%n%s%n", row1, row2, row3);
        System.out.println("_____________");
    }

    //Checks if the square already has a value - returns true if it is valid for a player to enter their value
    public static boolean isEmpty(int row, int col) {
        return board[row][col] == '-';
    }

    //Checks if a player has won
    public static char playerHasWon() {

        //Check each row
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                return board[i][0];
            }
        }

        //Check each column
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
                return board[0][j];
            }
        }

        //Check the diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return board[0][0];
        }
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != '-') {
            return board[2][0];
        }

        //Otherwise nobody has won yet
        return ' ';
    }


    //Checks if board is full
    public static boolean fullBoard() {
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}



