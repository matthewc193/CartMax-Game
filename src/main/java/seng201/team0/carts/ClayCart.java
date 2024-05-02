package seng201.team0.carts;

public class ClayCart extends Cart{
    /**
     * Constructor method, sets default values
     */
    public ClayCart(){
        this.resourceType = "Clay";
        this.cartCapacity = 100;
        this.currentResourceAmount = 0;
        this.cartFilled = false;
    }
}
