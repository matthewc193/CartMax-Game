package seng201.team0;

import javafx.beans.value.ObservableValue;
import seng201.team0.towers.Tower;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    int money;
    ArrayList<Tower> towers;
    public Player(){
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
     * Add a tower to players towers
     *
     * @param tower the tower being added
     */
    public void addTower(Tower tower){
        this.setLatestTowerStatus(tower);
        towers.add(tower);
    }

    /**
     * Removes a tower from the players towers
     *
     * @param tower the tower being removed
     */
    public void removeTower(Tower tower) {
        towers.remove(tower);
    }

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
     * Empties this towers from the players arraylist
     * Used in the setupscreen
     */
    public void resetTowers(){
        this.towers = new ArrayList<>();
    }

    /**
     * Methods to increase and decrease account balance of player
     */
    public void decreaseMoney(int amount){
        this.money -= amount;
    }
    public void increaseMoney(int amount){
        this.money += amount;
    }

    private void setLatestTowerStatus(Tower latestTower) {
        int count = 0;
        for (Tower tower : towers) {
            if (tower.getStatus() == "selected") {
                count++;
            }
        }
        if (count < 5) {
            latestTower.setStatus("selected");
        } else {
            latestTower.setStatus("reserved");
        }
    }

    public void sellTower(int playerTowerInx){
        this.money += towers.get(playerTowerInx).getCost();
        this.towers.remove(playerTowerInx);
    }

    public boolean buyTower(Tower tower){
        if (this.money >= tower.getCost()){
            this.money -= tower.getCost();
            this.addTower(tower);
            return true;
        }
        else{
            return false;
        }
    }
}
