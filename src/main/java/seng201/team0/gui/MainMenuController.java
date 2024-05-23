package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team0.game.Player;
import seng201.team0.game.GameEnvironment;
import javafx.scene.control.*;


/**
 * Controller for the MainMenu Screen
 */
public class MainMenuController {
    @FXML
    public Label roundsLabel;
    @FXML
    public Label playerNameLabel;
    @FXML
    public Button nextRoundButton;
    @FXML
    public Button changeInventoryButton;
    @FXML
    public Button shopButton;
    @FXML
    public Label playerMoneyLabel;

    private Player player;

    private GameEnvironment gameEnvironment;

    /**
     * Constructor method sets gameEnvironment and player.
     * @param gameEnvironment
     */
    public MainMenuController(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
    }
    /**
     * Initialize method sets up button and animations for the mainMenu.
     */
    public void initialize(){
        playerMoneyLabel.setText(String.valueOf(player.getMoney())); // Displays the players money

        nextRoundButton.setOnAction(event -> {
            onNextRoundClicked(); //Action for when continue button in clicked
        });

        changeInventoryButton.setOnAction(actionEvent -> {
            onInventoryButtonClicked(); // Sets up the inventory button
        });

        shopButton.setOnAction(actionEvent -> {
            onShopButtonClicked(); // Sets up the shop button
        });

        playerNameLabel.setText(gameEnvironment.getPlayer().getName()); // Displays the player's name
        roundsLabel.setText("Round " + gameEnvironment.getCurrentRoundNumber() + " of " + gameEnvironment.getTotalRounds()); // Displays the current round number
    }

    /**
     * launch the inventory when inventory button is clickes
     */
    private void onInventoryButtonClicked() {
        gameEnvironment.launchInventoryScreen();
    }

    /**
     * Launches the shop when the shop button is clicked
     */
    private void onShopButtonClicked() {
        gameEnvironment.launchShopScreen();
    }

    /**
     * Launches the in gameScreen when next round is clicked
     */
    public void onNextRoundClicked(){
        gameEnvironment.launchInGameScreen();
    }


}
