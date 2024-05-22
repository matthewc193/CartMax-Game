package seng201.team0.carts;

import javafx.scene.image.Image;

public class StoneCart extends Cart{
    /**
     * Constructor method, sets default values
     */
    public StoneCart(){
        this.resourceType = "Stone";
        this.cartCapacity = 1;
        this.currentResourceAmount = 0;
        this.cartFilled = false;
        this.cartImage = new Image(getClass().getResourceAsStream("/Img/StoneCart.png"));
    }

}
