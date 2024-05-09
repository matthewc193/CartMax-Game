package seng201.team0.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class starts the javaFX application window
 */
public class FXWindow extends Application {
    /**
     * Opens the gui with the fxml content specified in resources/fxml/main.fxml
     * @param primaryStage The current fxml stage, handled by javaFX Application class
     * @throws IOException if there is an issue loading fxml file
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/FXwrapper.fxml"));
        Parent root = baseLoader.load();
        FXWrapper fxWrapper = baseLoader.getController();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("FX Wrapper");
        primaryStage.setScene(scene);
        primaryStage.show();
        fxWrapper.init(primaryStage);
    }

    /**
     * Launches the FXML application, this must be called from another class (in this cass App.java) otherwise JavaFX
     * errors out and does not run
     * @param args command line arguments
     */
    public static void launchWrapper(String[] args) {
        launch(args);
    }

}