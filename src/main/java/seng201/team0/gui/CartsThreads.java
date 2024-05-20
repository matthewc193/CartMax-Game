package seng201.team0.gui;


import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import seng201.team0.carts.Cart;
import seng201.team0.carts.WoodCart;
import seng201.team0.game.Round;

import java.util.HashMap;
import java.util.Random;

/**
 * This class is used to call the animateCart method in
 * the InGameScreenController. Operates as the new thread
 * which runs separate to the main thread.
 */
public class CartsThreads extends Thread {
    InGameScreenController inGameScreenController;
    Round round;

    /**
     * Constructor method
     * @param inGameScreenController
     */
    public  CartsThreads(InGameScreenController inGameScreenController, Round round) {
        this.inGameScreenController = inGameScreenController;
        this.round = round;
    }

    /**
     * Overrides the run method so that each
     * cart is sent in a gap in-between.
     */
    @Override
    public void run() {
        Random random = new Random(); // Creating new random number
        while (true) {
            for (int i = 0; i < 6; i++) {
                int randomNumber = random.nextInt(4001 - 2500) + 2500;
                try {
                    // Sleep for 1 second (1000 milliseconds) between animations
                    SequentialTransition translate = inGameScreenController.animateCart(inGameScreenController.mineCart, (ImageView) inGameScreenController.getCartImageViews().get(i));
                    round.addCart(new WoodCart(), translate, (ImageView) inGameScreenController.getCartImageViews().get(i));
                    System.out.println(round.getCurrentCarts());
                    if (i != 0) {
                        CartsThreads.sleep(randomNumber); // Determines the time in-between the carts
                    }
                    translate.play();
                } catch (InterruptedException e) {
                    // Handle the interruption appropriately
                    CartsThreads.currentThread().interrupt();
                    System.out.println("Thread was interrupted, stopping animations");
                    break;
                }
            }
        }
    }
}