package com.example.matformater.controller;

import com.example.matformater.ImageConverterApp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class DashboardController {

    @FXML
    private Pane bodyPane;

    @FXML
    private Button compressButton;

    @FXML
    private Button cropButton;

    @FXML
    private Button developersButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button feedbackButton;

    @FXML
    private Button formatButton;

    @FXML
    private Button rateusButton;

    @FXML
    private Button resizeButton;

    @FXML
    private Button resultsButton;
    @FXML
    private AnchorPane root;

    /**
     * This method handles the action of Pressing the 'COMPRESS PHOTOS' button
     *
     * @param actionEvent - the action a user performs
     * @throws IOException
     */
    @FXML
    private void compressPhotosButtonPressed(ActionEvent actionEvent) throws IOException {
        System.out.println("Compress photos button pressed ");


 }

    /**
     * This method handles the action of Pressing the 'RESIZE PHOTOS' button
     *
     * @param actionEvent - the action a user performs
     * @throws IOException
     */
    @FXML
    private void resizePhotosButtonPressed(ActionEvent actionEvent){
        System.out.println("Resize Photos Button Pressed");

    }

    /**
     * This method handles the action of Pressing the 'CROP PHOTOS' button
     *
     * @param actionEvent - the action a user performs
     * @throws IOException
     */
    @FXML
    private void cropPhotosButtonPressed(ActionEvent actionEvent){
        System.out.println("Crop Button pressed");

    }

    /**
     * This method handles the action of Pressing the 'CHANGE FORMAT' button
     *
     * @param actionEvent - the action a user performs
     * @throws IOException
     */
    @FXML
    private void changeFormatButtonPressed(ActionEvent actionEvent){
        System.out.println("Change Format Button Pressed");

    }

    /**
     * Dashboard buttons
     */

    /**
     * This method handles the action of Pressing the 'EXIT' button
     *
     * @param event
     */
    @FXML
    private void exitButtonPressed(ActionEvent event){
        System.out.println("Exit button pressed");

        if (isOkay()){
            Platform.exit();
            System.exit(0);
        }

    }

    /**
     * This function is called when the exit button is pressed
     *It collects a user confirmation on closing an alert box
     * @return
     */
    private Boolean isOkay(){
        System.out.println("isOkay function called to confirm user input");

        Boolean isOkay = false;

        Alert alert = new Alert(Alert.AlertType.NONE);

        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setHeaderText("Please confirm:");
        alert.setContentText("Are you sure to exit the application?");

        ButtonType okay = new ButtonType("Okay");
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(okay,cancel);



        Optional<ButtonType> userChoice = alert.showAndWait();

        if (userChoice.isPresent() && userChoice.get() == okay)
            isOkay = true;

        return isOkay;

    }

    /**
     * This method handles the action of Pressing the 'RATE US' button
     *
     * @param event
     */
    @FXML
    private  void rateusButtonPressed(ActionEvent event){
        System.out.println("Rate us button pressed");

    }
    /**
     * This method handles the action of Pressing the 'FEEDBACK' button
     *
     * @param actionEvent
     */
    @FXML
    private  void feedBackButtonPressed(ActionEvent actionEvent){
        System.out.println("Feed back button pressed");


    }
    /**
     * This method handles the action of Pressing the 'DEVELOPERS' button
     *
     * @param actionEvent
     */
    @FXML
    private void developerButtonPressed(ActionEvent actionEvent){
        System.out.println("Developer button pressed");


    }
    /**
     * This method handles the action of Pressing the 'RESULTS FOLDER' button
     *
     * @param actionEvent
     */
    @FXML
    private void resultsButtonPressed(ActionEvent actionEvent){
        System.out.println("Results button pressed");


    }

}
