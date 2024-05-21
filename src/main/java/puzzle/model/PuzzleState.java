package puzzle.model;

import javafx.beans.property.ReadOnlyObjectProperty;
import puzzle.State;
import puzzle.TwoPhaseMoveState;
import javafx.beans.property.ReadOnlyObjectWrapper;
import java.util.HashSet;
import java.util.Set;

public class PuzzleState implements TwoPhaseMoveState {

    private ReadOnlyObjectWrapper<Square>[][] board;

    public PuzzleState() {
        this.board = new ReadOnlyObjectWrapper[3][5];
        initializeBoard();
        initializeInvalidBoard();
        initializeStones();
    }

    private void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReadOnlyObjectWrapper<>(Square.NONE); // Initialize with NONE
            }
        }
    }

    private void initializeInvalidBoard() {

        board[0][1] = new ReadOnlyObjectWrapper<>(Square.BLOCK);
        board[0][3] = new ReadOnlyObjectWrapper<>(Square.BLOCK);
        board[2][1] = new ReadOnlyObjectWrapper<>(Square.BLOCK);
        board[2][2] = new ReadOnlyObjectWrapper<>(Square.BLOCK);
        board[2][3] = new ReadOnlyObjectWrapper<>(Square.BLOCK);

    }

    private void initializeStones() {
        int numRows = board.length;
        int numCols = board[0].length;

        for (int i = 0; i < numRows; i++) {
            board[i][0].set(Square.RED); // Set RED squares in the first column
        }

        for (int i = 0; i < numRows; i++) {
            board[i][numCols - 1].set(Square.BLUE); // Set BLUE squares in the last column
        }
    }


    public ReadOnlyObjectProperty<Square> squareProperty(int i, int j) {
        return board[i][j].getReadOnlyProperty();
    }

    public Square getSquare(Position p) {
        return board[p.row()][p.col()].get();
    }

    private void setSquare(Position p, Square square) {
        board[p.row()][p.col()].set(square);
    }

    @Override
    public boolean isSolved() {
        return (board[0][0].get() == Square.BLUE && board[1][0].get() == Square.BLUE && board[2][0].get() == Square.BLUE) &&
                (board[0][4].get() == Square.RED && board[1][4].get() == Square.RED && board[2][4].get() == Square.RED);
    }


    @Override
    public boolean isLegalMove(Object move) {
        String moveStr = (String) move;
        String[] coordinates = moveStr.split("-");
        String[] srcIndices = coordinates[0].split(",");
        String[] destIndices = coordinates[1].split(",");

        int srcRow = Integer.parseInt(srcIndices[0]);
        int srcCol = Integer.parseInt(srcIndices[1]);
        int destRow = Integer.parseInt(destIndices[0]);
        int destCol = Integer.parseInt(destIndices[1]);

        // Check if source and destination positions are within bounds
        if (srcRow < 0 || srcRow >= board.length || srcCol < 0 || srcCol >= board[0].length ||
                destRow < 0 || destRow >= board.length || destCol < 0 || destCol >= board[0].length) {
            return false;
        }

        int rowDiff = Math.abs(destRow - srcRow);
        int colDiff = Math.abs(destCol - srcCol);

        // Check if the move is either a horizontal or vertical step
        if ((board[srcRow][srcCol].get() == Square.RED || board[srcRow][srcCol].get() == Square.BLUE) &&
                ((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1))) {
            // Check if the destination square is EMPTY
            return board[destRow][destCol].get() == Square.NONE;
        }
        return false;
    }


    @Override
    public void makeMove(Object move) {
        String moves = (String) move;
        String[] coordinates = moves.split("-");
        String[] srcIndices = coordinates[0].split(",");
        String[] destIndices = coordinates[1].split(",");

        int srcRow = Integer.parseInt(srcIndices[0]);
        int srcCol = Integer.parseInt(srcIndices[1]);
        int destRow = Integer.parseInt(destIndices[0]);
        int destCol = Integer.parseInt(destIndices[1]);

        Square stone = board[srcRow][srcCol].get();
        board[srcRow][srcCol].set(Square.NONE);
        board[destRow][destCol].set(stone);
    }


    @Override
    public Set<String> getLegalMoves() {
        Set<String> legalMoves = new HashSet<>();

        int numRows = board.length;
        int numCols = board[0].length;

        for (int idx = 0; idx < numRows * numCols; idx++) {
            int i = idx / numCols;
            int j = idx % numCols;

            if (board[i][j].get() == Square.RED || board[i][j].get() == Square.BLUE) {
                // Up
                if (i > 0 && board[i - 1][j].get() == Square.NONE) {
                    legalMoves.add(i + "," + j + "-" + (i - 1) + "," + j);
                }
                // Down
                if (i + 1 < numRows && board[i + 1][j].get() == Square.NONE) {
                    legalMoves.add(i + "," + j + "-" + (i + 1) + "," + j);
                }
                // Left
                if (j > 0 && board[i][j - 1].get() == Square.NONE) {
                    legalMoves.add(i + "," + j + "-" + i + "," + (j - 1));
                }
                // Right
                if (j + 1 < numCols && board[i][j + 1].get() == Square.NONE) {
                    legalMoves.add(i + "," + j + "-" + i + "," + (j + 1));
                }
            }
        }
        return legalMoves;
    }

    @Override
    public boolean isLegalToMoveFrom(Object from) {
        Square square = getSquare((Position) from);
        return square == Square.RED || square == Square.BLUE;
    }


    @Override
    public PuzzleState clone() {
        // Create a new PuzzleState object
        PuzzleState clonedState = new PuzzleState();

        // Copy the contents of the board array
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                clonedState.board[i][j].set(this.board[i][j].get());
            }
        }

        return clonedState;
    }


    public PuzzleState(ReadOnlyObjectWrapper<Square>[][] board) {
        this.board = board;
    }
}




