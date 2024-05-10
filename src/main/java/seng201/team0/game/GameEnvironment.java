package seng201.team0.game;

import seng201.team0.Player;

import java.util.List;
import java.util.function.Consumer;

/**
 * This class should be instantiated add the beginning
 * of the game and acts as the overall manager for the game.
 * It should keep track of things such as the player,
 * number of rounds, difficulty and
 * current round number
 */
public class GameEnvironment {

    private final Consumer<GameEnvironment> setupScreenLauncher;
    private final Consumer<GameEnvironment> mainScreenLauncher;

    private final Runnable clearScreen;
    Player player;
    int totalRounds;
    int currentRoundNumber;
    String difficulty;

    public GameEnvironment(Consumer<GameEnvironment> setupScreenLauncher, Consumer<GameEnvironment> mainScreenLauncher, Runnable clearScreen) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.mainScreenLauncher = mainScreenLauncher;
        this.clearScreen = clearScreen;
        this.player = new Player();
        this.currentRoundNumber = 0;
        launchSetupScreen();
    }
    /**
     * Basic getter and setter methods
     */
    public Player getPlayer() {return player;}
    public void setPlayer(Player player) {this.player = player;}
    public int getTotalRounds() {return totalRounds;}
    public void setTotalRounds(int rounds){
        this.totalRounds = rounds;
    }
    public int getCurrentRoundNumber() {return currentRoundNumber;}
    public String getDifficulty() {return difficulty;}
    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
    }
    /**
     * This method should start a new round
     * and increase currentRoundNumber by 1
     */

    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }
    public void closeSetupScreen() {
        clearScreen.run();
        launchMainScreen();
    }
    public void launchMainScreen() {
        mainScreenLauncher.accept(this);
    }

    public void startRound(){
        //To be implemented
    }
    public void quitGame(){
        //To be implemented
    }

}
