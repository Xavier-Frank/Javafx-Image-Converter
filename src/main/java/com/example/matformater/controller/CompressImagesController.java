package com.example.matformater.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private Button previousImageButton;

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
        int k = imageIndex.intValue();
        AtomicReference<Image> image = new AtomicReference<>(new Image(new FileInputStream(files.get(k))));

        imageViewPromptText.setVisible(false);
        compressImagesImageView.setImage(image.get());

        //for image numbering
        AtomicInteger i = new AtomicInteger(1);
        imageNumbers.setText(String.valueOf(i.get()));

        // check if next image button is pressed to load the next image in the list
        nextImageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    try {
                        setNextImage(imageIndex, i, files, image);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
            }
        });

        // check if previous image button is pressed to load the previous image in the list
        previousImageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    setPreviousImage(imageIndex, i, files, image);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }

    /**
     * This method sets the next image in the image view
     *
     * @param imageIndex image index to be set to get the image view
     * @param i the image numbers the bottom of the image view
     * @param files list containing the image files
     * @param image the image to be set to the view
     * @throws FileNotFoundException
     */
    private void setNextImage(AtomicInteger imageIndex, AtomicInteger i, List<File> files,AtomicReference<Image> image) throws FileNotFoundException {
            if (imageIndex.get() > files.size()) {

                i.incrementAndGet();
                imageNumbers.setText(String.valueOf(i));

                imageIndex.incrementAndGet();
                int k = imageIndex.intValue();
                image = new AtomicReference<Image>(new Image(new FileInputStream(files.get(k))));
                compressImagesImageView.setImage(image.get());

                System.out.println("Current image index is : " + imageIndex);
            } else {
                imageNumbers.setText(String.valueOf(files.size()));
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("No more images");
                alert.setHeaderText("Error loading images!");
                alert.setGraphic(new ImageView(getClass().getResource("../icons/warning-gif.gif").toString()));

                alert.show();
            }
    }
    /**
     * This method sets the previous image in the image view
     *
     * @param imageIndex image index to be set to get the image view
     * @param i the image numbers the bottom of the image view
     * @param files list containing the image files
     * @param image the image to be set to the view
     * @throws FileNotFoundException
     */
    private void setPreviousImage(AtomicInteger imageIndex, AtomicInteger i, List<File> files,AtomicReference<Image> image) throws FileNotFoundException {
        if(imageIndex.get() > 0 ){
            i.decrementAndGet();
            imageNumbers.setText(String.valueOf(i));

            imageIndex.decrementAndGet();
            int k = imageIndex.intValue();
            image = new AtomicReference<>(new Image(new FileInputStream(files.get(k))));
            compressImagesImageView.setImage(image.get());

            System.out.println("Current image index is: " + image);
        }else {
            imageNumbers.setText(String.valueOf(1));
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("No more images");
            alert.setHeaderText("Error loading images!");
            alert.setGraphic(new ImageView(getClass().getResource("../icons/warning-gif.gif").toString()));

            alert.show();
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

        AtomicInteger imageIndex = new AtomicInteger(0);
        int k = imageIndex.intValue();
        AtomicReference<Image> image = new AtomicReference<>(new  Image(new FileInputStream(selectedFiles.get(k))));

        imageViewPromptText.setVisible(false);
        compressImagesImageView.setImage(image.get());

        //for image numbering
        AtomicInteger i = new AtomicInteger(1);
        imageNumbers.setText(String.valueOf(i));

        // check if next image button is pressed to load the next image in the list
        nextImageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    setNextImage(imageIndex, i, selectedFiles, image);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //check if previous imaage button is pressed to load previous image
        previousImageButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    setPreviousImage(imageIndex, i, selectedFiles, image);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
