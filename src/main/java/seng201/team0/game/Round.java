package seng201.team0.game;

import seng201.team0.Player;

public class Round {
    int roundCarts;
    Player player;
    GameEnvironment gameEnvironment;
    public Round(Player player, GameEnvironment gameEnvironment){
        this.player = player;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Should set the number of cart for
     * @return
     */
    /**
    public int setRoundCarts(){
        String difficulty = this.gameEnvironment.getDifficulty();
        if (difficulty == "Easy"){

        }
        if (difficulty == "Medium"){

        }
        if (difficulty == "Hard"){

        }
    }
     **/
}
