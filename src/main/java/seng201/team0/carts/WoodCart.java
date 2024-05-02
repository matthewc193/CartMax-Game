package seng201.team0.carts;

public class WoodCart extends Cart{
    /**
     * Constructor method, sets default values
     */
    public WoodCart(){
        this.resourceType = "Wood";
        this.cartCapacity = 100;
        this.currentResourceAmount = 0;
        this.cartFilled = false;
    }
}
