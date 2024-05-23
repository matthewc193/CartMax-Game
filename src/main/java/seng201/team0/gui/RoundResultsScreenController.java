package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.game.Player;
import seng201.team0.game.GameEnvironment;
import seng201.team0.game.Round;
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
    private Round prevRound;

    public RoundResultsScreenController(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
        this.prevRound = gameEnvironment.getPrevRound();
    }

    public void initialize() {
        displayBrokenTowers(prevRound.getBrokenTower());
        displayFilledCarts();
        displayMessage();
        displayMoney();
    }

    private void displayFilledCarts() {
        int filledCarts = prevRound.determineNumberOfCarts() - prevRound.getCurrentCarts().size();
        int totalCarts = prevRound.determineNumberOfCarts();
        filledCartsLabel.setText(filledCarts + " / " + totalCarts);
    }

    private void displayBrokenTowers(Tower brokenTower) {
        StringBuilder brokenTowersText = new StringBuilder();
        if (brokenTower == null) {
            brokenTowersLabel.setText("None!");
        } else {
                brokenTowersText.append(brokenTower.getTowerName()).append("\n");
                System.out.println(brokenTower.getTowerName());
                brokenTowersLabel.setText(brokenTower.getTowerName());
        }
    }

    private void displayMessage() {
        if (prevRound.isRoundComplete()) {
            messageLabel.setText("Congratulations! You have successfully filled all carts.");
        } else {
            messageLabel.setText("Sorry! You could not fill all the carts.");
        }
    }

    private void displayMoney() {
        if (prevRound.isRoundComplete()) {
            switch(gameEnvironment.getDifficulty()) {
                case "Easy":
                    player.increaseMoney(200 + 10 * gameEnvironment.getCurrentRoundNumber());
                    break;
                case "Medium":
                    player.increaseMoney(200 + 25 * gameEnvironment.getCurrentRoundNumber());
                    break;
                case "Hard":
                    player.increaseMoney(200 + 50 * gameEnvironment.getCurrentRoundNumber());
                }
            }
        moneyLabel.setText("Money: " + player.getMoney());
    }

    @FXML
    private void onContinueClicked() {
        if (prevRound.isRoundComplete()) {
            if (gameEnvironment.getCurrentRoundNumber() == gameEnvironment.getTotalRounds()) {
                gameEnvironment.launchGameClearScreen();
            } else {
                gameEnvironment.launchMainScreen();
            }
        } else {
            gameEnvironment.launchGameOverScreen();
        }
    }
}
