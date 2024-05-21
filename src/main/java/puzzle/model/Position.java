package puzzle.model;

public record Position(int row, int col) {

    @Override
    public String toString() {
        return String.format(row+","+col);
    }

}