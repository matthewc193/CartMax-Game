package seng201.team0.gui;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import seng201.team0.Player;
import seng201.team0.game.GameEnvironment;



public class InGameScreenController {
    @FXML
    public Button inventoryTowerOne;
    @FXML
    public Button inventoryTowerTwo;
    @FXML
    public Button inventoryTowerThree;
    @FXML
    public ImageView cartImage;
    private Player player;
    private GameEnvironment gameEnvironment;
    //Image mineCart = new Image("C://Users//mcroc//Downloads//SENG201//coal-mining-minecart-shaft-mining-clip-art-mines-e068d0f64ebb1a359c3cc3ba928590fa");
    public InGameScreenController(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
    }



    public void initialize(){

        TranslateTransition translate = new TranslateTransition();
        translate.setNode(cartImage);
        translate.setDuration(Duration.millis(2000));
        translate.setByX(250);
        translate.play();

    }

}
