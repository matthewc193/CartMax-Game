package seng201.team0.towers;

public abstract class Tower {
    int resourceAmount;
    double reloadSpeed;
    String resourceType;
    double cost;

    /**
     * Basic Getter methods
     */
    int getResourceAmount() {return resourceAmount;}
    double getReloadSpeed() {return reloadSpeed;}
    String getResourceType() {return resourceType;}
    double getCost() {return cost;}

    /**
     * Upgrade methods
     * All upgrades comes at a cost
     */
    // All tower types should allow for upgrades at a certain cost
    public  void upgradeResourceAmount(){
        // Only allow if player has enough in bank balance
        this.resourceAmount += 1;
        // Implement Function that decrease user bank balance
    }

    public  void upgradeReloadSpeed(){
        // Only allow if player has enough in bank balance
        this.reloadSpeed -= 0.2;
        // Implement Function that decrease user bank balance
    }

}
