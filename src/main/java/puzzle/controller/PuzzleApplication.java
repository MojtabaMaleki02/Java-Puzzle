package puzzle.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The {@code PuzzleApplication} class serves as the entry point for the JavaFX board game application.
 * It extends the {@link javafx.application.Application} class and sets up the main application window.
 *
 * <p>This class loads the FXML file to create the user interface and initializes the primary stage with
 * a fixed-size scene.</p>
 *
 * @see javafx.application.Application
 * @see javafx.stage.Stage
 * @see javafx.scene.Scene
 */
public class PuzzleApplication extends Application {

    /**
     * Starts the JavaFX application by setting up the primary stage.
     *
     * <p>This method loads the FXML file located at {@code /ui.fxml} to create the root node of the scene
     * graph. It sets the title of the stage to "JavaFX Board Game Example", makes the stage non-resizable,
     * and displays the stage.</p>
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set
     * @throws IOException if an I/O error occurs during loading the FXML file
     */

    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ui.fxml"));
        stage.setTitle("JavaFX Board Game Example");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
