package com.example.cper_desktop.controllers;

import com.example.cper_core.dtos.auth.UpdatePassword;
import com.example.cper_core.dtos.endereco.EnderecoDetailsExtendedDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDetailsExtendedDto;
import com.example.cper_core.dtos.morada.MoradaSimplesDto;
import com.example.cper_core.enums.Setor;
import com.example.cper_core.services.interfaces.*;
import com.example.cper_desktop.controllers.reusable_components.LoadingOverlayController;
import com.example.cper_desktop.controllers.reusable_components.ToastNotificationController;
import com.example.cper_desktop.utils.ReusableComponentsAware;
import com.example.cper_desktop.utils.SessionStorage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class PerfilController implements ReusableComponentsAware {

    @FXML private StackPane basePane;
    @FXML private Label funcionario_id;
    @FXML private TextField funcionario_nome;
    @FXML private TextField funcionario_email;
    @FXML private TextField funcionario_cargo;
    @FXML private TextField funcionario_nif;
    @FXML private TextField funcionario_telefone;
    @FXML private TextField funcionario_dtNascimento;
    @FXML private TextField funcionario_morada;
    @FXML private TextField funcionario_salario;
    @FXML private TextField funcionario_dtContratacao;
    @FXML private TextField funcionario_estado;

    @FXML private ScrollPane mainPane;
    @FXML private AnchorPane passePopup;
    @FXML private Button closePopupBtn;
    @FXML private Button salvarPopupBtn;
    @FXML private Button cancelarPopupBtn;

    @FXML private TextField passwordAtualField;
    @FXML private TextField novaPasswordField;
    @FXML private TextField confirmarPasswordField;

    @FXML private Label passePop_erro1;
    @FXML private Label passePop_erro2;
    @FXML private Label passePop_erro3;

    @FXML private HBox normalBox;
    @FXML private Button editarBtn;
    @FXML private Button redefinirBtn;
    @FXML private HBox editarBox;
    @FXML private Button cancelarBtn;
    @FXML private Button salvarBtn;
    @FXML private Label erroGeralLabel;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.of("pt", "PT"));

    private final Validator validator;
    private final IFuncionarioService funcionarioService;
    private final IEnderecoService enderecoService;
    private final ILocalidadeService localidadeService;
    private final IMoradaService moradaService;

    private Integer userId;
    private Set<Integer> roleAtual;
    private FuncionarioDetailsExtendedDto funcionario;

    private LoadingOverlayController loadingOverlayController;
    private ToastNotificationController toastNotificationController;

    @Override
    public void setLoadingOverlayController(LoadingOverlayController controller) {
        this.loadingOverlayController = controller;
    }

    @Override
    public void setToastNotificationController(ToastNotificationController controller) {
        this.toastNotificationController = controller;
    }

    public PerfilController(Validator validator,
                            IFuncionarioService funcionarioService,
                            IEnderecoService enderecoService,
                            ILocalidadeService localidadeService,
                            IMoradaService moradaService) {
        this.validator = validator;
        this.funcionarioService = funcionarioService;
        this.enderecoService = enderecoService;
        this.localidadeService = localidadeService;
        this.moradaService = moradaService;
    }

    private final EventHandler<ScrollEvent> scrollBlocker = ScrollEvent::consume;

    @FXML
    public void initialize() {
        userId = SessionStorage.getUtilizadorId();
        if (userId == null) return;

        try {
            roleAtual = getTodosSetores();
            funcionario = funcionarioService.getExtendedById(userId);
            carregarFuncionario(funcionario);
            aplicarPermissoes(false);
        } catch (Exception ignored) {
        }

        basePane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.getStylesheets().add(
                        Objects.requireNonNull(getClass()
                                .getResource("/styles/TextFieldEditable.css")).toExternalForm()
                );
            }
        });

        basePane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.getStylesheets().add(
                        Objects.requireNonNull(getClass()
                                .getResource("/styles/Toast.css")).toExternalForm()
                );
            }
        });

        passePopup.setVisible(false);
        passePopup.setManaged(false);

        redefinirBtn.setOnAction(e -> mostrarPopupRedefinirPasse());
        closePopupBtn.setOnAction(e -> fecharPopup());
        salvarPopupBtn.setOnAction(e -> confirmarAlteracaoPassword());
        cancelarPopupBtn.setOnAction(e -> fecharPopup());

        editarBtn.setOnAction(e -> ativarModoEdicao());
        cancelarBtn.setOnAction(e -> cancelarEdicao());
        salvarBtn.setOnAction(e -> salvarEdicao());

        editarBox.setVisible(false);
    }

    private void showSuccessToast(String titulo, String descricao) {
        toastNotificationController.showMessage(titulo, descricao, "success");
    }

    private void showErrorToast(String titulo, String descricao) {
        toastNotificationController.showMessage(titulo, descricao, "error");
    }

    public static Set<Integer> getTodosSetores() {
        Set<Integer> setores = new HashSet<>();
        Optional.ofNullable(SessionStorage.getSetoresAssociados()).ifPresent(setores::addAll);
        Optional.ofNullable(SessionStorage.getSetorPrincipal()).filter(id -> id != 0).ifPresent(setores::add);
        return setores;
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
            return e.getRua() + ", nº" + f.getNPorta() + ", " + l.getCodigoPostal() + " " + l.getNome();
        } catch (Exception ex) {
            return "N/A";
        }
    }

    private MoradaSimplesDto extrairMoradaSimples(String moradaCompleta) {
        if (moradaCompleta == null || !moradaCompleta.matches(".+,\\s*nº\\d+,\\s*\\d{4}-\\d{3}\\s+.+")) return null;
        try {
            String[] partes = moradaCompleta.split(",");
            String rua = partes[0].trim();
            String porta = partes[1].replaceAll("[^0-9]", "").trim();
            String codLocalidade = partes[2].trim();
            int espaco = codLocalidade.indexOf(' ');
            if (espaco < 0) return null;
            String codigoPostal = codLocalidade.substring(0, espaco).trim();
            String localidade = codLocalidade.substring(espaco + 1).trim();
            MoradaSimplesDto dto = new MoradaSimplesDto();
            dto.setRua(rua);
            dto.setCodigoPostal(codigoPostal);
            dto.setNomeLocalidade(localidade);
            funcionario.setNPorta(porta);
            return dto;
        } catch (Exception e) {
            return null;
        }
    }

    private void aplicarPermissoes(boolean editar) {
        boolean isAdmin = roleAtual.contains(Setor.ADMINISTRATIVO.getId());
        configurarCampo(funcionario_nome, editar && isAdmin);
        configurarCampo(funcionario_cargo, editar && isAdmin);
        configurarCampo(funcionario_email, editar);
        configurarCampo(funcionario_nif, editar && isAdmin);
        configurarCampo(funcionario_telefone, editar);
        configurarCampo(funcionario_morada, editar);
        configurarCampo(funcionario_salario, editar && isAdmin);
        configurarCampo(funcionario_estado, editar && isAdmin);
        configurarCampo(funcionario_dtNascimento, editar && isAdmin);
        configurarCampo(funcionario_dtContratacao, editar && isAdmin);
    }

    private void configurarCampo(TextField campo, boolean editavel) {
        campo.setEditable(editavel);
        campo.getStyleClass().removeAll("editable", "readonly");
        campo.getStyleClass().add(editavel ? "editable" : "readonly");
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
            carregarFuncionario(funcionario);
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

        StringBuilder erros = new StringBuilder("Erro ao salvar alterações:\n");
        boolean temErro = false;

        String moradaRaw = funcionario_morada.getText();
        boolean moradaValida = moradaFormatoValido(moradaRaw);

        FuncionarioDetailsExtendedDto atualizado = getFuncionarioDetailsExtendedDto(moradaValida);

        Set<ConstraintViolation<FuncionarioDetailsExtendedDto>> todasViolacoes =
                validator.validate(atualizado, com.example.cper_core.dtos.OnUpdate.class);

        BeanWrapper wrapper = new BeanWrapperImpl(atualizado);

        Set<ConstraintViolation<FuncionarioDetailsExtendedDto>> violacoesReais = todasViolacoes.stream()
                .filter(v -> {
                    Object valor = wrapper.getPropertyValue(v.getPropertyPath().toString());
                    return valor != null && !(valor instanceof String s && s.isBlank());
                })
                .collect(Collectors.toSet());

        for (ConstraintViolation<?> v : violacoesReais) {
            erros.append(" • ").append(v.getPropertyPath()).append(": ").append(v.getMessage()).append("\n");
            temErro = true;
        }

        if (!moradaValida) {
            erros.append(" • morada: A morada deve estar no formato \"Rua, nº123, 1000-001 Localidade\"\n");
            temErro = true;
        }

        if (temErro) {
            erroGeralLabel.setText(erros.toString());
            erroGeralLabel.setVisible(true);
            loadingOverlayController.hide();
            return;
        }

        try {
            funcionarioService.update(userId, atualizado);
            funcionario = funcionarioService.getExtendedById(userId);
            carregarFuncionario(funcionario);

            Platform.runLater(() -> {
                cancelarEdicao();
                showSuccessToast("Guardado", "As alterações foram guardadas com sucesso.");
                loadingOverlayController.hide(); // Após UI atualizada
            });
        } catch (Exception e) {
            String mensagemErro = e.getMessage() != null ? e.getMessage() : "Erro inesperado ao salvar alterações.";
            erroGeralLabel.setText("Erro ao salvar alterações: " + mensagemErro);
            erroGeralLabel.setVisible(true);
            loadingOverlayController.hide();
        }
    }

    private boolean moradaFormatoValido(String morada) {
        return morada != null && morada.matches(".+,\\s*nº\\d+,\\s*\\d{4}-\\d{3}\\s+.+");
    }

    private @NotNull FuncionarioDetailsExtendedDto getFuncionarioDetailsExtendedDto(boolean moradaValida) {
        FuncionarioDetailsExtendedDto atualizado = new FuncionarioDetailsExtendedDto();
        atualizado.setEmail(funcionario_email.getText());
        atualizado.setTelefone(funcionario_telefone.getText());

        if (moradaValida) {
            MoradaSimplesDto moradaDto = extrairMoradaSimples(funcionario_morada.getText());
            if (moradaDto != null && funcionario.getNPorta() != null) {
                EnderecoDetailsExtendedDto endereco = moradaService.criarEnderecoSeNecessario(moradaDto);
                atualizado.setEndereco(endereco);
                atualizado.setNPorta(funcionario.getNPorta());
            }
        }

        return atualizado;
    }

    @FXML
    private void mostrarPopupRedefinirPasse() {
        passePopup.setVisible(true);
        passePopup.setManaged(true);
        mainPane.addEventFilter(ScrollEvent.ANY, scrollBlocker);
    }

    public void fecharPopup() {
        passePopup.setVisible(false);
        passePopup.setManaged(false);
        mainPane.removeEventFilter(ScrollEvent.ANY, scrollBlocker);
    }

    @FXML
    private void confirmarAlteracaoPassword() {
        limparErrosVisuais();
        loadingOverlayController.show();

        UpdatePassword updatePassword = new UpdatePassword();

        String newPass = novaPasswordField.getText();
        String confirm = confirmarPasswordField.getText();

        if (!Objects.equals(newPass, confirm)) {
            passePop_erro3.setText("As passwords não coincidem.");
            passePop_erro3.setVisible(true);
            confirmarPasswordField.getStyleClass().add("campo-invalido");
            loadingOverlayController.hide();
            return;
        }

        updatePassword.setNewPassword(newPass);
        updatePassword.setCurrentPassword(passwordAtualField.getText());

        try {
            funcionarioService.updatePassword(userId, updatePassword);
            fecharPopup();
            showSuccessToast("Guardado", "A palavra-passe foi alterada com sucesso.");
        } catch (IllegalArgumentException e) {
            passePop_erro1.setText(e.getMessage());
            passePop_erro1.setVisible(true);
            passwordAtualField.getStyleClass().add("campo-invalido");
        } catch (Exception e) {
            String errorMessage = Optional.ofNullable(e.getMessage())
                    .orElse("Erro inesperado ao alterar a palavra-passe.");
            passePop_erro3.setWrapText(true);
            passePop_erro3.setMaxWidth(430);
            passePop_erro3.setText(errorMessage);
            passePop_erro3.setVisible(true);
        } finally {
            loadingOverlayController.hide();
        }
    }

    private void limparErrosVisuais() {
        passePop_erro1.setText("");
        passePop_erro2.setText("");
        passePop_erro3.setText("");
        passePop_erro1.setVisible(false);
        passePop_erro2.setVisible(false);
        passePop_erro3.setVisible(false);
        passwordAtualField.getStyleClass().remove("campo-invalido");
        novaPasswordField.getStyleClass().remove("campo-invalido");
        confirmarPasswordField.getStyleClass().remove("campo-invalido");
    }
}