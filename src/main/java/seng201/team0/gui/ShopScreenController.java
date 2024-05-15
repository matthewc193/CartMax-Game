package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import seng201.team0.Player;
import seng201.team0.game.GameEnvironment;
import seng201.team0.towers.Tower;
import seng201.team0.towers.woodOne;

import java.util.ArrayList;

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
    public void initialize(){
        woodOneShop.setOnAction(event -> {
            woodOne instanceToCheck = new woodOne();
            if (containsInstanceOfClass(this.player.getTowers(), instanceToCheck.getClass())){
                woodOneShop.setText("Wood Tower 1.0");
            }
        });
    }

    public static boolean containsInstanceOfClass(ArrayList<Tower> list, Class<?> clazz) {
        for (Object obj : list) {
            if (clazz.isInstance(obj)) {
                return true;
            }
        }
        return false;
    }
}
