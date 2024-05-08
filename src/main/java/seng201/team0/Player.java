package seng201.team0;

import seng201.team0.towers.Tower;

import java.util.ArrayList;

public class Player {
    String name;
    double money;
    ArrayList<Tower> startTowers;
    public Player(){
        this.name = null;
        this.startTowers = new ArrayList<>();
        this.money = 0;
    }

    /**
     * Basic getter methods
     */
    public String getName() {
        return name;
    }
    public ArrayList<Tower> getStartTowers() {
        return startTowers;
    }
    public double getMoney() {
        return money;
    }
    public void setName(String name){
        this.name = name;
    }
    /**
     * Add a tower to players startTowers
     */
    public void addTower(Tower tower){
        startTowers.add(tower);
    }

    /**
     * Empties this towers from the players arraylist
     * Used in the setupscreen
     */
    public void resetTowers(){
        this.startTowers = new ArrayList<>();
    }

    /**
     * Methods to increase and decrease account balance of player
     */
    public void decreaseMoney(double amount){
        this.money -= amount;
    }
    public void increaseMoney(double amount){
        this.money += amount;
    }

    public void buyTower() {

    }
}
