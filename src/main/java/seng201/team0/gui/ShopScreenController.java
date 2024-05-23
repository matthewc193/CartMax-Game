package seng201.team0.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import seng201.team0.game.Player;
import seng201.team0.game.GameEnvironment;
import seng201.team0.towers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller for the Shop Screen.
 * Handles tasks such as selling, buying
 * and upgrading towers.
 */
public class ShopScreenController {
    private GameEnvironment gameEnvironment;
    private Player player;

    /**
     * Creates new player
     * @param stage
     */
    public void init(Stage stage) {
        player = new Player();
    }

    /**
     * Constructor method sets the player
     * and game environment.
     * @param gameEnvironment
     */
    public ShopScreenController(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
    }

    // FXML injections
    @FXML
    public Button woodOneShop;
    @FXML
    public Button woodTwoShop;
    @FXML
    public Button clayOneShop;
    @FXML
    public Button clayTwoShop;
    @FXML
    public Button stoneOneShop;
    @FXML
    public Button stoneTwoShop;
    @FXML
    public Label displayResourceAmount;
    @FXML
    public Label displayReloadSpeed;
    @FXML
    public Label welcomeText;
    @FXML
    public Button backToMainMenuButton;
    @FXML
    public Label resourceUpgradeLabel;
    @FXML
    public Label reloadSpeedUpgradeLabel;
    @FXML
    public Button upgradeResourceAmountButton;
    @FXML
    public Button upgradeReloadSpeedButton;
    @FXML
    public Label playerMoneyLabel;
    @FXML
    public Button buyOrSellButton;
    @FXML
    public Label buyOrSellPrice;
    @FXML
    public Label reloadUpgradeInvalid;
    @FXML
    public Label resourceUpgradeInvalid;
    List<Tower> towerTypes;

    /**
     * Initialize function, sets button
     * actions and tower stats
     */
    public void initialize(){
        // Takes player back to main menu
        backToMainMenuButton.setOnAction(actionEvent -> {
            onBackToMainMenuButtonClicked();
        });

        // Sets heading to welcome player
        welcomeText.setText("Welcome to the Shop, " + player.getName() + "!");

        // Used to set actions for buttons
        this.towerTypes = new ArrayList<>(Arrays.asList(new WoodOne(), new WoodTwo(), new ClayOne(), new ClayTwo(), new StoneOne(), new StoneTwo()));
        List<Button> towerShopButtons = List.of(woodOneShop, woodTwoShop, clayOneShop, clayTwoShop, stoneOneShop, stoneTwoShop);
        List<String> towerOwnedText = List.of("Wood Tower 1.0", "Wood Tower 2.0", "Clay Tower 1.0", "Clay Tower 2.0", "Stone Tower 1.0", "Stone Tower 2.0");
        List<String> towerLockedText = List.of("Wood Tower 1.0 \nLOCKED", "Wood Tower 2.0 \nLOCKED", "Clay Tower 1.0 \nLOCKED", "Clay Tower 2.0 \nLOCKED", "Stone Tower 1.0 \nLOCKED", "Stone Tower 2.0\nLOCKED");

        // Setting button text based on which towers the player owns
        for (int i = 0; i < towerTypes.size(); i++){
            int finalI = i;
            if (containsInstanceOfClass(this.player.getTowers(), towerTypes.get(finalI).getClass())){
                towerShopButtons.get(i).setText(towerOwnedText.get(i));
            }
            else{
                towerShopButtons.get(i).setText(towerLockedText.get(i));
            }
        }

        setTowerShopButtons(towerShopButtons); //Sets actions for all button so that the stats are displayed

        playerMoneyLabel.setText(String.valueOf(player.getMoney())); // Displays the player's money

    }

    /**
     * Determines if a player currently
     * owns a tower.
     * @param list
     * @param clazz
     * @return true if owned false otherwise
     */
    public static boolean containsInstanceOfClass(ArrayList<Tower> list, Class<?> clazz) {
        for (Object obj : list) {
            if (clazz.isInstance(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Find the index of an owned tower in the list of towers
     * owned by the player
     * @param list
     * @param clazz
     * @return
     */
    public int findInstanceIndex(List<?> list, Class<?> clazz) {
        for (int i = 0; i < list.size(); i++) {
            if (clazz.isInstance(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Handles Setting the tower stats if
     * the tower is owned. Called from the
     * initialize method.
     * @param playerTowersIndx
     */
    public void setStatsTowerOwned(int playerTowersIndx){
        displayResourceAmount.setText(String.valueOf(player.getTowers().get(playerTowersIndx).getResourceAmount()));
        displayReloadSpeed.setText(String.valueOf(player.getTowers().get(playerTowersIndx).getReloadSpeed()));

        buyOrSellButton.setText("Sell"); // Tower is owned thus player has option to sell
        buyOrSellPrice.setText(String.valueOf(player.getTowers().get(playerTowersIndx).getCost()));

    }

    /**
     * Sets up the upgrade buttons so that the potential
     * upgrades show next to the stats (for owned towers), when hovered over.
     *  Also sets the action for upgrade buttons.
     * @param playerTowersIndx
     */
    public void setUpgradeButtonsTowerOwned(int playerTowersIndx){
        // Shows player the potential upgrades when mouse is hovered over button
        upgradeReloadSpeedButton.setOnMouseEntered(event -> {
            reloadSpeedUpgradeLabel.setText("-0.2s");
            reloadSpeedUpgradeLabel.setStyle("-fx-text-fill: #66ff00;");
            reloadSpeedUpgradeLabel.setFont(Font.font("Bold"));
        });
        upgradeReloadSpeedButton.setOnMouseExited(event -> {
            reloadSpeedUpgradeLabel.setText("");
        });

        // Shows player the potential upgrades when mouse is hovered over button
        upgradeResourceAmountButton.setOnMouseEntered(event -> {
            resourceUpgradeLabel.setText("+2");
            resourceUpgradeLabel.setStyle("-fx-text-fill: #66ff00;");
            resourceUpgradeLabel.setFont(Font.font("Bold"));
        });
        upgradeResourceAmountButton.setOnMouseExited(event -> {
            resourceUpgradeLabel.setText("");
        });

        // Setting the action for upgrading the reload speed
        upgradeReloadSpeedButton.setOnAction(event -> {
            // Checking if player has enough money to upgrade and upgrade will not take reload to less than 0.
            if (player.getTowers().get(playerTowersIndx).upgradeReloadSpeed(this.player)){
                displayReloadSpeed.setText(String.valueOf(player.getTowers().get(playerTowersIndx).getReloadSpeed()));
                playerMoneyLabel.setText(String.valueOf(player.getMoney()));
            }
            else{
                // Flashes "Upgrade InValid!" label if player does not have enough money
                reloadUpgradeInvalid.setText("Upgrade invalid!");
                reloadUpgradeInvalid.setStyle("-fx-text-fill: red");

                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.seconds(2),
                        ae -> reloadUpgradeInvalid.setText("")));
                timeline.setCycleCount(1);
                timeline.play();
            }
        });

        // Setting the action for upgrading the resource amount
        upgradeResourceAmountButton.setOnAction(event -> {
            //Checking if player has enough money to upgrade
            if (player.getTowers().get(playerTowersIndx).upgradeResourceAmount(this.player)){
                displayResourceAmount.setText(String.valueOf(player.getTowers().get(playerTowersIndx).getResourceAmount()));
                playerMoneyLabel.setText(String.valueOf(player.getMoney()));
            }
            else{
                // Flashes "Upgrade InValid!" label if player does not have enough money
                resourceUpgradeInvalid.setText("Upgrade invalid!");
                resourceUpgradeInvalid.setStyle("-fx-text-fill: red");

                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.seconds(2),
                        ae -> resourceUpgradeInvalid.setText("")));
                timeline.setCycleCount(1);
                timeline.play();
            }
        });

    }


    /**
     * Handles Setting the tower stats if
     * the tower is locked. Called from the
     * initialize method.
     * @param towersIndx
     */
    public void setStatsTowerLocked(int towersIndx){
        displayResourceAmount.setText(String.valueOf(towerTypes.get(towersIndx).getResourceAmount()));
        displayReloadSpeed.setText(String.valueOf(towerTypes.get(towersIndx).getReloadSpeed()));
        buyOrSellButton.setText("Unlock"); // Tower is not owned thus player has option to buy/unlock
        buyOrSellPrice.setText(String.valueOf(towerTypes.get(towersIndx).getCost()));
    }

    /**
     * A player cannot upgrade a tower if
     * it is not owned. This sets it up so a
     * warning is display and user cannot upgrade
     * @param towersIndx
     */
    public void setUpgradeButtonsTowerLocked(int towersIndx){
        upgradeReloadSpeedButton.setOnMouseEntered(event -> {
            // Flashes "Tower Locked!" label if player does not own tower
            reloadUpgradeInvalid.setText("Tower Locked!");
            reloadUpgradeInvalid.setStyle("-fx-text-fill: red");

            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.seconds(2),
                    ae -> reloadUpgradeInvalid.setText("")));
            timeline.setCycleCount(1);
            timeline.play();
        });

        // Sets upgrade button so it doesn't do anythin
        upgradeReloadSpeedButton.setOnAction(actionEvent -> {

        });


        upgradeResourceAmountButton.setOnMouseEntered(event -> {
            // Flashes "Tower Locked!" label if player does not own tower
            resourceUpgradeInvalid.setText("Tower Locked!");
            resourceUpgradeInvalid.setStyle("-fx-text-fill: red");

            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.seconds(2),
                    ae -> resourceUpgradeInvalid.setText("")));
            timeline.setCycleCount(1);
            timeline.play();
        });

        // Sets upgrade button so it doesn't do anything
        upgradeResourceAmountButton.setOnAction(actionEvent -> {

        });

    }

    /**
     * Sets the function for the sell Button.
     * And the calls initialize() to rest the
     * stats and fires button so that the sell
     * button is changed to buy/unlock.
     * @param playerTowersIndx
     * @param towerButton
     */
    public void setSellButton(int playerTowersIndx, Button towerButton){
        buyOrSellButton.setOnAction(event -> {
            player.sellTower(playerTowersIndx);
            towerButton.fire();
            initialize(); // Update the player money and the button text to say locked
        });
    }


    /**
     * Sets the function for the buy/unlock Button.
     * And the calls initialize() to rest the
     * stats and fires button so that the buy/unlock
     * button is changed to sell.
     * @param tower
     * @param towerButton
     */
    public void setBuyButton(Tower tower, Button towerButton){
        buyOrSellButton.setOnAction(event -> {
            if (player.buyTower(tower));{
                //buyOrSellButton.setText("Sell");
                towerButton.fire();
                initialize(); // Update the player money and the button text to say locked
            }
        });
    }

    /**
     * Sets up the functionality  for the tower shop buttons
     * @param towerShopButtons
     */
    private void setTowerShopButtons(List<Button> towerShopButtons){
        for (int i = 0; i < towerShopButtons.size(); i++){
            int finalI = i;
            towerShopButtons.get(i).setOnAction(event -> {
                // Player owns tower
                if (containsInstanceOfClass(this.player.getTowers(), towerTypes.get(finalI).getClass())){
                    int playerTowersIndx = findInstanceIndex(player.getTowers(), towerTypes.get(finalI).getClass());
                    setStatsTowerOwned(playerTowersIndx); // Displays stats
                    setUpgradeButtonsTowerOwned(playerTowersIndx); // Sets action for upgrade buttons
                    setSellButton(playerTowersIndx, towerShopButtons.get(finalI)); // Sets actions for the sell button
                }
                // Player does not own tower
                else{
                    setStatsTowerLocked(finalI); // Displays stats
                    setUpgradeButtonsTowerLocked(finalI); // Sets the upgrade button for the tower
                    setBuyButton(towerTypes.get(finalI), towerShopButtons.get(finalI)); // Sets the tower buy button
                }
                towerShopButtons.get(finalI).setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                for(int j = 0; j < towerShopButtons.size(); j++){  // Setting the style of other button to default
                    if (j != finalI){
                        towerShopButtons.get(j).setStyle("");
                    }
                }
            });
        }
    }

    /**
     * Closes shop screen and takes user
     * back to the main menu.
     */
    private void onBackToMainMenuButtonClicked() {
        gameEnvironment.launchMainScreen();
    }
}
