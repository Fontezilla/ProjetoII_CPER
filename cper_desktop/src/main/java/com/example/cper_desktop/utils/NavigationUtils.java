package com.example.cper_desktop.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class NavigationUtils {

    private static Stage primaryStage;
    private static ApplicationContext springContext;

    public static void initialize(Stage stage, ApplicationContext context) {
        primaryStage = stage;
        springContext = context;
    }

    public static void goTo(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(NavigationUtils.class.getResource("/views/" + fxmlPath));
            loader.setControllerFactory(springContext::getBean);
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar vista: " + fxmlPath, e);
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void reloadBaseLayout() {
        goTo("layouts/BaseLayout.fxml");
    }
}
