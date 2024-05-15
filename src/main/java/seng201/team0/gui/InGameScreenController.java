package seng201.team0.gui;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import seng201.team0.Player;
import seng201.team0.game.GameEnvironment;

import java.util.Objects;


public class InGameScreenController {
    @FXML
    public Pane anchorPane;
    @FXML
    public Button inventoryTowerOne;
    @FXML
    public Button inventoryTowerTwo;
    @FXML
    public Button inventoryTowerThree;

    ///public Image mineCart = new Image(getClass().getResourceAsStream("C:/Users/mcroc/Downloads/SENG201/team-60/src/main/java/seng201/team0/gui/mineCart.png"));
    private Player player;
    private GameEnvironment gameEnvironment;

    public InGameScreenController(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
    }



    public void initialize(){

        inventoryTowerOne.setOnAction(event -> {
            //this.animateCart(mineCart);
        });




    }


    private void animateCart(Image cartImage) {
        ImageView cart = new ImageView(cartImage);
        // Set the position of the cart
        cart.setLayoutX(300); // Adjust the initial X position as needed
        cart.setLayoutY(300); // Adjust the initial Y position as needed

        // Add the cart to the scene
        // Assuming you have a Pane or other layout container in your FXML file
        // You should replace "yourPane" with the actual ID of your layout container
        anchorPane.getChildren().add(cart);
        //Scene scene = new Scene(anchorPane,600,600);
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(cart);
        translate.setDuration(Duration.millis(10000)); // Adjust the duration as needed
        translate.setByX(250); // Adjust the translation distance as needed
        translate.play();
    }

}
