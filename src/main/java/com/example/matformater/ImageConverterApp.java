package com.example.matformater;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ImageConverterApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ImageConverterApp.class.getResource("views/app-dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Format Images With Ease");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}