package puzzle.model;

import puzzle.model.PuzzleState;
import puzzle.solver.BreadthFirstSearch;
import puzzle.solver.Node;

import java.util.Optional;

/**
 * The {@code SolveConnector} class provides functionality to solve a puzzle using the Breadth-First Search algorithm.
 * It integrates with the {@link puzzle.solver.BreadthFirstSearch} class and handles the solution process.
 *
 * @see puzzle.solver.BreadthFirstSearch
 * @see puzzle.solver.Node
 */
public class SolveConnector {

    private final BreadthFirstSearch<String> solver;

    /**
     * Constructs a new {@code SolveConnector} with a Breadth-First Search solver.
     */
    public SolveConnector() {
        this.solver = new BreadthFirstSearch<>();
    }

    /**
     * Solves the puzzle given an initial state.
     *
     * @param initialState the initial state of the puzzle
     * @return an {@code Optional} containing the solution node if a solution is found, or an empty {@code Optional} if no solution is found
     */
    public Optional<Node<String>> solvePuzzle(PuzzleState initialState) {
        return solver.solve(initialState);
    }

    /**
     * Prints the solution to the puzzle.
     *
     * @param solution an {@code Optional} containing the solution node
     */
    public void printSolution(Optional<Node<String>> solution) {
        solution.ifPresentOrElse(
                node -> {
                    System.out.println("Solution:");
                    node.getParent().ifPresent(this::printPathTo);
                    System.out.println(node.getState());
                },
                () -> System.out.println("No solution found")
        );
    }

    /**
     * Recursively prints the path to the solution node.
     *
     * @param node the current node in the path
     */
    private void printPathTo(Node<String> node) {
        node.getParent().ifPresent(this::printPathTo);
        System.out.println(node.getMove().orElse("") + " -> " + node.getState());
    }

    public static void main(String[] args) {
        System.out.println("Start!!!");
        PuzzleState initialState = new PuzzleState();

        SolveConnector solveConnector = new SolveConnector();

        Optional<Node<String>> solution = solveConnector.solvePuzzle(initialState);

        solveConnector.printSolution(solution);
    }
}
