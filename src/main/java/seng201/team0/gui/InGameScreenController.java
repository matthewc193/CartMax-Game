package seng201.team0.gui;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import seng201.team0.game.Player;
import seng201.team0.carts.Cart;
import seng201.team0.game.GameEnvironment;
import seng201.team0.game.Round;
import seng201.team0.threads.CartsThreads;
import seng201.team0.towers.Tower;

import java.util.ArrayList;
import java.util.Arrays;
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
    @FXML
    public ImageView mineCartImage6;
    @FXML
    public ImageView mineCartImage7;
    @FXML
    public ImageView mineCartImage8;
    @FXML
    public ImageView mineCartImage9;
    @FXML
    public ImageView mineCartImage10;
    @FXML
    public ImageView mineCartImage11;
    @FXML
    public ImageView mineCartImage12;
    @FXML
    public ImageView mineCartImage13;
    @FXML
    public ImageView mineCartImage14;
    @FXML
    public ImageView mineCartImage15;
    @FXML
    public ImageView mineCartImage16;
    @FXML
    public ImageView mineCartImage17;
    @FXML
    public ImageView mineCartImage18;
    @FXML
    public ImageView mineCartImage19;
    @FXML
    public ImageView mineCartImage20;


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
        this.mineCart = new Image(getClass().getResourceAsStream("/Img/StoneCart.png"));
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();

    }

    public void initialize(){
        round = new Round(this.gameEnvironment);

        List<ImageView> imageViews = Arrays.asList(mineCartImage1,mineCartImage2,mineCartImage3,mineCartImage4,mineCartImage4,
                mineCartImage5,mineCartImage6,mineCartImage7,mineCartImage8,mineCartImage9,mineCartImage10,mineCartImage11,
                mineCartImage12,mineCartImage13,mineCartImage14,mineCartImage14,mineCartImage15,mineCartImage16,mineCartImage17,
                mineCartImage18,mineCartImage19,mineCartImage20);
        cartImageViews = new ArrayList<>(imageViews);


        CartsThreads cartsThreads = new CartsThreads(this, round, this.gameEnvironment);
        cartsThreads.start();
        System.out.println(round.getCurrentCarts());

        List<Button> inventoryTowerButtons = List.of(inventoryTowerOne, inventoryTowerTwo, inventoryTowerThree, inventoryTowerFour, inventoryTowerFive);
        int buttonIndx = 0;

        // Setting the function of the tower buttons, they should only shoot at the given material
        for(Tower tower : player.getTowers()){
            if (tower.getStatus() == "selected"){
                Button towerButton = inventoryTowerButtons.get(buttonIndx);
                towerButton.setText(tower.getTowerName());
                buttonIndx += 1;
                towerButton.setOnAction(actionEvent -> {
                    for (int cartIndx = 0; cartIndx < round.getCurrentCarts().size(); cartIndx++){
                        Cart cart = (Cart) round.getCart(cartIndx);
                        System.out.println(cart.getResourceType());
                        System.out.println(tower.getResourceType());
                        if (cart.getResourceType().equals(tower.getResourceType())){
                            cart.increaseResourceAmount(tower.getResourceAmount());
                            if (cart.isCartFilled()){
                                round.removeCart(cartIndx);
                            }
                            break;
                        }
                    }
                    towerButton.setDisable(true);
                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.seconds(tower.getReloadSpeed()),
                            ae -> towerButton.setDisable(false)));
                                    towerButton.setStyle("");
                    timeline.setCycleCount(1);

                    timeline.play();


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
    public SequentialTransition animateCart(Image cartImage, ImageView imageView, int cartSpeed) {
        // Setting imageView and imageView size
        imageView.setImage(cartImage);
        imageView.setFitHeight(90);
        imageView.setFitWidth(90);
        imageView.setPreserveRatio(true);

        // Create a sequential transition
        int rotationAngle = -90;
        SequentialTransition sequentialTransition = new SequentialTransition();
        boolean direction = true; // Determines if cart goes up or down Y axis
        for (int i = 0; i < 11; i++) {
            TranslateTransition translate = new TranslateTransition(Duration.millis(cartSpeed), imageView);
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
        sequentialTransition.setOnFinished(event -> {
            gameEnvironment.getPrevRound().setRoundComplete(false);
            gameEnvironment.launchRoundResultsScreen();
        });
        return sequentialTransition;
    }

}
