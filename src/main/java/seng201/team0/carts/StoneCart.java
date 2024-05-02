package seng201.team0.carts;

public class StoneCart extends Cart{
    /**
     * Constructor method, sets default values
     */
    public StoneCart(){
        this.resourceType = "Stone";
        this.cartCapacity = 100;
        this.currentResourceAmount = 0;
        this.cartFilled = false;
    }

}
