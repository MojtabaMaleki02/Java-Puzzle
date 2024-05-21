package puzzle.model;

import puzzle.model.PuzzleState;
import puzzle.solver.BreadthFirstSearch;
import puzzle.solver.Node;

import java.util.Optional;

public class SolveConnector {

    private final BreadthFirstSearch<String> solver;

    public SolveConnector() {
        this.solver = new BreadthFirstSearch<>();
    }

    public Optional<Node<String>> solvePuzzle(PuzzleState initialState) {
        return solver.solve(initialState);
    }

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

    private void printPathTo(Node<String> node) {
        node.getParent().ifPresent(this::printPathTo);
        System.out.println(node.getMove().orElse("") + " -> " + node.getState());
    }

    public static void main(String[] args) {
        System.out.println("Start!!!");
        // Create a puzzle state
        PuzzleState initialState = new PuzzleState();

        // Create a SolveConnector instance
        SolveConnector solveConnector = new SolveConnector();

        // Solve the puzzle
        Optional<Node<String>> solution = solveConnector.solvePuzzle(initialState);

        // Print the solution
        solveConnector.printSolution(solution);
    }
}
