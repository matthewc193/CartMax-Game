package seng201.team0.gui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.game.Player;
import seng201.team0.game.GameEnvironment;

import java.io.IOException;

/**
 * This class is responsible for launching screens.
 */
public class FXWrapper {
    @FXML
    private Pane pane;

    private Stage stage;

    Player player = new Player();

    /**
     * initiated at the start of the game and creates the gameEnvironment for the game.
     * @param stage
     */
    public void init(Stage stage) {
        this.stage = stage;
        new GameEnvironment(this::launchSetupScreen, this::launchMainScreen, this::launchInGameScreen, this::launchShopScreen, this::launchInventoryScreen, this::launchRoundResultsScreen, this::launchGameClearScreen, this::launchGameOverScreen, this::clearPane);
    }

    /**
     * Launches SetupScreen
     * @param gameEnvironment
     */
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

    /**
     * Clears current contents of the pane before a new screen in displayed.
     */
    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }

    /**
     * Launches MainScreen
     * @param gameEnvironment
     */
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

    /**
     * Launches InGameScreen
     * @param gameEnvironment
     */
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
    /**
     * Launches ShopScreen
     * @param gameEnvironment
     */
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
    /**
     * Launches InventoryScreen
     * @param gameEnvironment
     */
    public void launchInventoryScreen(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader inventoryScreenLoader = new FXMLLoader(getClass().getResource("/fxml/inventoryScreen.fxml"));
            inventoryScreenLoader.setControllerFactory(param -> new InventoryScreenController(gameEnvironment));
            Parent setupParent  = inventoryScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Inventory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Launches RoundResultScreen
     * @param gameEnvironment
     */
    public void launchRoundResultsScreen(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader roundResultsScreenLoader = new FXMLLoader(getClass().getResource("/fxml/roundResultsScreen.fxml"));
            roundResultsScreenLoader.setControllerFactory(param -> new RoundResultsScreenController(gameEnvironment));
            Parent setupParent = roundResultsScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Results");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Launches GameClearScreen
     * @param gameEnvironment
     */
    public void launchGameClearScreen(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader gameClearScreenLoader = new FXMLLoader(getClass().getResource("/fxml/gameClearScreen.fxml"));
            gameClearScreenLoader.setControllerFactory(param -> new GameClearScreenController(gameEnvironment));
            Parent setupParent = gameClearScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Clear!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Launches GameOverScreen
     * @param gameEnvironment
     */
    public void launchGameOverScreen(GameEnvironment gameEnvironment) {
        try {
            FXMLLoader gameOverScreenLoader = new FXMLLoader(getClass().getResource("/fxml/gameOverScreen.fxml"));
            gameOverScreenLoader.setControllerFactory(param -> new GameOverScreenController(gameEnvironment));
            Parent setupParent = gameOverScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Game Over");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}