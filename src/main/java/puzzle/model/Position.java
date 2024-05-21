package puzzle.model;

/**
 * The {@code Position} record represents a position on a two-dimensional grid.
 * It contains the row and column indices of the position.
 */
public record Position(int row, int col) {
    /**
     * Returns a string representation of the position in the format "row,col".
     *
     * @return a string representation of the position
     */
    @Override
    public String toString() {
        return String.format(row+","+col);
    }

}