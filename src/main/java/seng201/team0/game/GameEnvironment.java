package seng201.team0.game;

import seng201.team0.Player;

/**
 * This class should be instantiated add the beginning
 * of the game and acts as the overall manager for the game.
 * It should keep track of things such as the player,
 * number of rounds, difficulty and
 * current round number
 */
public class GameEnvironment {
    Player player;
    int totalRounds;
    int currentRoundNumber;
    String difficulty;
    public GameEnvironment(Player player, int totalRounds, String difficulty){
        this.player = player;
        this.totalRounds = totalRounds;
        this.difficulty = difficulty;
        this.currentRoundNumber = 0;
    }
    /**
     * Basic getter methods
     */
    public Player getPlayer() {return player;}
    public int getTotalRounds() {return totalRounds;}
    public int getCurrentRoundNumber() {return currentRoundNumber;}
    public String getDifficulty() {return difficulty;}
    /**
     * This method should start a new round
     * and increase currentRoundNumber by 1
     */
    public void startRound(){
        //To be implemented
    }
    public void quitGame(){
        //To be implemented
    }

}
