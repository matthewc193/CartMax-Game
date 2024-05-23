package seng201.team0.game;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import seng201.team0.carts.Cart;
import seng201.team0.towers.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class handles all the logic for the rounds and the amount, type and speed of carts per round.
 */
public class Round {
    public ArrayList<List<Object>> getCurrentCarts;
    int roundCarts;
    Player player;
    GameEnvironment gameEnvironment;
    ArrayList<List<Object>> currentCarts;
    boolean roundComplete;
    public boolean allCartsIn;
    Tower brokenTower = null;

    /**
     * Constructor, initializes values.
     * @param gameEnvironment
     */
    public Round(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
        this.player = gameEnvironment.getPlayer();
        this.currentCarts = new ArrayList<List<Object>>(); // This will contain cart that are currently on the track.
        this.roundComplete = false; // Sets to true if the round is complete
        this.allCartsIn = false; // Determines if all cart have been sent onto the tracks
    }

    /**
     * Adds a cart to the currentCarts. Each addition to currentCart is a list of
     * the cart, the transition (Animation of the cart) and its imageView.
     * @param cart
     * @param transition
     * @param imageView
     */
    public void addCart(Cart cart, SequentialTransition transition, ImageView imageView){
        List<Object> newList = List.of(cart,transition, imageView);
        this.currentCarts.add(newList);
    }

    /**
     * Once a cart has sufficient resources this method will remove the animation and the cart from the current carts.
     * If all other carts for the round have been filled it will end the round and set roundComplete to true.
     * @param cartIndx
     */
    public void removeCart(int cartIndx){
        SequentialTransition transition = (SequentialTransition) currentCarts.get(cartIndx).get(1);
        transition.stop();
        this.getImageView(cartIndx).setImage(null);
        this.currentCarts.remove(cartIndx);
        if (this.allCartsIn && this.currentCarts.size() == 0){
            this.setRoundComplete(true);
            randomTowerBreak();
            gameEnvironment.launchRoundResultsScreen();
        }
    }

    /**
     * This method decides the number of cart that should be in a given round based of the difficulty and the
     * progress through the game ie. the more rounds played the more carts per round.
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
     * Determines the speed of the cart based on difficulty and round number.
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

    /**
     * Random breaks up to one of the towers used in the round. If a tower is broken it will be removed from the
     * players tower list.
     */
    public void randomTowerBreak() {
        List<Tower> selectedTowers = new ArrayList<>(player.getSelectedTowers());
        Random random = new Random();
        for (Tower tower : selectedTowers) {
            if (random.nextDouble() < 0.05) {
                player.removeTower(tower);
                this.brokenTower = tower;
            }
            break;
        }
    }

    /**
     * Returns the broken tower if there is one. Null otherwise
     * @return
     */
    public Tower getBrokenTower(){
        return this.brokenTower;
    }
}
