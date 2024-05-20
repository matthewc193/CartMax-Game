package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.Player;
import seng201.team0.game.GameEnvironment;
import seng201.team0.towers.Tower;

import java.util.List;

public class RoundResultsController {
    @FXML
    private Label filledCartsLabel;
    @FXML
    private Label brokenTowersLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Button continueButton;

    private GameEnvironment gameEnvironment;
    private Player player;
    private List<Tower> brokenTowers;

    public RoundResultsController(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
    }

    public void initialize() {
        randomTowerBreak();
    }

    private void randomTowerBreak() {
        List<Tower> ownedTowers = player.getTowers();

    }
}
