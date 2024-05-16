package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.Player;
import seng201.team0.game.GameEnvironment;
import seng201.team0.items.Item;
import seng201.team0.towers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopScreenController {
    /**
     * FXML imports
     */
    @FXML
    private Label shopLabel;
    @FXML
    private Label towersLabel;
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
    private Label itemsLabel;
    @FXML
    private Button item1Button;
    @FXML
    private Button item2Button;
    @FXML
    private Button item3Button;
    @FXML
    private Label productStatsLabel;
    @FXML
    private Label productResourceAmountLabel;
    @FXML
    private Label productReloadSpeedLabel;
    @FXML
    private Label productResourceTypeLabel;
    @FXML
    private Label productCostLabel;
    @FXML
    private Button buyButton;
    @FXML
    private Button sellButton;
    @FXML
    private Button backButton;

    private GameEnvironment gameEnvironment;
    private Tower currentTower;
    private Item currentItem;

    public ShopScreenController(GameEnvironment gameEnvironment) {this.gameEnvironment = gameEnvironment;}
    public void initialize() {
        displayTowers();
    }

    public void displayTowers() {
        List<Tower> towerTypes = new ArrayList<>(Arrays.asList(new woodOne(), new woodTwo(), new clayOne(), new clayTwo(), new stoneOne(), new stoneTwo()));

    }

    @FXML
    private void buyTower() {
        if (gameEnvironment.getPlayer().getMoney() >= currentTower.getCost()) {
            gameEnvironment.getPlayer().decreaseMoney(currentTower.getCost());
            gameEnvironment.getPlayer().addTower(currentTower);
        } else {
            System.out.println("You don't have enough money to buy this tower.");
        }
    }

    @FXML
    private void sellTower() {
        if (gameEnvironment.getPlayer().getStartTowers().contains(currentTower.getCost())) {
            gameEnvironment.getPlayer().increaseMoney(currentTower.getCost());
            gameEnvironment.getPlayer().removeTower(currentTower);
        } else {
            System.out.println("You don't own this tower.");
        }
    }

    @FXML
    private void buyItem() {
        if (gameEnvironment.getPlayer().getMoney() >= currentItem.getCost()) {
            gameEnvironment.getPlayer().increaseMoney(currentItem.getCost());
            gameEnvironment.getPlayer().addItem(currentItem);
        }
    }

    @FXML
    private void onBackClicked() {
        gameEnvironment.closeShopScreen();
    }
}
