package seng201.team0.towers;

import seng201.team0.game.Player;

public abstract class Tower {
    int resourceAmount;
    double reloadSpeed;
    String resourceType;
    int cost;
    String towerName;
    String status;

    /**
     * Basic getter and setter methods
     */
    public int getResourceAmount() {return resourceAmount;}
    public double getReloadSpeed() {return reloadSpeed;}
    public String getResourceType() {return resourceType;}
    public int getCost() {return cost;}
    public String getTowerName() {return towerName;}
    public String getStatus() {return status;}
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * If player has enough money to upgrade the resourceAmount
     * the player is charged 20 and resourceAmount is increased by 2.
     * @param player
     * @return true is player has sufficient funds, false otherwise
     */
    public boolean upgradeResourceAmount(Player player){
        if(player.getMoney() >= 50){
            player.decreaseMoney(50);
            this.resourceAmount += 2;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * If player has enough money to upgrade the reload speed
     * the player is charged 20 and reload speed is decrease by 2.0.
     * @param player
     * @return true is player has sufficient funds.
     * false if player has insufficient funds or reload speed is at its minimum(1.0)
     */
    public boolean upgradeReloadSpeed(Player player){
        if(player.getMoney() >= 50 && this.reloadSpeed > 0.2){
            player.decreaseMoney(50);
            this.reloadSpeed -= 0.2;
            this.reloadSpeed = Math.round(this.reloadSpeed * 10.0)/10.0;
            return true;
        }
        else{
            return false;
        }
    }
}
