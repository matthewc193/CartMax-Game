package seng201.team0.game;

import javafx.animation.SequentialTransition;
import javafx.scene.image.ImageView;
import seng201.team0.carts.Cart;

import java.util.ArrayList;
import java.util.List;

public class Round {
    public ArrayList<List<Object>> getCurrentCarts;
    int roundCarts;
    Player player;
    GameEnvironment gameEnvironment;
    ArrayList<List<Object>> currentCarts;
    boolean roundComplete;
    public boolean allCartsIn;
    public Round(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
        this.currentCarts = new ArrayList<List<Object>>();
        this.roundComplete = false;
        this.allCartsIn = false;
    }

    public void addCart(Cart cart, SequentialTransition transition, ImageView imageView){
        List<Object> newList = List.of(cart,transition, imageView);
        System.out.println("here");
        this.currentCarts.add(newList);
    }

    public void removeCart(int cartIndx){
        SequentialTransition transition = (SequentialTransition) currentCarts.get(cartIndx).get(1);
        transition.stop();
        this.getImageView(cartIndx).setImage(null);
        this.currentCarts.remove(cartIndx);
        if (this.allCartsIn && this.currentCarts.size() == 0){
            this.setRoundComplete(true);
            gameEnvironment.launchRoundResultsScreen();
        }
    }

    /**
     * This method decides the number of cart that should
     * be in a given round based of the difficulty and the
     * progress through the game ie. the more rounds played
     * the more carts per round.
     * @return
     */
    public int determineNumberOfCarts(){
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

    public ArrayList<List<Object>> getCurrentCarts(){
        return this.currentCarts;
    }

    public SequentialTransition getTransition(int cartIndx){
        return (SequentialTransition) this.currentCarts.get(cartIndx).get(1);
    }

    public Cart getCart(int cartIndx){
        return (Cart) this.currentCarts.get(cartIndx).get(0);
    }

    public ImageView getImageView(int cartIndx){
        return (ImageView) this.currentCarts.get(cartIndx).get(2);
    }

    /**
     * Return true if round is complete false otherwise
     * @return Booleans
     */
    public boolean isRoundComplete(){
        return this.roundComplete;
    }

    /**
     * Sets roundComplete
     */
    public void setRoundComplete(boolean roundComplete){
        this.roundComplete = roundComplete;
    }

}
