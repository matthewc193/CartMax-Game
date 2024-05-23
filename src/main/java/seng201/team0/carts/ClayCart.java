package seng201.team0.carts;

import javafx.scene.image.Image;

/**
 * Sets the initial values for the
 * ClayCart such as capacity and the cart image.
 * Methods are inherited from the Cart class.
 */
public class ClayCart extends Cart{
    /**
     * Constructor method, sets default values
     */
    public ClayCart(){
        this.resourceType = "Clay";
        this.cartCapacity = 30;
        this.currentResourceAmount = 0;
        this.cartFilled = false;
        this.cartImage = new Image(getClass().getResourceAsStream("/Img/ClayCart.png"));
    }


}
