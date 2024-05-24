package puzzle.model;

import java.util.Scanner;
import java.util.Set;

/**
 * The {@code TerminalRun} class provides a terminal-based interface to play the puzzle game.
 * It allows users to interact with the game through the command line.
 */
public class TerminalRun {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PuzzleState puzzleState = new PuzzleState();

        while (!puzzleState.isSolved()) {
            printBoard(puzzleState);

            Set<String> legalMoves = puzzleState.getLegalMoves();
            System.out.println("Legal Moves:");
            for (String move : legalMoves) {
                System.out.println(move);
            }

            System.out.print("Enter your move (format: srcRow,srcCol-destRow,destCol): ");
            String move = scanner.nextLine();

            if (legalMoves.contains(move)) {
                puzzleState.makeMove(move);
            } else {
                System.out.println("Illegal move. Please try again.");
            }
        }

        System.out.println("Congratulations! The puzzle is solved.");
    }

    /**
     * Prints the current state of the game board to the terminal.
     * It uses different characters to represent the various types of squares.
     *
     * @param puzzleState the current state of the puzzle
     */
    private static void printBoard(PuzzleState puzzleState) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                Square square = puzzleState.getSquare(new Position(i, j));
                switch (square) {
                    case NONE:
                        System.out.print(".");
                        break;
                    case RED:
                        System.out.print("R");
                        break;
                    case BLUE:
                        System.out.print("B");
                        break;
                    case BLOCK:
                        System.out.print("X");
                        break;
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
