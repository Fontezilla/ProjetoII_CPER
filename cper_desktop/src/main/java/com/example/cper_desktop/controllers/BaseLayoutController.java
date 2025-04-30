package com.example.cper_desktop.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Component;

@Component
public class BaseLayoutController {

    @FXML
    private StackPane mainContent;

    public void setContent(Node content) {
        mainContent.getChildren().setAll(content);
    }

    @FXML
    public void toggleMenu() {
        System.out.println("Menu toggle clicado (implementa se precisares)");
    }
}
