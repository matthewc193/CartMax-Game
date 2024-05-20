package seng201.team0.game;

import com.sun.javafx.UnmodifiableArrayList;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import seng201.team0.Player;
import seng201.team0.carts.Cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Round {
    public ArrayList<List<Object>> getCurrentCarts;
    int roundCarts;
    Player player;
    GameEnvironment gameEnvironment;
    ArrayList<List<Object>> currentCarts;
    public Round(Player player, GameEnvironment gameEnvironment){
        this.player = player;
        this.gameEnvironment = gameEnvironment;
        this.currentCarts = new ArrayList<List<Object>>();
    }

    public Round(){
        this.currentCarts = new ArrayList<List<Object>>();
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
        System.out.println("Cart Removed");
        this.currentCarts.remove(cartIndx);
    }

    public ArrayList<?> getCurrentCarts(){
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
     * Should set the number of cart for
     * @return
     */
    /**
    public int setRoundCarts(){
        String difficulty = this.gameEnvironment.getDifficulty();
        if (difficulty == "Easy"){

        }
        if (difficulty == "Medium"){

        }
        if (difficulty == "Hard"){

        }
    }
     **/
}
