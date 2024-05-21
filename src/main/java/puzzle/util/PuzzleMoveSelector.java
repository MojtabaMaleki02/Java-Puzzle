package puzzle.util;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import puzzle.model.PuzzleState;
import puzzle.model.Position;

public class PuzzleMoveSelector {

    public enum Phase {
        SELECT_FROM,
        SELECT_TO,
        READY_TO_MOVE
    }

    private PuzzleState model;
    private ReadOnlyObjectWrapper<Phase> phase = new ReadOnlyObjectWrapper<>(Phase.SELECT_FROM);
    private boolean invalidSelection = false;
    private Position from;
    private Position to;

    public PuzzleMoveSelector(PuzzleState model) {
        this.model = model;
    }

    public Phase getPhase() {
        return phase.get();
    }

    public ReadOnlyObjectProperty<Phase> phaseProperty() {
        return phase.getReadOnlyProperty();
    }

    public boolean isReadyToMove() {
        return phase.get() == Phase.READY_TO_MOVE;
    }

    public void select(Position position) {
        switch (phase.get()) {
            case SELECT_FROM -> selectFrom(position);
            case SELECT_TO -> selectTo(position);
            case READY_TO_MOVE -> throw new IllegalStateException();
        }
    }

    private void selectFrom(Position position) {
        if (model.isLegalToMoveFrom(position)) {
            from = position;
            phase.set(Phase.SELECT_TO);
            invalidSelection = false;
        } else {
            invalidSelection = true;
        }
    }

    private void selectTo(Position position) {
        String move = from.row() + "," + from.col() + "-" + position.row() + "," + position.col();
        System.out.println(move);
        if (model.isLegalMove(move)) {
            to = position;
            phase.set(Phase.READY_TO_MOVE);
            invalidSelection = false;
        } else {
            invalidSelection = true;
        }
    }

    public Position getFrom() {
        if (phase.get() == Phase.SELECT_FROM) {
            throw new IllegalStateException();
        }
        return from;
    }

    public Position getTo() {
        if (phase.get() != Phase.READY_TO_MOVE) {
            throw new IllegalStateException();
        }
        return to;
    }

    public boolean isInvalidSelection() {
        return invalidSelection;
    }

    public void makeMove() {

        if (phase.get() != Phase.READY_TO_MOVE) {
            throw new IllegalStateException();
        }
        String move = from.row() + "," + from.col() + "-" + to.row() + "," + to.col();

        model.makeMove(move);
        reset();
    }

    public void reset() {
        from = null;
        to = null;
        phase.set(Phase.SELECT_FROM);
        invalidSelection = false;
    }
}
