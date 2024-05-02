package seng201.team0;

import seng201.team0.towers.Tower;

import java.util.ArrayList;

public class Player {
    private String name;
    private String difficulty;
    private ArrayList<Tower> startTowers;
    private double money;

    public String getName() {return name;}
    public String getDifficulty() {return difficulty;}
    public ArrayList<Tower> getStartTowers() {return startTowers;}
    public double getMoney() {return money;}

    public void buyTower() {

    }
}
