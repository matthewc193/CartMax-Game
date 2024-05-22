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
        int numberOfCarts = determineNumberOfCart();
        int cartSpeed = determineCartSpeed();
        for (int i = 0; i < numberOfCarts; i++) {
            int randomNumber = random.nextInt(4001 - 2500) + 2500;
            try {
                Cart nextCart = getRandomCart();
                SequentialTransition translate = inGameScreenController.animateCart(nextCart.getCartImage(), (ImageView) inGameScreenController.getCartImageViews().get(i), cartSpeed);
                System.out.println(round.getCurrentCarts());

                CartsThreads.sleep(randomNumber); // Determines the time in-between the carts

                if (i == numberOfCarts - 1){
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
            return nextCart;
        }
        if (randomCartNumber == 1){
            nextCart = new ClayCart();
            return nextCart;
        }
        else{
            nextCart = new StoneCart();
            return nextCart;
        }
    }

    /**
     * This method decides the number of cart that should
     * be in a given round based of the difficulty and the
     * progress through the game ie. the more rounds played
     * the more carts per round.
     * @return
     */
    public int determineNumberOfCart(){
        int difficultyBoost = 0;
        if(gameEnvironment.getDifficulty().equals("Medium")){
            difficultyBoost = 1;
        }
        if(gameEnvironment.getDifficulty().equals("Hard")){
            difficultyBoost = 2;
        }
        return gameEnvironment.getCurrentRoundNumber() + 3 + difficultyBoost;
    }

    /**
     * Determines the speed of the cart based on difficulty
     * and round number.
     * @return
     */
    public int determineCartSpeed(){
        int difficultyBoost = 0;
        if(gameEnvironment.getDifficulty().equals("Medium")){
            difficultyBoost -= 150;
        }
        if(gameEnvironment.getDifficulty().equals("Hard")){
            difficultyBoost -= 300;
        }
        // First round start at 2500 + difficultyBoost as becomes faster as round number increases
        return 2500 - gameEnvironment.getCurrentRoundNumber() * 125 + difficultyBoost;
    }
}