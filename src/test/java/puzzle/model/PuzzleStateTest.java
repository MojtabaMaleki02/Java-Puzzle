package puzzle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PuzzleStateTest {

    /*
    @Test
    void isSolved() {
        PuzzleState puzzleState = new PuzzleState();
        assertFalse(puzzleState.isSolved());
        puzzleState.makeMove("0,1-1,1");
        puzzleState.makeMove("1,1-1,2");
        puzzleState.makeMove("1,2-0,2");
        assertTrue(puzzleState.isSolved());
    }

     */

    @Test
    void isLegalMove() {
        PuzzleState puzzleState = new PuzzleState();
        assertTrue(puzzleState.isLegalMove("1,0-1,1")); // Legal move
        assertFalse(puzzleState.isLegalMove("1,1-3,2")); // Illegal move
    }

    @Test
    void makeMove() {
        PuzzleState puzzleState = new PuzzleState();
        Square initialSquare = puzzleState.getSquare(new Position(1, 4));
        puzzleState.makeMove("1,4-1,3");
        Square movedSquare = puzzleState.getSquare(new Position(1, 3));
        assertEquals(initialSquare, movedSquare);
        assertEquals(Square.NONE, puzzleState.getSquare(new Position(1, 4)));
    }

    @Test
    void getLegalMoves() {
        PuzzleState puzzleState = new PuzzleState();
        puzzleState.makeMove("0,1-1,1");
    }

    @Test
    void isLegalToMoveFrom() {
        PuzzleState puzzleState = new PuzzleState();
        assertTrue(puzzleState.isLegalToMoveFrom(new Position(0, 0)));
        assertFalse(puzzleState.isLegalToMoveFrom(new Position(0, 1)));
    }
}