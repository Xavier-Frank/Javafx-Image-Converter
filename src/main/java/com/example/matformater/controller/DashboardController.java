package com.example.matformater.controller;

import com.example.matformater.ImageConverterApp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController {

    @FXML
    private Button compressButton;

    @FXML
    private Pane contextArea;

    @FXML
    private Button cropButton;

    @FXML
    private Button developersButton;

    @FXML
    private Button exitButton;

    @FXML
    private Pane paneWithImageFunctions;
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

    private Stage stage;
    private Scene scene;

    /**
     * This method handles the action of Pressing the 'COMPRESS PHOTOS' button
     *
     * @param actionEvent - the action a user performs
     * @throws IOException
     */
    @FXML
    private void compressPhotosButtonPressed(ActionEvent actionEvent) throws IOException {
        System.out.println("Compress photos button pressed ");

        Pane compressPhotosPane = FXMLLoader.load(getClass().getResource("../views/compress-photos.fxml"));
        compressPhotosPane.setPrefSize(701, 533);
        contextArea.getChildren().setAll(compressPhotosPane);
        contextArea.setVisible(true);



        //load a new scene
//        root  = FXMLLoader.load(getClass().getResource("../views/compress-photos.fxml"));
//        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.setTitle("Compress Images");
//        stage.show();

    }

    /**
     * This method handles the action of Pressing the 'RESIZE PHOTOS' button
     *
     * @param actionEvent - the action a user performs
     * @throws IOException
     */
    @FXML
    private void resizePhotosButtonPressed(ActionEvent actionEvent) throws IOException {
        System.out.println("Resize Photos Button Pressed");

        Pane resizePhotosPane = FXMLLoader.load(getClass().getResource("../views/resize-photos.fxml"));
        contextArea.getChildren().setAll(resizePhotosPane);
        contextArea.setVisible(true);

    }

    /**
     * This method handles the action of Pressing the 'CROP PHOTOS' button
     *
     * @param actionEvent - the action a user performs
     * @throws IOException
     */
    @FXML
    private void cropPhotosButtonPressed(ActionEvent actionEvent) throws IOException {
        System.out.println("Crop Button pressed");

        Pane cropPhotosPane = FXMLLoader.load(getClass().getResource("../views/crop-photos.fxml"));
        contextArea.getChildren().setAll(cropPhotosPane);
        contextArea.setVisible(true);

    }

    /**
     * This method handles the action of Pressing the 'CHANGE FORMAT' button
     *
     * @param actionEvent - the action a user performs
     * @throws IOException
     */
    @FXML
    private void changeFormatButtonPressed(ActionEvent actionEvent) throws IOException {
        System.out.println("Change Format Button Pressed");

        Pane changePhotosFormatPane = FXMLLoader.load(getClass().getResource("../views/change-photo-format.fxml"));
        contextArea.getChildren().setAll(changePhotosFormatPane);
        contextArea.setVisible(true);

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
    private void exitButtonPressed(ActionEvent event) throws URISyntaxException, IOException {
        System.out.println("Exit button pressed");

        if (isOkay()){
            System.out.println("Application shutting down...");
            Platform.exit();
            System.exit(0);
        }

    }

    /**
     * This function is called when the exit button is pressed
     *It collects a user confirmation on closing an alert box
     * @return isOkay if a user presses okay button to close application
     * 
     */
    private Boolean isOkay() throws URISyntaxException, IOException {
        System.out.println("isOkay function called to confirm user input");

        Boolean isOkay = false;

        Alert alert = new Alert(Alert.AlertType.NONE);

        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setHeaderText("Please confirm:");
        alert.setContentText("Are you sure to exit the application?");

        //set custom image to the alert box
        alert.setGraphic(new ImageView(getClass().getResource("../icons/warning-gif.gif").toString()));

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
    private void feedBackButtonPressed(ActionEvent actionEvent){
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
    @FXML
    private void backButtonPressed(ActionEvent actionEvent){
        System.out.println("Back button pressed");


    }

}
