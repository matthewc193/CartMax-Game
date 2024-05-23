package seng201.team0.game;

import javafx.beans.value.ObservableValue;
import seng201.team0.towers.Tower;

import java.util.ArrayList;
import java.util.List;

public class Player {
    /**
     * Instance variables
     */
    String name;
    int money;
    ArrayList<Tower> towers;

    /**
     * Constructor
     */
    public Player() {
        this.name = null;
        this.towers = new ArrayList<>();
        this.money = 0;
    }

    /**
     * Basic getter and setter methods
     */
    public String getName() {
        return name;
    }
    public ArrayList<Tower> getTowers() {
        return towers;
    }
    public int getMoney() {
        return money;
    }
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets all towers from the player's towers that are status "selected"
     * @return list of selected towers
     */
    public List<Tower> getSelectedTowers() {
        List<Tower> selectedTowers = new ArrayList<>();
        for (Tower tower : this.getTowers()) {
            if (tower.getStatus().equals("selected")) {
                selectedTowers.add(tower);
            }
        }
        return selectedTowers;
    }

    /**
     * Methods to increase and decrease account balance of player
     */
    public void increaseMoney(int amount){
        this.money += amount;
    }
    public void decreaseMoney(int amount){
        this.money -= amount;
    }

    /**
     * Add a tower to players towers
     * @param tower the tower being added
     */
    public void addTower(Tower tower){
        this.setLatestTowerStatus(tower);
        towers.add(tower);
    }

    /**
     * Removes a tower from the players towers
     * @param tower the tower being removed
     */
    public void removeTower(Tower tower) {
        towers.remove(tower);
    }

    /**
     * Empties this towers from the players arraylist
     * Used in the setupscreen
     */
    public void resetTowers(){
        this.towers = new ArrayList<>();
    }

    /**
     * Buys a tower if the player has sufficient money
     * @param tower the tower being bought
     * @return true if there is sufficient money and tower was bought
     * false if player doesn't have sufficient money
     */
    public boolean buyTower(Tower tower){
        if (this.money >= tower.getCost()){
            this.money -= tower.getCost();
            addTower(tower);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Sells a tower from the player's towers
     * @param playerTowerInx the index of the tower being sold
     */
    public void sellTower(int playerTowerInx){
        if (playerTowerInx < 0 || playerTowerInx >= towers.size()) {
            throw new IndexOutOfBoundsException("Tower index out of bounds");
        }
        this.money += towers.get(playerTowerInx).getCost();
        this.towers.remove(playerTowerInx);
    }

    /**
     * Sets the status of the newly added tower to "select"
     * if there are less than 5 towers with status "selected"
     * else sets to "reserved"
     * @param latestTower the newly added tower
     */
    private void setLatestTowerStatus(Tower latestTower) {
        int count = 0;
        for (Tower tower : towers) {
            if (tower.getStatus().equals("selected")) {
                count++;
            }
        }
        if (count < 5) {
            latestTower.setStatus("selected");
        } else {
            latestTower.setStatus("reserved");
        }
    }
}
