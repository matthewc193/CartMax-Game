package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.game.Player;
import seng201.team0.game.GameEnvironment;

/**
 * Controller for gameOverScreen.fxml
 */
public class GameOverScreenController {
    @FXML
    private Label messageLabel;
    @FXML
    private Label difficultyLabel;
    @FXML
    private Label numRoundsLabel;
    private GameEnvironment gameEnvironment;
    private Player player;

    /**
     * Constructor sets gameEnvironment and player
     * @param gameEnvironment contains current game information
     */
    public GameOverScreenController(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
    }

    /**
     * Initialiser displays personal message and game results
     */
    public void initialize() {
        displayMessage();
        displayResults();
    }

    /**
     * Displays message with player name
     */
    private void displayMessage() {
        messageLabel.setText("Better luck next time " + player.getName() + "!");
    }

    /**
     * Displays played difficulty and umber of rounds cleared
     */
    private void displayResults() {
        difficultyLabel.setText(gameEnvironment.getDifficulty());
        numRoundsLabel.setText(String.valueOf(gameEnvironment.getCurrentRoundNumber() - 1));
    }

    /**
     * Closes the application when the finish button is clicked
     */
    @FXML
    private void onFinishClicked() {
        gameEnvironment.quitGame();
    }
}
