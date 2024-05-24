package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.game.Player;
import seng201.team0.game.GameEnvironment;

/**
 * Controller for gameClearScreen.fxml
 */
public class GameClearScreenController {
    @FXML
    private Label messageLabel;
    @FXML
    private Label difficultyLabel;
    @FXML
    private Label numTowersLabel;
    @FXML
    private Label moneyLabel;
    private GameEnvironment gameEnvironment;
    private Player player;

    /**
     * Constructor sets gameEnvironment and player
     * @param gameEnvironment contains current game information
     */
    public GameClearScreenController(GameEnvironment gameEnvironment) {
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
        messageLabel.setText("Congratulations " + player.getName() + "!");
    }

    /**
     * Displays cleared difficulty and number of towers and amount of money owned at the end
     */
    private void displayResults() {
        difficultyLabel.setText(gameEnvironment.getDifficulty());
        numTowersLabel.setText(String.valueOf(player.getTowers().size()));
        moneyLabel.setText(String.valueOf(player.getMoney()));
    }

    /**
     * Closes the application when the finish button is clicked
     */
    @FXML
    private void onFinishClicked() {
        gameEnvironment.quitGame();
    }
}
