package com.example.cper_desktop.controllers;

import com.example.cper_core.dtos.login.LoginRequestDto;
import com.example.cper_core.dtos.login.LoginResponseDto;
import com.example.cper_core.enums.JwtTipoUtilizador;
import com.example.cper_desktop.utils.Navigation;
import com.example.cper_desktop.utils.SessionStorage;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Set;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<JwtTipoUtilizador> tipoCombo;
    @FXML private Label errorLabel;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

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
            String requestBody = mapper.writeValueAsString(requestDto);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/auth/login?type=" + tipo.name()))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(response -> {
                        try {
                            LoginResponseDto responseDto = mapper.readValue(response.body(), LoginResponseDto.class);

                            SessionStorage.setToken(responseDto.getToken());
                            SessionStorage.setTipo(responseDto.getTipo());
                            SessionStorage.setSetorPrincipal(responseDto.getSetorPrincipal());
                            SessionStorage.setSetoresAssociados(responseDto.getSetoresAssociados());

                            Platform.runLater(() -> {
                                Navigation.goTo("dashboard.fxml");
                            });

                        } catch (Exception e) {
                            Platform.runLater(() -> errorLabel.setText("Erro ao processar resposta."));
                        }
                    })
                    .exceptionally(e -> {
                        Platform.runLater(() -> errorLabel.setText("Falha na ligação com o servidor."));
                        return null;
                    });

        } catch (Exception e) {
            errorLabel.setText("Erro ao preparar pedido.");
        }
    }
}
