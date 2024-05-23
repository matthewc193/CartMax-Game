package seng201.team0.carts;

import javafx.scene.image.Image;

/**
 * Sets the initial values for the
 * StoneCart such as capacity and the cart image.
 * Methods are inherited from the Cart class.
 */
public class StoneCart extends Cart{
    /**
     * Constructor method, sets default values
     */
    public StoneCart(){
        this.resourceType = "Stone";
        this.cartCapacity = 15;
        this.currentResourceAmount = 0;
        this.cartFilled = false;
        this.cartImage = new Image(getClass().getResourceAsStream("/Img/StoneCart.png"));
    }

}
