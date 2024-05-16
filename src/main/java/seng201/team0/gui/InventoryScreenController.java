package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.Player;
import seng201.team0.game.GameEnvironment;
import seng201.team0.towers.Tower;

import java.util.ArrayList;

public class InventoryScreenController {
    /**
     * FXML imports
     */
    @FXML
    private Label inventoryLabel;
    @FXML
    private Label selectedTowersLabel;
    @FXML
    private Button selectedTower1Button;
    @FXML
    private Button selectedTower2Button;
    @FXML
    private Button selectedTower3Button;
    @FXML
    private Button selectedTower4Button;
    @FXML
    private Button selectedTower5Button;
    @FXML
    private Label reservedTowersLabel;
    @FXML
    private Button reservedTower1Button;
    @FXML
    private Button reservedTower2Button;
    @FXML
    private Button reservedTower3Button;
    @FXML
    private Button reservedTower4Button;
    @FXML
    private Button reservedTower5Button;
    @FXML
    private Label towerStatsLabel;
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
    @FXML
    private Button confirmButton;

    public Player player;
    private Tower currentTower;
    private GameEnvironment gameEnvironment;

    public InventoryScreenController(Player player) {
        this.player = player;
    }

    public void initialize() {
        displayTowers();

        selectedTower1Button.setOnAction(event -> displayTowerStats(player.getStartTowers().get(0)));
        selectedTower2Button.setOnAction(event -> displayTowerStats(player.getStartTowers().get(1)));
        selectedTower3Button.setOnAction(event -> displayTowerStats(player.getStartTowers().get(2)));
        selectedTower4Button.setOnAction(event -> displayTowerStats(player.getStartTowers().get(3)));
        selectedTower5Button.setOnAction(event -> displayTowerStats(player.getStartTowers().get(4)));
        reservedTower1Button.setOnAction(event -> displayTowerStats(player.getStartTowers().get(5)));
        reservedTower2Button.setOnAction(event -> displayTowerStats(player.getStartTowers().get(6)));
        reservedTower3Button.setOnAction(event -> displayTowerStats(player.getStartTowers().get(7)));
        reservedTower4Button.setOnAction(event -> displayTowerStats(player.getStartTowers().get(8)));
        reservedTower5Button.setOnAction(event -> displayTowerStats(player.getStartTowers().get(9)));

        selectButton.setOnAction(event -> {
            if (currentTower != null && !currentTower.getIsSelected()) {
                int count = 0;
                for (Tower tower : player.getStartTowers()) {
                    if (tower.getIsSelected()) {
                        count++;
                    }
                }
                if (count < 5) {
                    currentTower.setIsSelected(true);;
                    displayTowers();
                } else {
                    showAlert("You can't select more than 5 towers at once.");
                }
            }
        });

        reserveButton.setOnAction(event -> {
            if (currentTower != null && currentTower.getIsSelected()) {
                int count = 0;
                for (Tower tower : player.getStartTowers()) {
                    if (!tower.getIsSelected()) {
                        count++;
                    }
                }
                if (count < 5) {
                    currentTower.setIsSelected(false);
                    displayTowers();
                } else {
                    showAlert("You can't reserve more than 5 towers at once.");
                }
            }
        });
    }

    private void displayTowerStats(Tower tower) {
        resourceAmountLabel.setText("Resource amount: " + tower.getResourceAmount());
        reloadSpeedLabel.setText("Reload speed: " + tower.getReloadSpeed());
        resourceTypeLabel.setText("Resource type: " + tower.getResourceType());
        costLabel.setText("Cost: " + tower.getCost());
    }

    private void displayTowers() {
        ArrayList<Tower> ownedTowers = player.getStartTowers();
        for (int i = 0; i < ownedTowers.size(); i++) {
            Tower tower = ownedTowers.get(i);
            Button towerButton;
            if (i < 5) {
                switch (i) {
                    case 0:
                        towerButton = selectedTower1Button;
                        break;
                    case 1:
                        towerButton = selectedTower2Button;
                        break;
                    case 2:
                        towerButton = selectedTower3Button;
                        break;
                    case 3:
                        towerButton = selectedTower4Button;
                        break;
                    case 4:
                        towerButton = selectedTower5Button;
                        break;
                    default:
                        continue;
                }
            } else {
                switch (i - 5) {
                    case 0:
                        towerButton = reservedTower1Button;
                        break;
                    case 1:
                        towerButton = reservedTower2Button;
                        break;
                    case 2:
                        towerButton = reservedTower3Button;
                        break;
                    case 3:
                        towerButton = reservedTower4Button;
                        break;
                    case 4:
                        towerButton = reservedTower5Button;
                        break;
                    default:
                        continue;
                }
            }
            if (i < ownedTowers.size()) {
                towerButton.setText(tower.getTowerName());
                towerButton.setDisable(false);
            } else {
                towerButton.setText("");
                towerButton.setDisable(true);
            }
        }
        selectedTower1Button.setOnAction(event -> setCurrentTower(0));
        selectedTower2Button.setOnAction(event -> setCurrentTower(1));
        selectedTower3Button.setOnAction(event -> setCurrentTower(2));
        selectedTower4Button.setOnAction(event -> setCurrentTower(3));
        selectedTower5Button.setOnAction(event -> setCurrentTower(4));
        reservedTower1Button.setOnAction(event -> setCurrentTower(5));
        reservedTower2Button.setOnAction(event -> setCurrentTower(6));
        reservedTower3Button.setOnAction(event -> setCurrentTower(7));
        reservedTower4Button.setOnAction(event -> setCurrentTower(8));
        reservedTower5Button.setOnAction(event -> setCurrentTower(9));
    }

    private void setCurrentTower(int index) {
        ArrayList<Tower> ownedTowers = player.getStartTowers();
        if (index >= 0 && index < ownedTowers.size()) {
            currentTower = ownedTowers.get(index);
            displayTowerStats(currentTower);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onConfirmClicked() {
        gameEnvironment.closeInventoryScreen();
    }
}
