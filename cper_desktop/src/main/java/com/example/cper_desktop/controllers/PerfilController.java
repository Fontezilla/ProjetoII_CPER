package com.example.cper_desktop.controllers;

import com.example.cper_core.dtos.funcionario.FuncionarioDetailsExtendedDto;
import com.example.cper_core.services.interfaces.IEnderecoService;
import com.example.cper_core.services.interfaces.IFuncionarioService;
import com.example.cper_core.services.interfaces.ILocalidadeService;
import com.example.cper_desktop.utils.SessionStorage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.EventHandler;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class PerfilController {

    @FXML private Label funcionario_id;
    @FXML private Label funcionario_nome;
    @FXML private Label funcionario_email;
    @FXML private Label funcionario_cargo;
    @FXML private Label funcionario_nif;
    @FXML private Label funcionario_telefone;
    @FXML private Label funcionario_dtNascimento;
    @FXML private Label funcionario_morada;
    @FXML private Label funcionario_salario;
    @FXML private Label funcionario_dtContratacao;
    @FXML private Label funcionario_estado;

    @FXML private ScrollPane mainPane;
    @FXML private AnchorPane passePopup;
    @FXML private Button closePopup;
    @FXML private Button confirmar;
    @FXML private Button redefinir;

    @FXML private TextField passwordAtualField;
    @FXML private TextField novaPasswordField;
    @FXML private TextField confirmarPasswordField;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "PT"));

    private final IFuncionarioService funcionarioService;
    private final IEnderecoService enderecoService;
    private final ILocalidadeService localidadeService;

    public PerfilController(IFuncionarioService funcionarioService, IEnderecoService enderecoService, ILocalidadeService localidadeService) {
        this.funcionarioService = funcionarioService;
        this.enderecoService = enderecoService;
        this.localidadeService = localidadeService;
    }

    private final EventHandler<ScrollEvent> scrollBlocker = ScrollEvent::consume;

    @FXML
    public void initialize() {
        Integer userId = SessionStorage.getUtilizadorId();
        if (userId == null) {
            System.err.println("Erro: utilizador não autenticado.");
            return;
        }

        try {
            FuncionarioDetailsExtendedDto funcionario = funcionarioService.getExtendedById(userId);
            carregarFuncionario(funcionario);
        } catch (Exception e) {
            System.err.println("Erro ao carregar dados do funcionário: " + e.getMessage());
        }

        // Inicialmente esconder popup
        passePopup.setVisible(false);
        passePopup.setManaged(false);

        // Listeners dos botões
        closePopup.setOnAction(e -> fecharPopup());
        confirmar.setOnAction(e -> confirmarAlteracaoPassword());
    }

    public void carregarFuncionario(FuncionarioDetailsExtendedDto f) {
        funcionario_id.setText(valor(f.getId()));
        funcionario_nome.setText(valor(f.getNome()));
        funcionario_email.setText(valor(f.getEmail()));
        funcionario_cargo.setText(valor(f.getCargo()));
        funcionario_nif.setText(valor(f.getNif()));
        funcionario_telefone.setText(valor(f.getTelefone()));
        funcionario_dtNascimento.setText(data(f.getDataNascimento()));
        funcionario_morada.setText(moradaCompleta(f));
        funcionario_salario.setText(moeda(f.getSalario()));
        funcionario_dtContratacao.setText(data(f.getDataContratacao()));
        funcionario_estado.setText(f.getEstado() != null ? f.getEstado().getLabel() : "N/A");
    }

    private String valor(Object obj) {
        return obj == null ? "N/A" : obj.toString();
    }

    private String data(OffsetDateTime data) {
        return data == null ? "N/A" : data.format(dateFormatter);
    }

    private String moeda(BigDecimal valor) {
        return valor == null ? "N/A" : currencyFormatter.format(valor);
    }

    private String moradaCompleta(FuncionarioDetailsExtendedDto f) {
        if (f.getEndereco() == null || f.getEndereco().getId() == null || f.getNPorta() == null) return "N/A";

        try {
            var e = enderecoService.getExtendedById(f.getEndereco().getId());
            if (e == null || e.getLocalidade() == null || e.getLocalidade().getId() == null) return "N/A";

            var l = localidadeService.getExtendedById(e.getLocalidade().getId());
            if (l == null) return "N/A";

            return e.getRua() + ", " + f.getNPorta() + ", " + l.getCodigoPostal() + " " + l.getNome();
        } catch (Exception ex) {
            System.err.println("Erro ao obter morada completa: " + ex.getMessage());
            return "N/A";
        }
    }

    // === Popup Controls ===

    @FXML
    private void mostrarPopupRedefinirPasse() {
        passePopup.setVisible(true);
        passePopup.setManaged(true);

        // Desativar scroll e barras visíveis
        mainPane.setPannable(false);
        mainPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Bloquear scroll do rato
        mainPane.addEventFilter(ScrollEvent.ANY, scrollBlocker);
    }

    public void fecharPopup() {
        passePopup.setVisible(false);
        passePopup.setManaged(false);

        // Reativar scroll e barras
        mainPane.setPannable(true);
        mainPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        mainPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Reativar scroll do rato
        mainPane.removeEventFilter(ScrollEvent.ANY, scrollBlocker);
    }

    @FXML
    public void confirmarAlteracaoPassword() {
        // TODO: lógica de validação e envio de nova palavra-passe
        System.out.println("Password atual: " + passwordAtualField.getText());
        System.out.println("Nova password: " + novaPasswordField.getText());
        System.out.println("Confirmação: " + confirmarPasswordField.getText());

        fecharPopup();
    }
}
