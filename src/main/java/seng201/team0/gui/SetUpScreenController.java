package seng201.team0.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
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
import javafx.scene.control.Tooltip;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import javafx.scene.control.TextFormatter;

/**
 * Controller for setUpScreen.fxml
 */
public class SetUpScreenController {
    /**
     * Initialize the window
     *
     * @param stage Top level container for this window
     */
    private GameEnvironment gameEnvironment;
    public void init(Stage stage) {
        player = new Player();
    }
    public SetUpScreenController(){

    }
    public SetUpScreenController(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;

    }



    /**
     * FXML import (buttons, test field etc.)
     */
    @FXML
    private Slider roundNumberSlider;
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
    @FXML
    public Label playerNameWarning;

    /**
     * Temporary variables which are used to instantiate GameEnvironment Class
     * when user clicks continue
     */
    private Player player;
    private int totalRounds = 5;
    private String difficulty;
    public void initialize(){

        player = gameEnvironment.getPlayer();

        List<Button> towerButtons = List.of(WoodOneButton, WoodTwoButton, ClayOneButton, ClayTwoButton, StoneOneButton, StoneTwoButton);
        List<Tower> towerTypes = new ArrayList<>(Arrays.asList(new woodOne(), new woodTwo(), new clayOne(), new clayTwo(), new stoneOne(), new stoneTwo()));

        //Action for when continue button in clicked
        continueButton.setOnAction(event -> {
            onContinueClicked();
        });

        roundNumberSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                totalRounds = (int) roundNumberSlider.getValue();
            }
        });

        // Displays a warning message if name is not 3-15 chars or not alphanumeric
        playerNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 3 || newValue.length() > 15 || !newValue.matches("[a-zA-Z0-9]*")) {
                playerNameWarning.setText("Name must be 3-15 alphanumeric characters!");
                playerNameWarning.setStyle("-fx-text-fill: red;");
            } else {
                playerNameWarning.setText("");
            }
        });

        //Clears setUpTowers and tower buttons
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
                    if (button == towerButtons.get(finalI) && player.getStartTowers().size() < 3) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                        player.addTower(towerTypes.get(finalI));
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
        if(playerNameWarning.getText() == ""){
            gameEnvironment.getPlayer().setName(playerNameInput.getText());
            gameEnvironment.setTotalRounds(totalRounds);
            gameEnvironment.setDifficulty(difficulty);
            gameEnvironment.setTotalRounds(totalRounds);
            gameEnvironment.closeSetupScreen();
        }
    }

}
