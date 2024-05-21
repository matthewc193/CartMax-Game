package seng201.team0.carts;

import javafx.scene.image.Image;

public abstract class Cart {
    String resourceType;
    int currentResourceAmount;
    boolean cartFilled;
    int cartCapacity;

    Image cartImage;

    /**
     * Basic getter methods
     */
    public String getResourceType() {return resourceType;}
    public int getCurrentResourceAmount() {return currentResourceAmount;}
    public boolean isCartFilled() {return cartFilled;}
    public int getCartCapacity() {return cartCapacity;}

    public Image getCartImage() {
        return cartImage;
    }

    /**
     * Will be called when resource is shot from tower into cart.
     * If it exceeds that cartCapacity it should update cartFilled to true
     */
    public void increaseResourceAmount(int resourceAmount){
        System.out.println("INCREASING RESOURCE AMOUNT");
        currentResourceAmount += resourceAmount;
        if(currentResourceAmount >= cartCapacity){
            cartFilled = true;
        }
    }

}
