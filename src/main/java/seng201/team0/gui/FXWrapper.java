package seng201.team0.gui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.Player;
import seng201.team0.game.GameEnvironment;

import java.io.IOException;

public class FXWrapper {
    @FXML
    private Pane pane;

    private Stage stage;

    Player player = new Player();

    public void init(Stage stage) {
        this.stage = stage;
        new GameEnvironment(this::launchSetupScreen, this::launchMainScreen, this::launchInGameScreen, this::launchShopScreen, this::launchInventoryScreen, this::clearPane);
    }

    public void launchSetupScreen(GameEnvironment gameEnvironment) {
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
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass().getResource("/fxml/mainMenu.fxml"));
            mainScreenLoader.setControllerFactory(param -> new MainMenuController(gameEnvironment));
            Parent setupParent  = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Main Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchInGameScreen(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader inGameScreenLoader = new FXMLLoader(getClass().getResource("/fxml/inGameScreen.fxml"));
            inGameScreenLoader.setControllerFactory(param -> new InGameScreenController(gameEnvironment));
            Parent setupParent  = inGameScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Game Play");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchShopScreen(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader shopScreenLoader = new FXMLLoader(getClass().getResource("/fxml/shopScreen.fxml"));
            shopScreenLoader.setControllerFactory(param -> new ShopScreenController(gameEnvironment));
            Parent setupParent  = shopScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Shop");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchInventoryScreen(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader inventoryScreenLoader = new FXMLLoader(getClass().getResource("/fxml/inventoryScreen.fxml"));
            inventoryScreenLoader.setControllerFactory(param -> new ShopScreenController(gameEnvironment));
            Parent setupParent  = inventoryScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Inventory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}