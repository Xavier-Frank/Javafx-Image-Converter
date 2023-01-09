package com.example.matformater.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CompressImagesController {

    @FXML
    private Button backButton;

    @FXML
    private Button clearSelectionButton;

    @FXML
    private Button compressAndSaveButton;

    @FXML
    private ImageView compressImagesImageView;

    @FXML
    private Pane root;

    private Stage stage;
    private Scene scene;
    @FXML
    private void handleDragOverAction(DragEvent event) {
        compressImagesImageView.setVisible(true);
        //accept only images
        if(event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }

    }

    @FXML
    private void backButtonPressed(ActionEvent actionEvent) throws IOException {
        System.out.println("Back button pressed");

        //load a new scene
        root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/app-dashboard.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Format Images with Ease");
        stage.show();

    }

    @FXML
    private void handleDropAction(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();

        Image image = new Image(new FileInputStream(files.get(0)));

        compressImagesImageView.setImage(image);
    }

    @FXML
    private void removeImageInImageView(ActionEvent event) {
        compressImagesImageView.setImage(null);

    }

}
