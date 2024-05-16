package seng201.team0.gui;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import seng201.team0.Player;
import seng201.team0.game.GameEnvironment;

import java.util.List;


public class InGameScreenController {

    @FXML
    public AnchorPane anchorPane;
    @FXML
    public Button inventoryTowerOne;
    @FXML
    public Button inventoryTowerTwo;
    @FXML
    public Button inventoryTowerThree;

    @FXML
    public ImageView mineCartImage1;
    @FXML
    public ImageView mineCartImage2;
    @FXML
    public ImageView mineCartImage3;
    @FXML
    public ImageView mineCartImage4;
    @FXML
    public ImageView mineCartImage5;

    public Image mineCart;
    private Player player;
    private GameEnvironment gameEnvironment;

    public InGameScreenController(GameEnvironment gameEnvironment){
        this.mineCart = new Image(getClass().getResourceAsStream("/Img/mineCart.png"));
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
    }



    public void initialize(){
        List<ImageView>  cartImageViews = List.of(mineCartImage1, mineCartImage2, mineCartImage3, mineCartImage4, mineCartImage5);

        for (int i = 0; i < cartImageViews.size();i++){
            try {
                // Sleep for 1 second (1000 milliseconds) between animations
                TranslateTransition translate = animateCart(mineCart,cartImageViews.get(i));
                Thread.sleep(1000);
                translate.play();
            } catch (InterruptedException e) {
                // Handle the interruption appropriately
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, stopping animations");
                break;
            }
        }

    }


    public TranslateTransition animateCart(Image cartImage, ImageView imageView) {
        imageView.setImage(cartImage);
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(imageView);
        translate.setDuration(Duration.millis(10000)); // Adjust the duration as needed
        translate.setByX(600);
        return translate;
    }

}
