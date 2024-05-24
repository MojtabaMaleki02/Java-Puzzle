package puzzle.model;

import javafx.application.Application;
import puzzle.controller.PuzzleApplication;

import java.util.Scanner;
import java.util.Set;


/**
 * The {@code Main} class serves as the entry point for launching the JavaFX puzzle application.
 * It contains the main method which launches the {@link PuzzleApplication}.
 *
 * @see javafx.application.Application
 * @see puzzle.controller.PuzzleApplication
 */

public class Main {

    /**
     * The main method, which launches the JavaFX puzzle application.
     *
     * <p>This method uses the {@link javafx.application.Application#launch(Class, String...)} method
     * to launch the {@link PuzzleApplication} class.</p>
     *
     * @param args the command-line arguments passed to the program
     */
    public static void main(String[] args) {

        Application.launch(PuzzleApplication.class, args);

    }
}
