package seng201.team0.carts;

import javafx.scene.image.Image;

public class WoodCart extends Cart{
    /**
     * Constructor method, sets default values
     */
    public WoodCart(){
        this.resourceType = "Wood";
        this.cartCapacity = 1;
        this.currentResourceAmount = 0;
        this.cartFilled = false;
        this.cartImage = new Image(getClass().getResourceAsStream("/Img/WoodCart.png"));
    }
}
