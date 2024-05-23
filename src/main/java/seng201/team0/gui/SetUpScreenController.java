package seng201.team0.gui;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seng201.team0.game.Player;
import seng201.team0.game.GameEnvironment;
import seng201.team0.towers.Tower;
import seng201.team0.towers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller for setUpScreen.fxml.
 */
public class SetUpScreenController {
    /**
     * Initialize the window
     * @param stage Top level container for this window
     */
    private GameEnvironment gameEnvironment;
    public void init(Stage stage) {
        player = new Player();
    }

    /**
     * Constructor method sets the gameEnvironment
     * @param gameEnvironment
     */
    public SetUpScreenController(GameEnvironment gameEnvironment){
        this.gameEnvironment = gameEnvironment;

    }

    // FXML import (buttons, test field etc.)
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
    @FXML
    public Label towerNumberWarning;

    private Player player;
    private int totalRounds = 5;
    private String difficulty;

    /**
     * Initialize method used to set actions for buttons
     */
    public void initialize(){

        player = gameEnvironment.getPlayer();
        this.difficulty = "Easy"; // Defaults to Easy if the player does not select difficulty
        List<Button> towerButtons = List.of(WoodOneButton, WoodTwoButton, ClayOneButton, ClayTwoButton, StoneOneButton, StoneTwoButton);
        List<Tower> towerTypes = new ArrayList<>(Arrays.asList(new WoodOne(), new WoodTwo(), new ClayOne(), new ClayTwo(), new StoneOne(), new StoneTwo()));

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

        playerNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
            setPlayerNameInput(observable, oldValue, newValue); // Displays a warning message if name is not 3-15 chars or not alphanumeric
        });

        resetTowersButton.setOnAction(event -> {
            player.resetTowers(); //Clears setUpTowers and tower buttons
            for (int i = 0; i < towerButtons.size(); i++) {
                towerButtons.get(i).setStyle("");
            }
        });

        //Setting actions for difficulty buttons
        easyButton.setOnAction(event -> {
            setEasyButton();
        });
        mediumButton.setOnAction(event -> {
            setMediumButton();
        });
        hardButton.setOnAction(event -> {
            setHardButton();
        });

        //Setting action for selecting tower buttons
        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i; // variables used within lambdas must be final
            towerButtons.get(i).setOnAction(event -> {
                towerButtons.forEach(button -> {
                    if (button == towerButtons.get(finalI) && player.getTowers().size() < 3) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
                        player.addTower(towerTypes.get(finalI));
                    }
                });
            });
        }

    }

    /**
     * Sets up the player name input so that it only allows names between 3 and 15 alphanumeric characters.
     * @param observable
     * @param oldValue
     * @param newValue
     */
    public void setPlayerNameInput(Observable observable, String oldValue, String newValue){
        if (newValue.length() < 3 || newValue.length() > 15 || !newValue.matches("[a-zA-Z0-9]*")) {
            playerNameWarning.setText("Name must be 3-15 alphanumeric characters!");
            playerNameWarning.setStyle("-fx-text-fill: red;");
        } else {
            playerNameWarning.setText("");
        }
    }

    /**
     * Sets up the easy button function
     */
    private void setEasyButton(){
        this.difficulty = "Easy";
        easyButton.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
        mediumButton.setStyle("");
        hardButton.setStyle("");
    }

    /**
     * Sets up the medium button function
     */
    private void setMediumButton(){
        this.difficulty = "Medium";
        easyButton.setStyle("");
        mediumButton.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
        hardButton.setStyle("");
    }

    /**
     * Sets up the hard button function
     */
    private void setHardButton(){
        this.difficulty = "Hard";
        easyButton.setStyle("");
        mediumButton.setStyle("");
        hardButton.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
    }

    /**
     * Closes the setup screen
     */
    public void onContinueClicked(){
        //should only allow if player name is between 3-15 alphabetical chars
        if(gameEnvironment.getPlayer().getTowers().size() != 3){
            towerNumberWarning.setStyle("-fx-text-fill: red;");
        }
        else{
            if(playerNameWarning.getText() == ""){
                gameEnvironment.getPlayer().setName(playerNameInput.getText());
                gameEnvironment.setTotalRounds(totalRounds);
                gameEnvironment.setDifficulty(difficulty);
                gameEnvironment.setTotalRounds(totalRounds);
                gameEnvironment.closeSetupScreen();
            }
        }

    }

}
