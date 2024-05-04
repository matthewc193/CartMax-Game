package seng201.team0.towers;

public abstract class Tower {
    int resourceAmount;
    double reloadSpeed;
    String resourceType;
    double cost;

    /**
     * Basic Getter methods
     */
    public int getResourceAmount() {return resourceAmount;}
    public double getReloadSpeed() {return reloadSpeed;}
    public String getResourceType() {return resourceType;}
    public double getCost() {return cost;}

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
