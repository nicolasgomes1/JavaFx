package com.qaflow.desktopdemo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;


public class HelloController {


    @FXML
    private Label welcomeText;

    @FXML
    private Button hideButton;

    @FXML
    private Button showButton;

    @FXML
    private Label currentText;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        showButton.setDisable(true);
        hideButton.setDisable(false);
        currentText.setText("label is shown");
        currentText.setStyle("-fx-border-color: red; -fx-border-width: 2;");
    }

    @FXML
    protected void onHelloButtonClickReset() {
        welcomeText.setText("");
        showButton.setDisable(false);
        hideButton.setDisable(true);
        currentText.setText("label is hidden");
        currentText.setStyle("-fx-border-color: green; -fx-border-width: 2;");
    }
}
