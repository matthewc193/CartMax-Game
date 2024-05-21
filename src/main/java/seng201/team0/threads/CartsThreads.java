package seng201.team0.threads;


import javafx.animation.SequentialTransition;
import javafx.scene.image.ImageView;
import seng201.team0.carts.Cart;
import seng201.team0.carts.ClayCart;
import seng201.team0.carts.StoneCart;
import seng201.team0.carts.WoodCart;
import seng201.team0.game.GameEnvironment;
import seng201.team0.game.Round;
import seng201.team0.gui.InGameScreenController;

import java.util.Random;

/**
 * This class is used to call the animateCart method in
 * the InGameScreenController. Operates as the new thread
 * which runs separate to the main thread.
 */
public class CartsThreads extends Thread {
    InGameScreenController inGameScreenController;
    Round round;
    GameEnvironment gameEnvironment;

    /**
     * Constructor method
     * @param inGameScreenController
     */
    public  CartsThreads(InGameScreenController inGameScreenController, Round round, GameEnvironment gameEnvironment) {
        this.inGameScreenController = inGameScreenController;
        this.round = round;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Overrides the run method so that each
     * cart is sent in a random gap in-between.
     */
    @Override
    public void run() {
        Random random = new Random(); // Creating new random number
        for (int i = 0; i < 5; i++) {
            int randomNumber = random.nextInt(4001 - 2500) + 2500;
            try {
                Cart nextCart = getRandomCart();
                SequentialTransition translate = inGameScreenController.animateCart(nextCart.getCartImage(), (ImageView) inGameScreenController.getCartImageViews().get(i));
                System.out.println(round.getCurrentCarts());

                CartsThreads.sleep(randomNumber); // Determines the time in-between the carts

                if (i == 4){
                    round.allCartsIn = true;
                }

                round.addCart(nextCart, translate, (ImageView) inGameScreenController.getCartImageViews().get(i));
                translate.play();
            } catch (InterruptedException e) {
                // Handle the interruption appropriately
                CartsThreads.currentThread().interrupt();
                System.out.println("Thread was interrupted, stopping animations");
                break;
            }
        }

    }

    /**
     * Return a cart of random type
     * (Wood, Clay or Stone).
     * @return return nextCart;
     */
    public Cart getRandomCart(){
        Random randomCart = new Random();
        int randomCartNumber = randomCart.nextInt(3);
        Cart nextCart;
        if (randomCartNumber == 0){
            nextCart = new WoodCart();
        }
        if (randomCartNumber == 1){
            nextCart = new ClayCart();
        }
        else {
            nextCart = new StoneCart();
        }
        return nextCart;
    }
}