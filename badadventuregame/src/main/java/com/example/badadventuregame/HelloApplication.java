package com.example.badadventuregame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void game(Stage stage2) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("game-view.fxml"));
        Scene scene2 = new Scene(root);
        stage2.setScene(scene2);
        stage2.show();

    }


    public static void main(String[] args) {
        launch();
    }
}
