package puzzle.controller;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import puzzle.model.Position;
import puzzle.model.PuzzleState;
import puzzle.model.Square;
import puzzle.util.PuzzleMoveSelector;
import org.tinylog.Logger;


/**
 * The {@code PuzzleController} class is responsible for managing the game's UI and interactions.
 * It handles user inputs, updates the game state, and manages the display of the game board.
 *
 * @see javafx.fxml.FXML
 * @see javafx.scene.layout.GridPane
 * @see puzzle.model.PuzzleState
 * @see puzzle.util.PuzzleMoveSelector
 */

public class PuzzleController {

    @FXML
    private GridPane board;

    private PuzzleState model;

    private PuzzleMoveSelector selector;

    /**
     * Initializes the game controller. This method sets up the game state, the move selector,
     * and the game board UI components.
     */
    @FXML
    private void initialize() {
        model = new PuzzleState();
        selector = new PuzzleMoveSelector(model);

        for (var i = 0; i < board.getRowCount(); i++) {
            for (var j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare(i, j);
                board.add(square, j, i);
            }
        }
        selector.phaseProperty().addListener(this::showSelectionPhaseChange);
    }

    /**
     * Creates a square at the specified position on the game board.
     *
     * @param i the row index
     * @param j the column index
     * @return the {@code StackPane} representing the square
     */

    private StackPane createSquare(int i, int j) {
        var square = new StackPane();
        square.getStyleClass().add("square");
        var piece = new Circle(50);
        piece.fillProperty().bind(createSquareBinding(model.squareProperty(i, j)));
        square.getChildren().add(piece);
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    /**
     * Handles mouse click events on the game board squares.
     *
     * @param event the {@code MouseEvent} representing the click event
     */
    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        Logger.info("Click on square ({},{})", row, col);

        selector.select(new Position(row, col));
        if (selector.isReadyToMove()) {
            selector.makeMove();
        }
        handleGameCompletion();
    }

    /**
     * Creates a binding for the color property of a square based on its state.
     *
     * @param squareProperty the property representing the state of the square
     * @return the {@code ObjectBinding<Paint>} for the square's color
     */
    private ObjectBinding<Paint> createSquareBinding(ReadOnlyObjectProperty<Square> squareProperty) {
        return new ObjectBinding<Paint>() {
            {
                super.bind(squareProperty);
            }
            @Override
            protected Paint computeValue() {
                return switch (squareProperty.get()) {
                    case NONE -> Color.TRANSPARENT;
                    case RED -> Color.RED;
                    case BLUE -> Color.BLUE;
                    case BLOCK -> Color.BLACK;
                };
            }
        };
    }

    /**
     * Handles changes in the selection phase of the move selector.
     *
     * @param value the observable value representing the phase property
     * @param oldPhase the previous phase
     * @param newPhase the new phase
     */
    private void showSelectionPhaseChange(ObservableValue<? extends PuzzleMoveSelector.Phase> value, PuzzleMoveSelector.Phase oldPhase, PuzzleMoveSelector.Phase newPhase) {
        switch (newPhase) {
            case SELECT_FROM -> {}
            case SELECT_TO -> showSelection(selector.getFrom());
            case READY_TO_MOVE -> hideSelection(selector.getFrom());
        }
    }

    /**
     * Highlights the square at the specified position to indicate selection.
     *
     * @param position the {@code Position} of the square to be highlighted
     */
    private void showSelection(Position position) {
        var square = getSquare(position);
        square.getStyleClass().add("selected");
    }


    /**
     * Removes the highlight from the square at the specified position.
     *
     * @param position the {@code Position} of the square to be unhighlighted
     */
    private void hideSelection(Position position) {
        var square = getSquare(position);
        square.getStyleClass().remove("selected");
    }


    /**
     * Retrieves the square at the specified position on the game board.
     *
     * @param position the {@code Position} of the square
     * @return the {@code StackPane} representing the square
     * @throws AssertionError if the square is not found at the specified position
     */

    private StackPane getSquare(Position position) {
        for (var child : board.getChildren()) {
            if (GridPane.getRowIndex(child) == position.row() && GridPane.getColumnIndex(child) == position.col()) {
                return (StackPane) child;
            }
        }
        throw new AssertionError();
    }

    /**
     * Checks if the game is completed and shows a completion alert if it is.
     */
    private void handleGameCompletion() {
        if (model.isSolved()) {
            showGameCompletionAlert();
        }
    }

    /**
     * Displays an alert indicating that the game is completed.
     */
    private void showGameCompletionAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("Congratulations! You've solved the puzzle.");

        alert.showAndWait();
    }
}
