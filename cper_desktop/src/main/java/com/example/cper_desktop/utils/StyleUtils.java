package com.example.cper_desktop.utils;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.BooleanSupplier;

public class StyleUtils {

    private static final DateTimeFormatter DD_MM_YYYY = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static final String STYLE_EDITABLE = "editable-field";
    public static final String STYLE_READONLY = "readonly-field";
    public static final String STYLE_INVALID = "invalid-field";
    public static final String STYLE_HIDE_ARROW = "hide-arrow";
    public static final String STYLE_DATE_TRANSPARENT = "custom-transparent";
    public static final String STYLE_BUTTON_YELLOW = "button-yellow";
    public static final String STYLE_HOVER_TRANSPARENT = "hover-transparent";

    public static void applyEditStyle(Control control, boolean editable) {
        control.getStyleClass().removeAll(STYLE_EDITABLE, STYLE_READONLY, STYLE_INVALID);
        control.getStyleClass().add(editable ? STYLE_EDITABLE : STYLE_READONLY);

        if (control instanceof ComboBox<?>) {
            toggleHideArrow(control, !editable);
        }

        if (control instanceof DatePicker datePicker) {
            toggleHideArrow(control, !editable);
            if (!datePicker.getStyleClass().contains(STYLE_DATE_TRANSPARENT)) {
                datePicker.getStyleClass().add(STYLE_DATE_TRANSPARENT);
            }
        }
    }

    public static void markInvalid(Control control) {
        control.getStyleClass().removeAll(STYLE_EDITABLE, STYLE_READONLY, STYLE_INVALID);
        control.getStyleClass().add(STYLE_INVALID);
    }

    public static void clearInvalid(Control control, boolean editable) {
        applyEditStyle(control, editable);
    }

    private static void toggleHideArrow(Control control, boolean hide) {
        if (hide) {
            if (!control.getStyleClass().contains(STYLE_HIDE_ARROW)) {
                control.getStyleClass().add(STYLE_HIDE_ARROW);
            }
        } else {
            control.getStyleClass().remove(STYLE_HIDE_ARROW);
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
        if (!button.getStyleClass().contains(STYLE_BUTTON_YELLOW)) {
            button.getStyleClass().add(STYLE_BUTTON_YELLOW);
        }
    }

    public static void applyTransparentHoverEffect(Region region) {
        if (!region.getStyleClass().contains(STYLE_HOVER_TRANSPARENT)) {
            region.getStyleClass().add(STYLE_HOVER_TRANSPARENT);
        }
    }

    public static void applyStackPaneHoverEffect(StackPane stackPane) {
        stackPane.setCursor(Cursor.HAND);
        applyTransparentHoverEffect(stackPane);
    }

    public static void applyButtonHoverEffect(Button button) {
        applyTransparentHoverEffect(button);
    }

    public static void ensureStylesheetApplied(Region node, String cssPath) {
        node.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                URL resource = StyleUtils.class.getResource(cssPath);
                if (resource == null) {
                    System.err.println("Ficheiro CSS não encontrado: " + cssPath);
                    return;
                }

                String path = resource.toExternalForm();
                if (!newScene.getStylesheets().contains(path)) {
                    newScene.getStylesheets().add(path);
                }
            }
        });
    }


    public static void applyEditStylesOnSceneAvailable(Region node, Control... controls) {
        node.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                for (Control c : controls) {
                    applyEditStyle(c, false);
                }
            }
        });
    }

    public static void applyTextAreaDefaultStyle(TextArea textArea) {
        textArea.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                ensureStylesheetApplied(textArea, "/styles/style-fixed.css");

                if (!textArea.getStyleClass().contains("text-area")) {
                    textArea.getStyleClass().add("text-area");
                }
            }
        });

        if (textArea.getScene() != null) {
            ensureStylesheetApplied(textArea, "/styles/style-fixed.css");

            if (!textArea.getStyleClass().contains("text-area")) {
                textArea.getStyleClass().add("text-area");
            }
        }
    }

    public static StringConverter<LocalDate> getDatePickerConverter() {
        return new StringConverter<>() {
            @Override
            public String toString(LocalDate date) {
                return date != null ? DD_MM_YYYY.format(date) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return (string == null || string.isBlank()) ? null : LocalDate.parse(string, DD_MM_YYYY);
            }
        };
    }
}
