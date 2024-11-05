import java.util.Scanner;

public class Main {

    // Constants for the game
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    private static String currentPlayer = "X"; // X always starts
    private static int moveCount = 0; // Count the number of moves

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        // Main game loop
        do {
            clearBoard();
            displayBoard();

            // Game loop for making moves
            while (true) {
                int rowMove = SafeInput.getRangedInt(console, "Enter row (1-3): ", 1, 3) - 1;
                int colMove = SafeInput.getRangedInt(console, "Enter column (1-3): ", 1, 3) - 1;

                if (isValidMove(rowMove, colMove)) {
                    board[rowMove][colMove] = currentPlayer;
                    moveCount++;
                    displayBoard();

                    // Check for win or tie
                    if (isWin(currentPlayer)) {
                        System.out.println("Player " + currentPlayer + " wins!");
                        break;
                    } else if (isTie()) {
                        System.out.println("It's a tie!");
                        break;
                    }

                    // Switch players
                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }

            // Ask if players want to play again
        } while (SafeInput.getYNConfirm(console, "Play again? (Y/N): "));

        console.close();
    }

    // Helper method to clear the board
    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    // Helper method to display the board
    private static void displayBoard() {
        System.out.println("Current board:");
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.print(" " + board[row][col] + " ");
                if (col < COLS - 1) System.out.print("|");
            }
            System.out.println();
            if (row < ROWS - 1) {
                System.out.println("---+---+---");
            }
        }
    }

    // Helper method to check if a move is valid
    private static boolean isValidMove(int row, int col) {
        return (row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col].equals(" "));
    }

    // Helper method to check if there is a win
    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagnalWin(player);
    }

    // Helper method to check if there's a row win
    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check if there's a column win
    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check if there's a diagonal win
    private static boolean isDiagnalWin(String player) {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false;
    }

    // Helper method to check if there's a tie
    private static boolean isTie() {
        if (moveCount == 9) {
            return true;
        }

        // Check if all win vectors are blocked by both X and O
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }

        return true;
    }
}
