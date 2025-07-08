package com.example.cper_desktop.controllers;

import com.example.cper_core.dtos.OnUpdate;
import com.example.cper_core.dtos.auth.UpdatePassword;
import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDetailsExtendedDto;
import com.example.cper_core.enums.Setor;
import com.example.cper_core.services.interfaces.*;
import com.example.cper_desktop.controllers.base.AbstractDetailsController;
import com.example.cper_desktop.utils.*;
import jakarta.validation.Validator;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.example.cper_desktop.utils.FormatterUtils.*;
import static com.example.cper_desktop.utils.StyleUtils.*;

@Component
public class PerfilController extends AbstractDetailsController<FuncionarioDetailsExtendedDto> {

    @FXML private StackPane basePane;
    @FXML private ScrollPane mainPane;
    @FXML private AnchorPane passePopup;
    @FXML private Label funcionarioId, erroGeralLabel;
    @FXML private TextField funcionarioNome, funcionarioEmail, funcionarioCargo, funcionarioNif,
            funcionarioTelefone, funcionarioDtNascimento, funcionarioMorada,
            funcionarioSalario, funcionarioDtContratacao, funcionarioEstado;
    @FXML private Button editarBtn, redefinirBtn, cancelarBtn, salvarBtn;
    @FXML private HBox normalBox, editarBox;

    @FXML private Button closePopupBtn, salvarPopupBtn, cancelarPopupBtn;
    @FXML private TextField passwordAtualField, novaPasswordField, confirmarPasswordField;
    @FXML private Label passePop_erro1, passePop_erro2, passePop_erro3;

    private final IFuncionarioService funcionarioService;
    private final IEnderecoService enderecoService;
    private final ILocalidadeService localidadeService;
    private final IMoradaService moradaService;

    private FuncionarioDetailsExtendedDto funcionario;
    private Set<Integer> roleAtual;

    private final EventHandler<ScrollEvent> scrollBlocker = ScrollEvent::consume;

    public PerfilController(Validator validator,
                            IFuncionarioService funcionarioService,
                            IEnderecoService enderecoService,
                            ILocalidadeService localidadeService,
                            IMoradaService moradaService) {
        super(validator);
        this.funcionarioService = funcionarioService;
        this.enderecoService = enderecoService;
        this.localidadeService = localidadeService;
        this.moradaService = moradaService;
    }

    @Override
    protected void loadData() {
        roleAtual = PermissionUtils.getAllSetores();
        funcionario = funcionarioService.getExtendedById(id);

        funcionarioId.setText(value(funcionario.getCodigo()));
        funcionarioNome.setText(value(funcionario.getNome()));
        funcionarioEmail.setText(value(funcionario.getEmail()));
        funcionarioCargo.setText(value(funcionario.getCargo()));
        funcionarioNif.setText(value(funcionario.getNif()));
        funcionarioTelefone.setText(value(funcionario.getTelefone()));
        funcionarioDtNascimento.setText(date(funcionario.getDataNascimento()));
        funcionarioMorada.setText(formatSimpleAddress(
                funcionario.getEndereco() != null ? funcionario.getEndereco() : null,
                funcionario.getNPorta(), enderecoService, localidadeService));
        funcionarioSalario.setText(currency(funcionario.getSalario()));
        funcionarioDtContratacao.setText(date(funcionario.getDataContratacao()));
        funcionarioEstado.setText(label(funcionario.getEstado()));

        aplicarPermissoes(false);
    }

    @Override
    protected FuncionarioDetailsExtendedDto updateData() {
        String moradaRaw = funcionarioMorada.getText();
        boolean moradaValida = FormatterUtils.validFormatAddress(moradaRaw);

        FuncionarioDetailsExtendedDto dto = FormatterUtils.getDetails(
                new FuncionarioDetailsExtendedDto(),
                funcionarioEmail.getText(),
                funcionarioTelefone.getText(),
                funcionario.getNPorta(),
                moradaRaw,
                moradaValida,
                moradaService,
                extended -> new EnderecoDto(extended.getId()),
                FuncionarioDetailsExtendedDto::setEndereco,
                FuncionarioDetailsExtendedDto::setNPorta
        );

        dto.setId(funcionario.getId());
        dto.setNome(funcionarioNome.getText());
        dto.setCargo(funcionarioCargo.getText());
        dto.setNif(funcionarioNif.getText());
        dto.setSalario(FormatterUtils.parseDecimal(funcionarioSalario.getText()));
        dto.setDataNascimento(funcionario.getDataNascimento());
        dto.setDataContratacao(funcionario.getDataContratacao());
        dto.setEstado(funcionario.getEstado());

        return dto;
    }

    @Override
    protected void postInitialize() {
        this.id = SessionStorage.getUtilizadorId();

        ensureStylesheetApplied(basePane, "/styles/style-fixed.css");
        ensureStylesheetApplied(basePane, "/styles/Toast.css");

        applyEditStylesOnSceneAvailable(basePane,
                funcionarioNome, funcionarioEmail, funcionarioCargo, funcionarioNif,
                funcionarioTelefone, funcionarioDtNascimento, funcionarioMorada,
                funcionarioSalario, funcionarioDtContratacao, funcionarioEstado
        );

        configurarPopup();
        configurarBotoes();
    }

    private void aplicarPermissoes(boolean editar) {
        boolean isAdmin = roleAtual.contains(Setor.ADMINISTRATIVO.getId());

        Map<Control, Boolean> permissoes = Map.of(
                funcionarioNome, isAdmin,
                funcionarioCargo, isAdmin,
                funcionarioEmail, true,
                funcionarioNif, isAdmin,
                funcionarioTelefone, true,
                funcionarioMorada, true,
                funcionarioSalario, isAdmin,
                funcionarioEstado, isAdmin,
                funcionarioDtNascimento, isAdmin,
                funcionarioDtContratacao, isAdmin
        );

        applyPermissions(permissoes, editar);
    }

    private void configurarPopup() {
        passePopup.setVisible(false);
        passePopup.setManaged(false);
    }

    private void configurarBotoes() {
        redefinirBtn.setOnAction(e -> mostrarPopupRedefinirPasse());
        closePopupBtn.setOnAction(e -> fecharPopup());
        cancelarPopupBtn.setOnAction(e -> fecharPopup());
        salvarPopupBtn.setOnAction(e -> confirmarAlteracaoPassword());

        editarBtn.setOnAction(e -> ativarModoEdicao());
        cancelarBtn.setOnAction(e -> cancelarEdicao());
        salvarBtn.setOnAction(e -> salvarEdicao());

        editarBox.setVisible(false);
    }

    private void ativarModoEdicao() {
        aplicarPermissoes(true);
        normalBox.setVisible(false);
        editarBox.setVisible(true);
    }

    private void cancelarEdicao() {
        loadingOverlayController.show();
        Platform.runLater(() -> {
            aplicarPermissoes(false);
            loadData();
            normalBox.setVisible(true);
            editarBox.setVisible(false);
            erroGeralLabel.setText("");
            loadingOverlayController.hide();
        });
    }

    private void salvarEdicao() {
        loadingOverlayController.show();
        erroGeralLabel.setText("");
        erroGeralLabel.setVisible(false);

        FuncionarioDetailsExtendedDto atualizado = updateData();
        String moradaRaw = funcionarioMorada.getText();
        boolean moradaValida = validFormatAddress(moradaRaw);

        var result = ValidationUtils.validar(atualizado, validator, OnUpdate.class);

        if (!result.valido() || !moradaValida) {
            StringBuilder erros = ValidationUtils.generateErrorMessage(result.violacoes());
            if (!moradaValida) {
                validateAddress(moradaRaw, erros, funcionarioMorada);
            }
            erroGeralLabel.setText(erros.toString());
            erroGeralLabel.setVisible(true);
            loadingOverlayController.hide();
            return;
        }

        try {
            funcionarioService.update(id, atualizado);
            funcionario = funcionarioService.getExtendedById(id);

            Platform.runLater(() -> {
                cancelarEdicao();
                showSuccessToast("Guardado", "As alterações foram guardadas com sucesso.");
                loadingOverlayController.hide();
            });
        } catch (Exception e) {
            String mensagemErro = Optional.ofNullable(e.getMessage()).orElse("Erro inesperado ao salvar alterações.");
            erroGeralLabel.setText("Erro ao salvar alterações: " + mensagemErro);
            erroGeralLabel.setVisible(true);
            loadingOverlayController.hide();
        }
    }

    @FXML
    private void mostrarPopupRedefinirPasse() {
        passePopup.setVisible(true);
        passePopup.setManaged(true);
        mainPane.addEventFilter(ScrollEvent.ANY, scrollBlocker);
    }

    @FXML
    private void fecharPopup() {
        passePopup.setVisible(false);
        passePopup.setManaged(false);
        mainPane.removeEventFilter(ScrollEvent.ANY, scrollBlocker);
    }

    @FXML
    private void confirmarAlteracaoPassword() {
        limparErrosVisuais();
        loadingOverlayController.show();

        String newPass = novaPasswordField.getText();
        String confirm = confirmarPasswordField.getText();

        if (!Objects.equals(newPass, confirm)) {
            passePop_erro3.setText("As passwords não coincidem.");
            passePop_erro3.setVisible(true);
            markInvalid(confirmarPasswordField);
            loadingOverlayController.hide();
            return;
        }

        UpdatePassword updatePassword = new UpdatePassword();
        updatePassword.setNewPassword(newPass);
        updatePassword.setCurrentPassword(passwordAtualField.getText());

        try {
            funcionarioService.updatePassword(id, updatePassword);
            fecharPopup();
            showSuccessToast("Guardado", "A palavra-passe foi alterada com sucesso.");
        } catch (IllegalArgumentException e) {
            passePop_erro1.setText(e.getMessage());
            passePop_erro1.setVisible(true);
            markInvalid(passwordAtualField);
        } catch (Exception e) {
            String errorMessage = Optional.ofNullable(e.getMessage()).orElse("Erro inesperado ao alterar a palavra-passe.");
            passePop_erro3.setWrapText(true);
            passePop_erro3.setMaxWidth(430);
            passePop_erro3.setText(errorMessage);
            passePop_erro3.setVisible(true);
        } finally {
            loadingOverlayController.hide();
        }
    }

    private void limparErrosVisuais() {
        List.of(passePop_erro1, passePop_erro2, passePop_erro3).forEach(label -> {
            label.setVisible(false);
            label.setText("");
        });

        List.of(passwordAtualField, novaPasswordField, confirmarPasswordField)
                .forEach(field -> clearInvalid(field, true));
    }
}
