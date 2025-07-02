package com.example.cper_desktop.controllers.reusable_components;

import com.example.cper_desktop.controllers.reusable_components.interfaces.PaginatableListController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.NotNull;

public class PaginationBarController {

    @FXML private Button firstBtn;
    @FXML private Button prevBtn;
    @FXML private Button nextBtn;
    @FXML private Button lastBtn;
    @FXML private HBox pageBtnContainer;

    private PaginatableListController controller;
    private int maxVisiblePages = 5;

    public void initialize() {
        firstBtn.setOnAction(e -> goToPage(0));
        prevBtn.setOnAction(e -> goToPage(getCurrentPage() - 1));
        nextBtn.setOnAction(e -> goToPage(getCurrentPage() + 1));
        lastBtn.setOnAction(e -> goToPage(getTotalPages() - 1));

        applyHoverEffect(firstBtn);
        applyHoverEffect(prevBtn);
        applyHoverEffect(nextBtn);
        applyHoverEffect(lastBtn);
    }

    public void bindTo(PaginatableListController controller) {
        this.controller = controller;
        renderPagination();
    }

    public void updatePagination() {
        renderPagination();
    }

    private void goToPage(int page) {
        if (controller == null) return;

        int currentPage = getCurrentPage();
        int totalPages = getTotalPages();

        if (page >= 0 && page < totalPages && page != currentPage) {
            controller.onPageChanged(page);
            renderPagination();
        }
    }

    private void renderPagination() {
        pageBtnContainer.getChildren().clear();

        int currentPage = getCurrentPage();
        int totalPages = getTotalPages();

        if (totalPages <= 1) {
            Button onlyBtn = createPageButton(0);
            pageBtnContainer.getChildren().add(onlyBtn);
            updateNavButtonsState();
            return;
        }

        int half = maxVisiblePages / 2;
        int start = Math.max(0, currentPage - half);
        int end = Math.min(totalPages, start + maxVisiblePages);

        if (end - start < maxVisiblePages) {
            start = Math.max(0, end - maxVisiblePages);
        }

        if (start > 0) {
            pageBtnContainer.getChildren().add(createPageButton(0));
            if (start > 1) pageBtnContainer.getChildren().add(createEllipsis());
        }

        for (int i = start; i < end; i++) {
            pageBtnContainer.getChildren().add(createPageButton(i));
        }

        if (end < totalPages) {
            if (end < totalPages - 1) pageBtnContainer.getChildren().add(createEllipsis());
            pageBtnContainer.getChildren().add(createPageButton(totalPages - 1));
        }

        updateNavButtonsState();
    }

    private Button createPageButton(int page) {
        Button btn = new Button(String.valueOf(page + 1));
        btn.setMinSize(32, 32);
        btn.setPrefSize(32, 32);

        if (page == getCurrentPage()) {
            applySelectedPageStyle(btn);
        } else {
            applyHoverEffect(btn);
            btn.setOnAction(e -> goToPage(page));
        }

        return btn;
    }

    private Label createEllipsis() {
        Label lbl = new Label("...");
        applyEllipsisStyle(lbl);
        return lbl;
    }

    private void updateNavButtonsState() {
        int currentPage = getCurrentPage();
        int totalPages = getTotalPages();

        boolean isFirst = currentPage == 0;
        boolean isLast = currentPage >= totalPages - 1;

        firstBtn.setDisable(isFirst);
        prevBtn.setDisable(isFirst);
        nextBtn.setDisable(isLast);
        lastBtn.setDisable(isLast);

        setOpacityForButton(firstBtn, !isFirst);
        setOpacityForButton(prevBtn, !isFirst);
        setOpacityForButton(nextBtn, !isLast);
        setOpacityForButton(lastBtn, !isLast);
    }


    private void setOpacityForButton(Button button, boolean enabled) {
        if (button.getParent() instanceof StackPane stack) {
            stack.getChildren().stream()
                    .filter(node -> node instanceof ImageView)
                    .findFirst()
                    .ifPresent(image -> image.setOpacity(enabled ? 1.0 : 0.4));
        }
    }

    private int getCurrentPage() {
        return (controller != null) ? controller.getCurrentPage() : 0;
    }

    private int getTotalPages() {
        return (controller != null) ? controller.getTotalPages() : 1;
    }

    private void applyEllipsisStyle(Label label) {
        label.setStyle("""
        -fx-font-size: 16px;
        -fx-text-fill: #666666;
    """);
    }

    private void applySelectedPageStyle(@NotNull Button button) {
        button.setStyle("""
        -fx-background-color: #ffd300;
        -fx-border-color: #d4ab0d;
        -fx-border-radius: 5;
        -fx-background-radius: 5;
    """);
        button.setDisable(true);
    }

    private void applyHoverEffect(@NotNull Button button) {
        button.setStyle("""
        -fx-background-color: transparent;
        -fx-border-color: transparent;
        -fx-border-radius: 5;
        -fx-background-radius: 5;
    """);

        button.setOnMouseEntered(e -> button.setStyle("""
        -fx-background-color: #00000010;
        -fx-border-color: transparent;
        -fx-border-radius: 5;
        -fx-background-radius: 5;
    """));

        button.setOnMouseExited(e -> button.setStyle("""
        -fx-background-color: transparent;
        -fx-border-color: transparent;
        -fx-border-radius: 5;
        -fx-background-radius: 5;
    """));
    }
}