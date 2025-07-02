package com.example.cper_desktop.utils;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.time.LocalDate;
import java.util.function.BooleanSupplier;

public class StyleUtils {

    private static final String BASE_STYLE =
            "-fx-background-color: #ffffff;" +
                    "-fx-background-radius: 12;" +
                    "-fx-background-insets: 0;" +
                    "-fx-border-radius: 10;" +
                    "-fx-border-style: solid;" +
                    "-fx-border-width: 1;";

    private static final String STYLE_EDITABLE =
            BASE_STYLE + "-fx-border-color: #d4ab0d;";

    private static final String STYLE_READONLY =
            BASE_STYLE + "-fx-border-color: #ffffff;";

    private static final String STYLE_INVALID =
            BASE_STYLE + "-fx-border-color: #e74c3c;";

    private static final String INLINE_CSS =
            "data:text/css," +
                    ".combo-box.hide-arrow .arrow-button {" +
                    " -fx-shape: \"\";" +
                    " -fx-background-color: transparent;" +
                    " -fx-border-color: transparent;" +
                    " -fx-padding: 0;" +
                    "}" +
                    ".combo-box.hide-arrow .arrow {" +
                    " -fx-background-color: transparent;" +
                    "}" +
                    ".date-picker.hide-arrow .arrow-button {" +
                    " -fx-shape: \"\";" +
                    " -fx-background-color: transparent;" +
                    " -fx-border-color: transparent;" +
                    " -fx-padding: 0;" +
                    "}" +
                    ".date-picker.hide-arrow .arrow {" +
                    " -fx-background-color: transparent;" +
                    "}" +
                    ".date-picker.custom-transparent {" +
                    " -fx-background-color: transparent;" +
                    " -fx-background-radius: 12;" +
                    " -fx-border-radius: 12;" +
                    " -fx-border-color: transparent;" +
                    " -fx-border-width: 1;" +
                    "}" +
                    ".date-picker.custom-transparent > .text-field {" +
                    " -fx-background-color: transparent;" +
                    " -fx-background-insets: 0;" +
                    " -fx-border-color: transparent;" +
                    " -fx-border-width: 0;" +
                    " -fx-background-radius: 12;" +
                    " -fx-padding: 4 6 4 6;" +
                    " -fx-max-width: 222;" +
                    "}" +
                    ".date-picker.custom-transparent > .arrow-button {" +
                    " -fx-background-color: transparent;" +
                    " -fx-border-color: transparent;" +
                    " -fx-padding: 0 4 0 0;" +
                    "}" +
                    ".date-picker.custom-transparent > .arrow-button > .arrow {" +
                    " -fx-padding: 4;" +
                    " -fx-translate-x: -4;" +
                    "}";

    public static void applyEditStyle(Control control, boolean editable) {
        control.setStyle(editable ? STYLE_EDITABLE : STYLE_READONLY);

        if (control instanceof ComboBox<?> comboBox) {
            toggleHideArrow(comboBox, !editable);
            injectInlineCSSIfNeeded(comboBox);
        }

        if (control instanceof DatePicker datePicker) {
            toggleHideArrow(datePicker, !editable);
            injectInlineCSSIfNeeded(datePicker);
        }
    }

    public static void markInvalid(Control control) {
        control.setStyle(STYLE_INVALID);
    }

    public static void clearInvalid(Control control, boolean editable) {
        applyEditStyle(control, editable);
    }

    private static void toggleHideArrow(Control control, boolean hide) {
        if (hide) {
            if (!control.getStyleClass().contains("hide-arrow")) {
                control.getStyleClass().add("hide-arrow");
            }
        } else {
            control.getStyleClass().remove("hide-arrow");
        }
    }

    private static void injectInlineCSSIfNeeded(Control control) {
        ChangeListener<Scene> sceneListener = (obs, oldScene, newScene) -> {
            if (newScene != null && !newScene.getStylesheets().contains(INLINE_CSS)) {
                newScene.getStylesheets().add(INLINE_CSS);
            }
        };

        if (control.getScene() != null) {
            sceneListener.changed(null, null, control.getScene());
        } else {
            control.sceneProperty().addListener(sceneListener);
        }
    }

    public static void bloquearDatePickerSeNaoEditavel(DatePicker datePicker, BooleanSupplier podeEditar) {
        datePicker.getEditor().setEditable(false);

        datePicker.getEditor().addEventFilter(KeyEvent.KEY_TYPED, e -> {
            if (!podeEditar.getAsBoolean()) e.consume();
        });

        datePicker.showingProperty().addListener((obs, wasShowing, isNowShowing) -> {
            if (isNowShowing && !podeEditar.getAsBoolean()) {
                Platform.runLater(datePicker::hide);
            }
        });

        datePicker.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                Platform.runLater(() -> {
                    var arrowButtons = datePicker.lookupAll(".arrow-button");
                    for (var node : arrowButtons) {
                        node.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
                            if (!podeEditar.getAsBoolean()) e.consume();
                        });
                    }
                });
            }
        });

        datePicker.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if (!podeEditar.getAsBoolean()) e.consume();
        });

        datePicker.getEditor().focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused && !podeEditar.getAsBoolean()) {
                Platform.runLater(() -> datePicker.getEditor().getParent().requestFocus());
            }
        });

        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(!podeEditar.getAsBoolean());
            }
        });
    }

    public static <T> void bloquearComboBoxSeNaoEditavel(ComboBox<T> comboBox, BooleanSupplier podeEditar) {
        comboBox.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (!podeEditar.getAsBoolean()) e.consume();
        });

        comboBox.showingProperty().addListener((obs, wasShowing, isNowShowing) -> {
            if (isNowShowing && !podeEditar.getAsBoolean()) {
                Platform.runLater(comboBox::hide);
            }
        });

        comboBox.getEditor().setEditable(false);

        comboBox.getEditor().addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (!podeEditar.getAsBoolean()) e.consume();
        });

        comboBox.getEditor().addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (!podeEditar.getAsBoolean()) e.consume();
        });

        comboBox.getEditor().focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused && !podeEditar.getAsBoolean()) {
                Platform.runLater(() -> comboBox.getEditor().getParent().requestFocus());
            }
        });
    }

    public static void applySubtleOverlayHoverEffect(Button button) {
        String baseStyle = """
            -fx-background-color: #ffd300;
            -fx-background-radius: 10;
            -fx-background-insets: 0;
            -fx-padding: 6 12;
            -fx-border-color: #d4ab0d;
            -fx-border-radius: 8;
            -fx-border-width: 2;
            -fx-border-style: solid;
            -fx-font-weight: bold;
            -fx-font-size: 14px;
            -fx-text-fill: black;
            -fx-cursor: hand;
        """;

        String hoverStyle = """
            -fx-background-color: #ffd300, #00000010;
            -fx-background-radius: 10, 10;
            -fx-background-insets: 0, 0;
            -fx-padding: 6 12;
            -fx-border-color: #d4ab0d;
            -fx-border-radius: 8;
            -fx-border-width: 2;
            -fx-border-style: solid;
            -fx-font-weight: bold;
            -fx-font-size: 14px;
            -fx-text-fill: black;
            -fx-cursor: hand;
        """;

        button.setStyle(baseStyle);
        button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
        button.setOnMouseExited(e -> button.setStyle(baseStyle));
    }

    public static void applyTransparentHoverEffect(Region region) {
        String baseStyle = "-fx-background-color: transparent; -fx-cursor: hand;";
        String hoverStyle = "-fx-background-color: #00000010; -fx-cursor: hand;";

        region.setStyle(baseStyle);
        region.setOnMouseEntered(e -> region.setStyle(hoverStyle));
        region.setOnMouseExited(e -> region.setStyle(baseStyle));
    }

    public static void applyStackPaneHoverEffect(StackPane stackPane) {
        stackPane.setCursor(Cursor.HAND);
        applyTransparentHoverEffect(stackPane);
    }

    public static void applyButtonHoverEffect(Button button) {
        applyTransparentHoverEffect(button);
    }
}