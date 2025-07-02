package com.example.cper_desktop.controllers.reusable_components;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ToastNotificationController {

    @FXML private Pane root;
    @FXML private Label titulo;
    @FXML private Label descricao;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            root.setVisible(false);
            root.setOpacity(0.0);
        });
    }

    public void showMessage(String title, String desc, String type) {
        titulo.setText(title);
        descricao.setText(desc);

        root.setVisible(true);
        root.setOpacity(0.0);

        root.getStyleClass().setAll("toast", "toast-" + type.toLowerCase());

        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        fadeIn.setOnFinished(e -> {
            FadeTransition fadeOut = new FadeTransition(Duration.millis(300), root);
            fadeOut.setDelay(Duration.seconds(2));
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(ev -> root.setVisible(false));
            fadeOut.play();
        });

        fadeIn.play();
    }

    public void showSuccess(String title, String desc) {
        showMessage(title, desc, "success");
    }

    public void showError(String title, String desc) {
        showMessage(title, desc, "error");
    }

    public Pane getRoot() {
        return root;
    }
}