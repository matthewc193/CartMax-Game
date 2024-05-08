package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seng201.team0.Player;
import seng201.team0.game.GameEnvironment;
import seng201.team0.services.CounterService;
import seng201.team0.towers.Tower;
import seng201.team0.towers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.*;

/**
 * Controller for setUpScreen.fxml
 */
public class SetUpScreenController {
    /**
     * Initialize the window
     *
     * @param stage Top level container for this window
     */
    public void init(Stage stage) {
        player = new Player();
    }
    private GameEnvironment gameEnvironment;

    /**
     *Constructor method which initializes GameEnvironment Class
     */
    public SetUpScreenController(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * FXML import (buttons, test field etc.)
     */
    @FXML
    public TextField playerNameInput;
    @FXML
    public Button easyButton;
    @FXML
    public Button mediumButton;
    @FXML
    public Button hardButton;
    @FXML
    public Button WoodOneButton;
    @FXML
    public Button WoodTwoButton;
    @FXML
    public Button ClayOneButton;
    @FXML
    public Button ClayTwoButton;
    @FXML
    public Button StoneOneButton;
    @FXML
    public Button StoneTwoButton;
    @FXML
    public Button continueButton;
    @FXML
    public Button resetTowersButton;
    /**
     * Temporary variables which are used to instantiate GameEnvironment Class
     * when user clicks continue
     */
    private Player player;
    private int totalRounds;
    private String difficulty;
    public void initialize(){

        List<Button> towerButtons = List.of(WoodOneButton, WoodTwoButton, ClayOneButton, ClayTwoButton, StoneOneButton, StoneTwoButton);
        List<Tower> towerTypes = new ArrayList<>(Arrays.asList(new woodOne(), new woodTwo(), new clayOne(), new clayTwo(), new stoneOne(), new stoneTwo()));

        //Action for when continue button in clicked
        continueButton.setOnAction(event -> {
            onContinueClicked();
        });

        resetTowersButton.setOnAction(event -> {
            player.resetTowers();
            for (int i = 0; i < towerButtons.size(); i++) {
                towerButtons.get(i).setStyle("");
            }
        });

        //Setting actions for difficulty buttons
        easyButton.setOnAction(event -> {
            this.difficulty = "Easy";
            easyButton.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
            mediumButton.setStyle("");
            hardButton.setStyle("");;
        });

        mediumButton.setOnAction(event -> {
            this.difficulty = "Medium";
            easyButton.setStyle("");
            mediumButton.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
            hardButton.setStyle("");
        });

        hardButton.setOnAction(event -> {
            this.difficulty = "Hard";
            easyButton.setStyle("");
            mediumButton.setStyle("");
            hardButton.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
        });

        //Setting action for selecting tower buttons
        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            towerButtons.get(i).setOnAction(event -> {
                //selectedRocketIndex = finalI;
                towerButtons.forEach(button -> {
                    if (button == towerButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                        player.addTower(towerTypes.get(finalI));
                    } else {
                        button.setStyle("");
                    }
                });
            });
        }


    }

    /**
     * Closes the setup screen
     */
    public void onContinueClicked(){
        //should only allow if player name is between 3-15 alphabetical chars
        //setup game environment class
        player.setName(playerNameInput.getText());
        GameEnvironment gameEnvironment = new GameEnvironment(player, totalRounds, difficulty);

        // Need to implement closesetupscreen
    }

}
