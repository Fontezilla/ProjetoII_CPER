package com.example.cper_desktop.controllers.reusable_components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;

public class MenuButtonItem extends VBox {

    private final Button button;

    public MenuButtonItem(String text, Runnable onClick) {
        this.setSpacing(0);
        this.setPadding(new Insets(0));
        this.getStyleClass().add("menu-button-item");

        button = new Button(text);
        button.setPrefHeight(40);
        button.setMaxWidth(Double.MAX_VALUE);
        button.getStyleClass().add("menu-button");

        button.setOnAction(e -> onClick.run());

        Line divider = new Line();
        divider.setStartX(0);
        divider.setEndX(315);
        divider.setStrokeWidth(2);
        divider.getStyleClass().add("menu-divider");

        StackPane dividerWrapper = new StackPane(divider);
        dividerWrapper.setPrefWidth(180);
        dividerWrapper.setMinHeight(4);

        this.getChildren().addAll(button, dividerWrapper);
    }

    public Button getButton() {
        return button;
    }
}
