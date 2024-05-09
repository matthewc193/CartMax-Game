package seng201.team0.gui;

import javafx.fxml.FXML;
import seng201.team0.Player;
import seng201.team0.game.GameEnvironment;

import java.awt.*;

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
    public Button inventoryShopButton;
    private Player player;
    private GameEnvironment gameEnvironment;
    public MainMenuController(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
    }

    public void initialize(){
        playerNameLabel.setText(player.getName());

    }


}
