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

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @FXML
    public void initialize() {
    }

    @FXML
    public void onLoginButtonClick(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isBlank() || password.isBlank()) {
            errorLabel.setText("Todos os campos são obrigatórios.");
            return;
        }

        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setEmail(email);
        requestDto.setPassword(password);

        try {
            LoginResponseDto response = authService.login(JwtTipoUtilizador.FUNCIONARIO, requestDto);

            SessionStorage.initializeFromToken(response.getToken());

            Platform.runLater(() -> Navigation.goTo("layouts/BaseLayout.fxml"));

        } catch (RuntimeException e) {
            errorLabel.setText(e.getMessage());
        } catch (Exception e) {
            errorLabel.setText("Erro inesperado durante o login.");
        }
    }
}