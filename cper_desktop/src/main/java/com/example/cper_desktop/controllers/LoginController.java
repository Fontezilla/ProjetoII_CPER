package com.example.cper_desktop.controllers;

import com.example.cper_core.dtos.funcionario.FuncionarioLoginDto;
import com.example.cper_core.dtos.funcionario.FuncionarioLoginResponseDto;
import com.example.cper_core.services.interfaces.IFuncionarioService;
import com.example.cper_core.utils.JwtUtils;
import com.example.cper_desktop.utils.SessionStorage;
import io.jsonwebtoken.Claims;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class LoginController {

    @FXML private TextField username;
    @FXML private PasswordField password;

    private final IFuncionarioService funcionarioService;

    @Autowired
    public LoginController(IFuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @FXML
    public void handleLogin(ActionEvent event) {
        String email = username.getText();
        String pass = password.getText();

        FuncionarioLoginDto loginDto = new FuncionarioLoginDto(pass, email);
        Optional<FuncionarioLoginResponseDto> login = funcionarioService.login(loginDto);

        if (login.isPresent()) {
            String token = login.get().getToken();

            try {
                Claims claims = JwtUtils.extractClaims(token);

                // Extrair informações do token
                String nome = claims.get("nome", String.class);
                String tipo = claims.get("tipo", String.class);
                List<String> setores = claims.get("setores", List.class); // pode ser null

                // Guardar sessão
                SessionStorage.getInstance().setToken(token);
                SessionStorage.getInstance().setNome(nome);
                SessionStorage.getInstance().setTipo(tipo);
                SessionStorage.getInstance().setSetores(setores);

                openDashboard();

            } catch (Exception e) {
                showError("Erro ao decodificar o token JWT.");
                e.printStackTrace();
            }

        } else {
            showError("Email ou password incorretos.");
        }
    }

    private void openDashboard() {
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/views/main.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("CPER Desktop - Principal");
            stage.show();

            Stage loginStage = (Stage) username.getScene().getWindow();
            loginStage.close();

        } catch (IOException e) {
            showError("Erro ao abrir o painel principal.");
            e.printStackTrace();
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de Autenticação");
        alert.setHeaderText("Falha ao autenticar");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}