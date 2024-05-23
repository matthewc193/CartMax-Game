package seng201.team0.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class starts the javaFX application window
 * @author seng201 teaching team
 */
public class MainWindow extends Application {

    /**
     * Opens the gui with the fxml content starting with setUpScreen
     * @param primaryStage
     * @throws IOException
     */
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/setUpScreen.fxml"));
        Parent root = baseLoader.load();

        SetUpScreenController baseController = baseLoader.getController();
        baseController.init(primaryStage);

        primaryStage.setTitle("CartMax Set Up Screen");
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Launches the FXML application, this must be called from another class (in this cass App.java) otherwise JavaFX
     * errors out and does not run
     * @param args command line arguments
     */
    public static void launchWrapper(String [] args) {
        launch(args);
    }

}
