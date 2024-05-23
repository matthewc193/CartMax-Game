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

/**
 * Controller for roundResultsScreen.fxml
 */
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

    /**
     * Constructor sets gameEnvironment and player
     * @param gameEnvironment contains current game information
     */
    public RoundResultsScreenController(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
        this.prevRound = gameEnvironment.getPrevRound();
    }

    /**
     * Initialiser displays broken towers if any,
     * displays how many carts were filled during the round,
     * message depending on round results, and
     * the amount of money after the round
     */
    public void initialize() {
        displayBrokenTowers(prevRound.getBrokenTower());
        displayFilledCarts();
        displayMessage();
        displayMoney();
    }

    /**
     * Displays how many carts were filled versus total number of carts
     */
    private void displayFilledCarts() {
        int filledCarts = prevRound.determineNumberOfCarts() - prevRound.getCurrentCarts().size();
        int totalCarts = prevRound.determineNumberOfCarts();
        filledCartsLabel.setText(filledCarts + " / " + totalCarts);
    }

    /**
     * Displays the broken tower
     * @param brokenTower the tower broken by random chance,
     *                    null if none broken
     */
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

    /**
     * Displays message based on round results
     */
    private void displayMessage() {
        if (prevRound.isRoundComplete()) {
            messageLabel.setText("Congratulations! You have successfully filled all carts.");
        } else {
            messageLabel.setText("Sorry! You could not fill all the carts.");
        }
    }

    /**
     * Increases player balance if the round was won, based on difficulty and round count
     */
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

    /**
     * Closes the inventory screen and if the round was won redirects back to main screen,
     * unless the final round was played, which in that case opens game clear screen,
     * else opens game over screen
     */
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
