package com.example.cper_desktop.controllers;

import com.example.cper_desktop.utils.Navigation;
import com.example.cper_desktop.utils.SessionStorage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class DashboardController {

    @FXML
    private Label tipoLabel;

    @FXML
    private Label setorLabel;

    @FXML
    public void initialize() {
        tipoLabel.setText("Tipo: " + SessionStorage.getTipo().name());

        Integer setor = SessionStorage.getSetorPrincipal();
        if (setor != null) {
            setorLabel.setText("Setor Principal: " + setor);
        } else {
            setorLabel.setText("Setor não atribuído.");
        }
    }

    @FXML
    public void handleLogout() {
        SessionStorage.clear();
        Navigation.goTo("login.fxml");
    }
}