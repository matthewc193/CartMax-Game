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
import seng201.team0.carts.Cart;
import seng201.team0.carts.WoodCart;
import seng201.team0.game.GameEnvironment;
import seng201.team0.game.Round;
import seng201.team0.towers.Tower;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the inGameScreen.fxml. Handles the Cart animations
 * and allows user to shoot resources at cart.
 */
public class InGameScreenController {

    //FXML injections
    @FXML
    public AnchorPane anchorPane;
    @FXML
    public Button inventoryTowerOne;
    @FXML
    public Button inventoryTowerTwo;
    @FXML
    public Button inventoryTowerThree;
    @FXML
    public Button inventoryTowerFour;
    @FXML
    public Button inventoryTowerFive;
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
    public Round round;

    /**
     * Constructor method, accesses required images
     * and set the gameEnvironment and
     * player variables.
     * @param gameEnvironment
     */
    public InGameScreenController(GameEnvironment gameEnvironment){
        this.mineCart = new Image(getClass().getResourceAsStream("/Img/mineCart.png"));
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();

    }

    public void initialize(){
        round = new Round();

        cartImageViews = new ArrayList<>();
        cartImageViews.add(mineCartImage1);
        cartImageViews.add(mineCartImage2);
        cartImageViews.add(mineCartImage3);
        cartImageViews.add(mineCartImage4);
        cartImageViews.add(mineCartImage5);

        CartsThreads cartsThreads = new CartsThreads(this, round);
        cartsThreads.start();
        System.out.println(round.getCurrentCarts());

        List<Button> inventoryTowerButtons = List.of(inventoryTowerOne, inventoryTowerTwo, inventoryTowerThree, inventoryTowerFour, inventoryTowerFive);
        int buttonIndx = 0;
        for(Tower tower : player.getTowers()){
            if (tower.getStatus() == "selected"){
                Button towerButton = inventoryTowerButtons.get(buttonIndx);
                towerButton.setText(tower.getTowerName());
                buttonIndx += 1;
                towerButton.setOnAction(actionEvent -> {
                    round.getCart(0).increaseResourceAmount(tower.getResourceAmount());
                    if (round.getCart(0).isCartFilled()){
                        round.removeCart(0);
                    }

                });
            }
        }


    }

    public Image getMineCart(){
        return mineCart;
    }

    /**
     * Returns a list of FXML imageView injections.
     */
    public List getCartImageViews(){
        return this.cartImageViews;
    }

    /**
     * Animate cart method handle the animation of an individual cart.
     * Creates a zigzag of the cart through the screen. Duration can be
     * changed based on difficulty.
     * @param cartImage
     * @param imageView
     * @return SequentialTransition
     */
    public SequentialTransition animateCart(Image cartImage, ImageView imageView) {


        // Setting imageView and imageView size
        imageView.setImage(cartImage);
        imageView.setFitHeight(90);
        imageView.setFitWidth(90);
        imageView.setPreserveRatio(true);

        // Create a sequential transition
        int rotationAngle = -90;
        SequentialTransition sequentialTransition = new SequentialTransition();
        boolean direction = true; // Determines if cart goes up or down Y axis
        for (int i = 0; i < 8; i++) {
            TranslateTransition translate = new TranslateTransition(Duration.millis(1000), imageView);
            RotateTransition rotate = new RotateTransition(Duration.millis(500), imageView); // Creating a Rotate Transition

            // Set the correct rotational angle;
            if (i % 4 == 0 || i % 4 == 3) {
                rotationAngle = 90;
            }
            else{
                rotationAngle = -90;
            }

            // Determines the direction of the transition
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

        //sequentialTransition.setOnFinished(event -> imageView.setImage(null));

        return sequentialTransition;
    }

}
