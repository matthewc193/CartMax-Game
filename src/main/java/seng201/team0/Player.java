package seng201.team0;

import seng201.team0.towers.Tower;

import java.util.ArrayList;

public class Player {
    String name;
    double money;
    ArrayList<Tower> towers;
    public Player(){
        this.name = null;
        this.towers = new ArrayList<>();
        this.money = 0;
    }

    /**
     * Basic getter methods
     */
    public String getName() {
        return name;
    }
    public ArrayList<Tower> getTowers() {
        return towers;
    }
    public double getMoney() {
        return money;
    }
    public void setName(String name){
        this.name = name;
    }
    /**
     * Add a tower to players towers
     */
    public void addTower(Tower tower){
        towers.add(tower);
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
    public void decreaseMoney(double amount){
        this.money -= amount;
    }
    public void increaseMoney(double amount){
        this.money += amount;
    }

    public void buyTower() {

    }
}
