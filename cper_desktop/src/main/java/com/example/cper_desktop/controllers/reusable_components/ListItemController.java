package com.example.cper_desktop.controllers.reusable_components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;

import java.util.List;
import java.util.function.Consumer;

public class ListItemController {

    @FXML private StackPane listLayout;
    @FXML private HBox contentBox;

    private Consumer<ListItemController> onClickAction;

    public void setData(List<String> fields, Consumer<ListItemController> onClick) {
        contentBox.getChildren().clear();
        this.onClickAction = onClick;

        for (String text : fields) {
            Label label = new Label(text);
            label.setMaxWidth(Double.MAX_VALUE);

            StackPane wrapper = createWrapper(label);
            HBox.setHgrow(wrapper, Priority.ALWAYS);
            contentBox.getChildren().add(wrapper);
        }

        applyInteractivity();
    }

    public void setDataComLarguras(List<String> fields, List<Double> larguras, Consumer<ListItemController> onClick) {
        if (fields.size() != larguras.size()) {
            throw new IllegalArgumentException("Tamanho da lista de campos e larguras deve ser igual.");
        }

        contentBox.getChildren().clear();
        this.onClickAction = onClick;

        for (int i = 0; i < fields.size(); i++) {
            Label label = new Label(fields.get(i));
            label.setMaxWidth(Double.MAX_VALUE);

            StackPane wrapper = createWrapper(label);
            wrapper.setMinWidth(larguras.get(i));
            wrapper.setMaxWidth(larguras.get(i));
            contentBox.getChildren().add(wrapper);
        }

        applyInteractivity();
    }

    private StackPane createWrapper(Label label) {
        StackPane wrapper = new StackPane(label);
        wrapper.setMinHeight(30);
        wrapper.setPrefHeight(30);
        wrapper.setMaxHeight(30);
        wrapper.setStyle(wrapperStyle);
        StackPane.setMargin(label, new Insets(0, 10, 0, 10));
        return wrapper;
    }

    private void applyInteractivity() {
        listLayout.setStyle(baseStyle);
        listLayout.setOnMouseClicked(this::handleClick);
        listLayout.setOnMouseEntered(e -> listLayout.setStyle(hoverStyle));
        listLayout.setOnMouseExited(e -> listLayout.setStyle(baseStyle));
    }

    private void handleClick(MouseEvent event) {
        if (onClickAction != null) {
            onClickAction.accept(this);
        }
    }

    private final String baseStyle = """
                -fx-border-radius: 12;
                -fx-border-width: 2;
                -fx-border-color: #e1e3e1;
                -fx-border-style: solid;
                -fx-background-color: #e1e3e1;
                -fx-background-radius: 15;
                -fx-cursor: hand;
            """;

    private final String hoverStyle = """
                -fx-border-radius: 12;
                -fx-border-width: 2;
                -fx-border-color: #cfd1cf;
                -fx-border-style: solid;
                -fx-background-color: #cfd1cf;
                -fx-background-radius: 15;
                -fx-cursor: hand;
            """;

    private final String wrapperStyle = """
                -fx-background-radius: 12;
                -fx-background-color: #ffffff;
                -fx-background-insets: 0;
                -fx-border-radius: 10;
                -fx-border-width: 1;
                -fx-border-color: transparent;
            """;
}