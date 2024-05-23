package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.game.Player;
import seng201.team0.game.GameEnvironment;

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

    public GameClearScreenController(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
    }

    public void initialize() {
        displayMessage();
        displayResults();
    }

    private void displayMessage() {
        messageLabel.setText("Congratulations " + player.getName() + "!");
    }

    private void displayResults() {
        difficultyLabel.setText(gameEnvironment.getDifficulty());
        numTowersLabel.setText(String.valueOf(player.getTowers().size()));
        moneyLabel.setText(String.valueOf(player.getMoney()));
    }

    @FXML
    private void onFinishClicked() {
        gameEnvironment.quitGame();
    }
}
