package com.example.cper_desktop.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {

    private static Stage primaryStage;

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    public static void goTo(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(Navigation.class.getResource("/views/layouts/" + fxmlPath));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar vista: " + fxmlPath, e);
        }
    }
}