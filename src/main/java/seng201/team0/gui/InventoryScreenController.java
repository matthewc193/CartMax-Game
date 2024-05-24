package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.game.Player;
import seng201.team0.game.GameEnvironment;
import seng201.team0.towers.Tower;

import java.util.Arrays;
import java.util.List;

/**
 * Controller for inventoryScreen.fxml
 */
public class InventoryScreenController {
    @FXML
    private Button tower1Button;
    @FXML
    private Button tower2Button;
    @FXML
    private Button tower3Button;
    @FXML
    private Button tower4Button;
    @FXML
    private Button tower5Button;
    @FXML
    private Button tower6Button;
    @FXML
    private Label tower1StatusLabel;
    @FXML
    private Label tower2StatusLabel;
    @FXML
    private Label tower3StatusLabel;
    @FXML
    private Label tower4StatusLabel;
    @FXML
    private Label tower5StatusLabel;
    @FXML
    private Label tower6StatusLabel;
    @FXML
    private Label resourceAmountLabel;
    @FXML
    private Label reloadSpeedLabel;
    @FXML
    private Label resourceTypeLabel;
    @FXML
    private Label costLabel;
    @FXML
    private Button selectButton;
    @FXML
    private Button reserveButton;
    private final GameEnvironment gameEnvironment;
    private Player player;
    private Tower currentTower;
    private List<Button> towerButtons;
    private List<Label> towerStatusLabels;

    /**
     * Constructor sets gameEnvironment and player
     * @param gameEnvironment contains current game information
     */
    public InventoryScreenController(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
    }

    /**
     * Initialiser sets towerButtons and towerStatusLabels with the 6 buttons and 6 labels respectively,
     * displays tower information on each of the buttons,
     * sets the current tower to the first tower in the player's inventory and displays its stats,
     * updates the current tower when a tower button is clicked and displays corresponding stats, and
     * changes the status of the current tower when the select or reserve button is clicked
     */
    @FXML
    public void initialize() {
        towerButtons = Arrays.asList(tower1Button, tower2Button, tower3Button, tower4Button, tower5Button, tower6Button);
        towerStatusLabels = Arrays.asList(tower1StatusLabel, tower2StatusLabel, tower3StatusLabel, tower4StatusLabel, tower5StatusLabel, tower6StatusLabel);
        displayTowers();
        displayTowerStats(player.getTowers().get(0)); // First tower on the list is set as current initially
        towerButtons.get(0).setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
        for (int i = 0; i < player.getTowers().size(); i++) {
            int finalI = i;
            towerButtons.get(i).setOnAction(event -> {
                displayTowerStats(player.getTowers().get(finalI));
                resetTowerStyle();
                towerButtons.get(finalI).setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
            });
        }
        selectButton.setOnAction(event -> selectTower());
        reserveButton.setOnAction(event -> reserveTower());
    }

    /**
     * Displays current player owned towers and their status as "selected" or "reserved"
     */
    private void displayTowers() {
        List<Tower> towers = player.getTowers();
        for (int i = 0; i < towerButtons.size(); i++) {
            if (i < towers.size()) {
                Tower tower = towers.get(i);
                towerButtons.get(i).setText(tower.getTowerName());
                towerStatusLabels.get(i).setText(tower.getStatus());
            } else {
                towerButtons.get(i).setText("");
                towerStatusLabels.get(i).setText("");
                towerButtons.get(i).setDisable(true);
            }
        }
    }

    /**
     * Sets the tower clicked by the player as current and displays its stats
     * @param tower the tower clicked by the player
     */
    private void displayTowerStats(Tower tower) {
        currentTower = tower;
        resourceAmountLabel.setText("Resource amount:\n" + tower.getResourceAmount());
        reloadSpeedLabel.setText("Reload speed:\n" + tower.getReloadSpeed());
        resourceTypeLabel.setText("Resource type:\n" + tower.getResourceType());
        costLabel.setText("Cost:\n" + tower.getCost());
    }

    /**
     * Resets the colour of the available buttons
     */
    private void resetTowerStyle() {
        for (int i = 0; i < player.getTowers().size(); i++) {
            towerButtons.get(i).setStyle("");
        }
    }

    /**
     * Changes the status of the current tower as "selected" if current status is "reserved"
     */
    private void selectTower() {
        if (currentTower != null && currentTower.getStatus().equals("reserved")) {
            int length = player.getSelectedTowers().size();
            if (length < 5) {
                currentTower.setStatus("selected");
                displayTowers();
            } else {
                showAlert("You can't select more than 5 towers at once.");
            }
        }
    }

    /**
     * Changes the status of the current tower as "reserved" if current status is "selected"
     */
    private void reserveTower() {
        if (currentTower != null && currentTower.getStatus().equals("selected")) {
            int length = player.getTowers().size() - player.getSelectedTowers().size();
            if (length < 5) {
                currentTower.setStatus("reserved");
                displayTowers();
            } else {
                showAlert("You can't reserve more than 5 towers at once.");
            }
        }
    }

    /**
     * Displays a pop up to alert the player
     * @param message the error message
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Closes the inventory screen and redirects back to main screen
     */
    @FXML
    private void onCloseClicked() {
        gameEnvironment.launchMainScreen();
    }
}
