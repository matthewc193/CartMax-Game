package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.Player;
import seng201.team0.game.GameEnvironment;
import seng201.team0.towers.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoundResultsScreenController {
    @FXML
    private Label filledCartsLabel;
    @FXML
    private Label brokenTowersLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Label moneyLabel;

    private GameEnvironment gameEnvironment;
    private Player player;
    private List<Tower> brokenTowers;
    private boolean isWin;

    public RoundResultsScreenController(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
        this.isWin = false;
    }

    public void initialize() {
        this.brokenTowers = new ArrayList<Tower>();
        displayFilledCarts();
        randomTowerBreak();
        displayBrokenTowers();
        displayMessage();
        displayMoney();
    }

    private void displayFilledCarts() {
        filledCartsLabel.setText("5/5");
    }

    private void randomTowerBreak() {
        List<Tower> usedTowers = player.getSelectedTowers();
        Random random = new Random();
        for (Tower tower : usedTowers) {
            if (random.nextDouble() < 0.05) {
                brokenTowers.add(tower);
                player.removeTower(tower);
            }
        }
    }

    private void displayBrokenTowers() {
        StringBuilder brokenTowersText = new StringBuilder();
        if (brokenTowers.isEmpty()) {
            brokenTowersLabel.setText("None!");
        } else {
            for (Tower brokenTower : brokenTowers) {
                brokenTowersText.append(brokenTower.getTowerName()).append("\n");
            }
        }
        brokenTowersLabel.setText(brokenTowersText.toString());
    }

    private void displayMessage() {
        if (isWin) {
            messageLabel.setText("Congratulations! You have successfully filled all carts.");
        } else {
            messageLabel.setText("Sorry! You could not fill all the carts.");
        }
    }

    private void displayMoney() {
        if (isWin) {
            player.increaseMoney(100);
        }
        moneyLabel.setText("Money: " + String.valueOf(player.getMoney()));
    }

    @FXML
    private void onContinueClicked() {
        gameEnvironment.launchMainScreen();
    }
}