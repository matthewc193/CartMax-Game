package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.game.GameEnvironment;

import java.io.IOException;

public class FXWrapper {
    @FXML
    private Pane pane;

    private Stage stage;

    public void init(Stage stage) {
        this.stage = stage;
        new GameEnvironment(this::launchSetupScreen, this::launchMainScreen, this::clearPane);
    }

    public void launchSetupScreen(GameEnvironment gameEnvironment) {
        System.out.println("here");
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass().getResource("/fxml/setUpScreen.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param -> new SetUpScreenController(gameEnvironment));
            Parent setupParent  = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("CartMax Setup");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }

    public void launchMainScreen(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/main_screen.fxml"));
            mainScreenLoader.setControllerFactory(param -> new MainMenuController(gameEnvironment));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Rocket Manager Main Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}