package seng201.team0.gui;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import seng201.team0.Player;
import seng201.team0.game.GameEnvironment;

import java.util.ArrayList;
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
    public ArrayList<ImageView>  cartImageViews;
    private Player player;
    private GameEnvironment gameEnvironment;

    public InGameScreenController(GameEnvironment gameEnvironment){
        this.mineCart = new Image(getClass().getResourceAsStream("/Img/mineCart.png"));
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
    }

    public void initialize(){
        cartImageViews = new ArrayList<>();
        cartImageViews.add(mineCartImage1);
        cartImageViews.add(mineCartImage2);
        cartImageViews.add(mineCartImage3);
        cartImageViews.add(mineCartImage4);
        cartImageViews.add(mineCartImage5);

        CartsThreads cartsThreads = new CartsThreads(this);
        cartsThreads.start();


    }

    public Image getMineCart(){
        return mineCart;
    }

    public List getCartImageViews(){
        return this.cartImageViews;
    }

//    public TranslateTransition animateCart(Image cartImage, ImageView imageView) {
//        imageView.setImage(cartImage);
//        imageView.setFitHeight(90);
//        imageView.setFitWidth(90);
//        imageView.setPreserveRatio(true);
//        TranslateTransition translate = new TranslateTransition();
//        translate.setNode(imageView);
//        translate.setDuration(Duration.millis(10000));// Adjust the duration as needed
//        for(int i = 0; i < 4; i++){
//            if ( i % 2 == 0){
//                translate.setByX(100);
//            }
//            else{
//                translate.setByY(100);
//            }
//            //translate.setByX(600);
//        }
//        return translate;
//    }


    public SequentialTransition animateCart2222(Image cartImage, ImageView imageView) {
        imageView.setImage(cartImage);
        imageView.setFitHeight(90);
        imageView.setFitWidth(90);
        imageView.setPreserveRatio(true);

        // Create a sequential transition
        int rotationAngle = -90;
        SequentialTransition sequentialTransition = new SequentialTransition();
        boolean direction = true;
        for (int i = 0; i < 8; i++) {
            TranslateTransition translate = new TranslateTransition(Duration.millis(1000), imageView);
            RotateTransition rotate = new RotateTransition(Duration.millis(500), imageView);
            if (i % 4 == 0 || i % 4 == 3) {
                rotationAngle = 90;
            }
            else{
                rotationAngle = -90;
            }
            if (i % 2 == 0) {
                translate.setByX(100);
                rotate.setByAngle(rotationAngle);
            } else {
                if(direction == true){
                    translate.setByY(100);
                    rotate.setByAngle(rotationAngle);
                    direction = false;
                }
                else{
                    translate.setByY(-100);
                    rotate.setByAngle(rotationAngle);
                    direction = true;
                }

            }
            // Add both translate and rotate transitions to the sequential transition
            sequentialTransition.getChildren().addAll(translate, rotate);
        }

        return sequentialTransition;
    }

}
