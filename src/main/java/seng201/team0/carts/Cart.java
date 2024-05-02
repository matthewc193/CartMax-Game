package seng201.team0.carts;

public abstract class Cart {
    String resourceType;
    int currentResourceAmount;
    boolean cartFilled;
    int cartCapacity;

    /**
     * Basic getter methods
     */
    public String getResourceType() {return resourceType;}
    public int getCurrentResourceAmount() {return currentResourceAmount;}
    public boolean isCartFilled() {return cartFilled;}
    public int getCartCapacity() {return cartCapacity;}

    /**
     * Will be called when resource is shot from tower into cart.
     * If it exceeds that cartCapacity it should update cartFilled to true
     */
    public void increaseResourceAmount(int resourceAmount){
        currentResourceAmount += resourceAmount;
        if(currentResourceAmount >= cartCapacity){
            cartFilled = true;
        }
    }

}
