package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.Player;
import seng201.team0.game.GameEnvironment;

public class GameOverScreenController {
    @FXML
    private Label messageLabel;
    @FXML
    private Label difficultyLabel;
    @FXML
    private Label numRoundsLabel;

    private GameEnvironment gameEnvironment;
    private Player player;

    public GameOverScreenController(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
    }

    public void initialize() {
        displayMessage();
        displayResults();
    }

    private void displayMessage() {
        messageLabel.setText("Better luck next time " + player.getName() + "!");
    }

    private void displayResults() {
        difficultyLabel.setText(gameEnvironment.getDifficulty());
        numRoundsLabel.setText(String.valueOf(gameEnvironment.getCurrentRoundNumber()));
    }

    @FXML
    private void onFinishClicked() {
        gameEnvironment.quitGame();
    }
}
