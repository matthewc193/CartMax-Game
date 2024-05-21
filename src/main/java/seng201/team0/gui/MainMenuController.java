package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import seng201.team0.Player;
import seng201.team0.game.GameEnvironment;
import javafx.scene.control.*;
import seng201.team0.towers.Tower;


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
    public MainMenuController(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
    }

    public void initialize(){
        playerMoneyLabel.setText(String.valueOf(player.getMoney()));


        //Action for when continue button in clicked
        nextRoundButton.setOnAction(event -> {
            onNextRoundClicked();
        });

        changeInventoryButton.setOnAction(actionEvent -> {
            onInventoryButtonClicked();
        });

        shopButton.setOnAction(actionEvent -> {
            onShopButtonClicked();
        });

        playerNameLabel.setText(gameEnvironment.getPlayer().getName());
        roundsLabel.setText("Round " + gameEnvironment.getCurrentRoundNumber() + " of " + gameEnvironment.getTotalRounds());
    }

    private void onInventoryButtonClicked() {
        gameEnvironment.launchInventoryScreen();
    }

    private void onShopButtonClicked() {
        gameEnvironment.launchShopScreen();
    }

    public void onNextRoundClicked(){
        gameEnvironment.launchInGameScreen();
    }


}
