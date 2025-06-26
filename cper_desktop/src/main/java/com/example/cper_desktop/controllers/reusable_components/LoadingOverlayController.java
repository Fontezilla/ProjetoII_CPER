package com.example.cper_desktop.controllers.reusable_components;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class LoadingOverlayController {

    @FXML private AnchorPane overlayRoot;

    public void show() {
        overlayRoot.setVisible(true);
        overlayRoot.setManaged(true);
        overlayRoot.setMouseTransparent(false);
        overlayRoot.setOpacity(1);
    }


    public void hide() {
        overlayRoot.setVisible(false);
        overlayRoot.setManaged(false);
        overlayRoot.setMouseTransparent(true);
    }

    public AnchorPane getView() {
        return overlayRoot;
    }
}