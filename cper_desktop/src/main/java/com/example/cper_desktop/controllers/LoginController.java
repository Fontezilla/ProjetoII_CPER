package com.example.cper_desktop.controllers;

import com.example.cper_core.dtos.login.LoginRequestDto;
import com.example.cper_core.dtos.login.LoginResponseDto;
import com.example.cper_core.enums.JwtTipoUtilizador;
import com.example.cper_core.services.AuthService;
import com.example.cper_desktop.utils.Navigation;
import com.example.cper_desktop.utils.SessionStorage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<JwtTipoUtilizador> tipoCombo;
    @FXML private Label errorLabel;

    private final AuthService authService;

    // Injeção via construtor (Spring Boot cuida disso)
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @FXML
    public void initialize() {
        tipoCombo.getItems().setAll(JwtTipoUtilizador.values());
    }

    @FXML
    public void onLoginButtonClick(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        JwtTipoUtilizador tipo = tipoCombo.getValue();

        if (email.isBlank() || password.isBlank() || tipo == null) {
            errorLabel.setText("Todos os campos são obrigatórios.");
            return;
        }

        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setEmail(email);
        requestDto.setPassword(password);

        try {
            LoginResponseDto response = authService.login(tipo, requestDto);

            // Guarda dados em sessão (podes adaptar)
            SessionStorage.setToken(response.getToken());
            SessionStorage.setTipo(response.getTipo());
            SessionStorage.setSetorPrincipal(response.getSetorPrincipal());
            SessionStorage.setSetoresAssociados(response.getSetoresAssociados());

            Platform.runLater(() -> Navigation.goTo("dashboard.fxml"));

        } catch (RuntimeException e) {
            errorLabel.setText(e.getMessage());
        } catch (Exception e) {
            errorLabel.setText("Erro inesperado durante o login.");
        }
    }
}