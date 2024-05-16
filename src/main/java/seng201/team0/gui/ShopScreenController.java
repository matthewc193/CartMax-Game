package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seng201.team0.Player;
import seng201.team0.game.GameEnvironment;
import seng201.team0.towers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopScreenController {
    private GameEnvironment gameEnvironment;
    private Player player;
    public void init(Stage stage) {
        player = new Player();
    }
    public ShopScreenController(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
    }

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
    public void initialize(){

        backToMainMenuButton.setOnAction(actionEvent -> {
            onBackToMainMenuButtonClicked();
        });

        welcomeText.setText("Welcome to the Shop, " + player.getName() + "!");

        List<Tower> towerTypes = new ArrayList<>(Arrays.asList(new woodOne(), new woodTwo(), new clayOne(), new clayTwo(), new stoneOne(), new stoneTwo()));
        List<Button> towerShopButtons = List.of(woodOneShop, woodTwoShop, clayOneShop, clayTwoShop, stoneOneShop, stoneTwoShop);
        List<String> towerOwnedText = List.of("Wood Tower 1.0", "Wood Tower 2.0", "Clay Tower 1.0", "Clay Tower 2.0", "Stone Tower 1.0", "Stone Tower 2.0");
        List<String> towerLockedText = List.of("Wood Tower 1.0 \nLOCKED", "Wood Tower 2.0", "Clay Tower 1.0", "Clay Tower 2.0", "Stone Tower 1.0", "Stone Tower 2.0");

        // Setting button text based on which towers the player owns
        for (int i = 0; i < towerTypes.size(); i++){
            if (containsInstanceOfClass(this.player.getTowers(), towerTypes.get(i).getClass())){
                towerShopButtons.get(i).setText(towerOwnedText.get(i));
            }
            else{
                towerShopButtons.get(i).setText(towerLockedText.get(i));
            }

        }

        //Sets actions for all button so that the stat are displayed
        for (int i = 0; i < towerShopButtons.size(); i++){
            int finalI = i;
            towerShopButtons.get(i).setOnAction(event -> {
                if (containsInstanceOfClass(this.player.getTowers(), towerTypes.get(finalI).getClass())){
                    int towersIndx = findInstanceIndex(player.getTowers(), towerTypes.get(finalI).getClass());
                    displayResourceAmount.setText(String.valueOf(player.getTowers().get(towersIndx).getResourceAmount()));
                    displayReloadSpeed.setText(String.valueOf(player.getTowers().get(towersIndx).getReloadSpeed()));
                }
                else{
                    displayResourceAmount.setText(String.valueOf(towerTypes.get(finalI).getResourceAmount()));
                    displayReloadSpeed.setText(String.valueOf(towerTypes.get(finalI).getReloadSpeed()));
                    }
                towerShopButtons.get(finalI).setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                for(int j = 0; j < towerShopButtons.size(); j++){
                    if (j != finalI){
                        towerShopButtons.get(j).setStyle("");
                    }
                }
            });
        }
    }

    public static boolean containsInstanceOfClass(ArrayList<Tower> list, Class<?> clazz) {
        for (Object obj : list) {
            if (clazz.isInstance(obj)) {
                return true;
            }
        }
        return false;
    }
    public int findInstanceIndex(List<?> list, Class<?> clazz) {
        for (int i = 0; i < list.size(); i++) {
            if (clazz.isInstance(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private void onBackToMainMenuButtonClicked() {
        gameEnvironment.launchMainScreen();
    }
}
