package com.example.matformater.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CompressImagesController {

    @FXML
    private Button PreviousImageButton;

    @FXML
    private Button backButton;

    @FXML
    private Button clearSelectionButton;

    @FXML
    private Button compressAndSaveButton;

    @FXML
    private Label imageViewPromptText;

    @FXML
    private ImageView compressImagesImageView;

    @FXML
    private Label imageNumbers;

    @FXML
    private Button nextImageButton;

    @FXML
    private Pane root;

    @FXML
    private Button uploadImageButton;

    private Stage stage;
    private Scene scene;
    @FXML
    private void handleDragOverAction(DragEvent event) {
        System.out.println("User dragging image over the image view");
        compressImagesImageView.setVisible(true);
        //accept only images
        if(event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }

    }

    @FXML
    private void handleDropAction(DragEvent event) throws FileNotFoundException {
        System.out.println("User has dropped image over the image view \n" + "and image captured");
        List<File> files = event.getDragboard().getFiles();

        AtomicInteger imageIndex = new AtomicInteger(0);
        AtomicReference<Image> image = new AtomicReference<>(new Image(new FileInputStream(files.get(0))));

        imageViewPromptText.setVisible(false);
        compressImagesImageView.setImage(image.get());

        //for image numbering
        AtomicInteger i = new AtomicInteger(1);
        imageNumbers.setText(String.valueOf(i.get()));

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
    private void removeImageInImageView(ActionEvent event) {
        System.out.println("Clear Selection Button Pressed to remove image from the imageView");

        imageNumbers.setText(" ");
        imageViewPromptText.setVisible(true);
        compressImagesImageView.setImage(null);

    }

    @FXML
    private void uploadImage(ActionEvent event) throws FileNotFoundException {
        System.out.println("Upload Image button Pressed");

        //set image filter to retrieve images with .jpg and .png extensions
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(imageFilter);

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);

        int imageIndex = 0;

        Image image = new Image(new FileInputStream(selectedFiles.get(imageIndex)));

        imageViewPromptText.setVisible(false);
        compressImagesImageView.setImage(image);

        //for image numbering
        int i = 1;
        imageNumbers.setText(String.valueOf(i));

        if (nextImageButton.isPressed()){
            i++;
            imageNumbers.setText(String.valueOf(i));

            imageIndex++;
            image = new Image(new FileInputStream(selectedFiles.get(imageIndex)));
            compressImagesImageView.setImage(image);
        }
    }

}
