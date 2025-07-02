package com.example.cper_desktop.controllers;

import com.example.cper_core.dtos.cliente.ClienteDetailsExtendedDto;
import com.example.cper_core.dtos.endereco.EnderecoDetailsExtendedDto;
import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaFiltroDto;
import com.example.cper_core.enums.EstadoCliente;
import com.example.cper_core.enums.Setor;
import com.example.cper_core.enums.TipoCliente;
import com.example.cper_core.services.interfaces.IClienteService;
import com.example.cper_core.services.interfaces.IEnderecoService;
import com.example.cper_core.services.interfaces.ILocalidadeService;
import com.example.cper_desktop.controllers.reusable_components.LoadingOverlayController;
import com.example.cper_desktop.controllers.reusable_components.ToastNotificationController;
import com.example.cper_desktop.utils.ReusableComponentsAware;
import com.example.cper_desktop.utils.SessionStorage;
import com.example.cper_desktop.utils.StyleUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.example.cper_desktop.utils.StyleUtils.*;

@Component
public class ClienteController implements ReusableComponentsAware {

    // --- FXML ---
    @FXML private StackPane basePane, backBtn;
    @FXML private Label funcionario_id;
    @FXML private TextField clienteNome, clienteNif, clienteEmail, clienteTelefone, clienteMorada, clienteDemanda,
            clienteConsumo, clienteDtCad;
    @FXML private DatePicker clienteDtNas;
    @FXML private ComboBox<TipoCliente> clienteTipo;
    @FXML private ComboBox<EstadoCliente> clienteEstado;
    @FXML private Button editarBtn, cancelarBtn, salvarBtn, solicitacoesBtn, contratosBtn, faturasBtn, historicoBtn;

    // --- Serviços ---
    private final IClienteService clienteService;
    private final IEnderecoService enderecoService;
    private final ILocalidadeService localidadeService;
    private final Validator validator;

    // --- Estado ---
    private Integer clienteId;
    private boolean editMode = false;

    // --- Componentes externos ---
    private LoadingOverlayController loadingOverlayController;
    private ToastNotificationController toastNotificationController;

    // --- Utilitários ---
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.forLanguageTag("pt-PT"));

    @Autowired
    public ClienteController(IClienteService clienteService,
                             IEnderecoService enderecoService,
                             ILocalidadeService localidadeService,
                             Validator validator) {
        this.clienteService = clienteService;
        this.enderecoService = enderecoService;
        this.localidadeService = localidadeService;
        this.validator = validator;
    }

    public void initialize() {
        backBtn.setOnMouseClicked(e -> onBackClick());
        editarBtn.setOnAction(e -> onEditClick());
        cancelarBtn.setOnAction(e -> onCancelClick());
        salvarBtn.setOnAction(e -> onSaveClick());
        solicitacoesBtn.setOnAction(e -> onSolicitacoesClick());

        clienteTipo.getItems().setAll(TipoCliente.values());
        clienteEstado.getItems().setAll(EstadoCliente.values());

        bloquearComboBoxSeNaoEditavel(clienteTipo, () -> editMode);
        bloquearComboBoxSeNaoEditavel(clienteEstado, () -> editMode);
        bloquearDatePickerSeNaoEditavel(clienteDtNas, () -> editMode);

        clienteDtNas.setConverter(new StringConverter<>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            @Override
            public String toString(LocalDate date) {
                return date != null ? formatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return (string == null || string.isBlank()) ? null : LocalDate.parse(string, formatter);
            }
        });

        clienteDtNas.getStyleClass().add("custom-transparent");

        toggleEditMode(false);
        apllyPermissionBySetor();
        applyHoverEffects();
    }

    public void setClienteId(Integer id) {
        this.clienteId = id;
        loadData();
    }

    private void apllyPermissionBySetor() {
        int setorPrincipal = SessionStorage.getSetorPrincipal();
        Set<Integer> setoresAssociados = SessionStorage.getSetoresAssociados();

        boolean isSoFaturacao =
                setorPrincipal == Setor.FINANCEIRO.getId() &&
                        setoresAssociados.stream().noneMatch(id ->
                                id == Setor.ADMINISTRATIVO.getId() || id == Setor.COMERCIAL.getId()
                        );

        if (isSoFaturacao) {
            solicitacoesBtn.setDisable(true);
            editarBtn.setDisable(true);
            historicoBtn.setDisable(true);

        } else {
            solicitacoesBtn.setDisable(false);
            editarBtn.setDisable(false);
            historicoBtn.setDisable(false);
        }
        contratosBtn.setDisable(false);
        faturasBtn.setDisable(false);
    }


    @Override
    public void applyInitialStyle() {
        toggleEditMode(false);
        applyEditStyle(clienteEmail, false);
        applyEditStyle(clienteDemanda, false);
        applyEditStyle(clienteConsumo, false);
        applyEditStyle(clienteDtCad, false);
    }

    private void applyHoverEffects() {
        applyStackPaneHoverEffect(backBtn);

        List.of(editarBtn, cancelarBtn, salvarBtn, solicitacoesBtn, contratosBtn, faturasBtn, historicoBtn)
                .forEach(StyleUtils::applySubtleOverlayHoverEffect);
    }

    private void loadData() {
        ClienteDetailsExtendedDto dto = clienteService.getExtendedById(clienteId);
        loadDataWithObjects(dto);
    }

    private void loadDataWithObjects(ClienteDetailsExtendedDto c) {
        funcionario_id.setText(value(c.getId()));
        clienteNome.setText(value(c.getNome()));
        clienteNif.setText(value(c.getNif()));
        clienteTipo.setValue(c.getTipoCliente());

        clienteDtNas.setValue(c.getDataNascimento() != null ? c.getDataNascimento().toLocalDate() : null);
        clienteEmail.setText(value(c.getEmail()));
        clienteTelefone.setText(value(c.getTelefone()));
        clienteMorada.setText(fullAddress(c));
        clienteDemanda.setText(decimal(c.getDemandaContratada(), "kVA"));
        clienteConsumo.setText(decimal(c.getConsumoMedio(), "kWh"));
        clienteDtCad.setText(date(c.getDataCadastro()));

        clienteEstado.getItems().setAll(EstadoCliente.values());
        clienteEstado.setValue(c.getEstado());
    }

    // --- Botões ---
    private void onEditClick() {
        toggleEditMode(true);
    }

    private void onCancelClick() {
        toggleEditMode(false);
        loadData();
    }

    private void onSaveClick() {
        loadingOverlayController.show();

        ClienteDetailsExtendedDto dto = buildUpdatedClienteDto();
        BeanWrapper wrapper = new BeanWrapperImpl(dto);
        Set<ConstraintViolation<ClienteDetailsExtendedDto>> violacoes = validator.validate(dto, com.example.cper_core.dtos.OnUpdate.class);

        clearInvalid(clienteNome, editMode);
        clearInvalid(clienteNif, editMode);
        clearInvalid(clienteEmail, editMode);
        clearInvalid(clienteTelefone, editMode);

        boolean temErro = false;
        StringBuilder erros = new StringBuilder("Erro ao salvar alterações:\n");

        for (ConstraintViolation<?> v : violacoes) {
            Object valor = wrapper.getPropertyValue(v.getPropertyPath().toString());
            if (valor == null || (valor instanceof String s && s.isBlank())) continue;

            erros.append(" • ").append(v.getPropertyPath()).append(": ").append(v.getMessage()).append("\n");
            temErro = true;

            switch (v.getPropertyPath().toString()) {
                case "nome" -> markInvalid(clienteNome);
                case "nif" -> markInvalid(clienteNif);
                case "email" -> markInvalid(clienteEmail);
                case "telefone" -> markInvalid(clienteTelefone);
            }
        }

        if (temErro) {
            toastNotificationController.showError("Erro de Validação", erros.toString());
            loadingOverlayController.hide();
            return;
        }

        try {
            clienteService.update(clienteId, dto);
            ClienteDetailsExtendedDto atualizado = clienteService.getExtendedById(clienteId);
            loadDataWithObjects(atualizado);
            toggleEditMode(false);
            toastNotificationController.showSuccess("Guardado", "As alterações foram guardadas com sucesso.");
        } catch (Exception e) {
            String msg = e.getMessage() != null ? e.getMessage() : "Erro inesperado ao guardar.";
            toastNotificationController.showError("Erro ao guardar", msg);
        } finally {
            loadingOverlayController.hide();
        }
    }

    private void onBackClick() {
        BaseLayoutController base = getBaseController();
        if (base != null) base.goBack();
    }

    // --- Modo de Edição ---
    private void toggleEditMode(boolean edit) {
        this.editMode = edit;

        clienteNome.setEditable(edit); applyEditStyle(clienteNome, edit);
        clienteNif.setEditable(edit); applyEditStyle(clienteNif, edit);
        applyEditStyle(clienteTipo, edit);
        applyEditStyle(clienteDtNas, edit);
        clienteTelefone.setEditable(edit); applyEditStyle(clienteTelefone, edit);
        clienteMorada.setEditable(edit); applyEditStyle(clienteMorada, edit);
        applyEditStyle(clienteEstado, edit);

        salvarBtn.setVisible(edit);
        cancelarBtn.setVisible(edit);
        editarBtn.setDisable(edit);
    }

    // --- Utilitários ---
    private ClienteDetailsExtendedDto buildUpdatedClienteDto() {
        ClienteDetailsExtendedDto dto = new ClienteDetailsExtendedDto();
        dto.setId(clienteId);
        dto.setNome(clienteNome.getText());
        dto.setNif(clienteNif.getText());
        dto.setTipoCliente(clienteTipo.getValue());
        dto.setEmail(clienteEmail.getText());
        dto.setTelefone(clienteTelefone.getText());
        dto.setDemandaContratada(parseBigDecimal(clienteDemanda.getText()));
        dto.setConsumoMedio(parseBigDecimal(clienteConsumo.getText()));
        dto.setEstado(clienteEstado.getValue());

        try {
            if (clienteDtNas.getValue() != null) {
                dto.setDataNascimento(clienteDtNas.getValue().atStartOfDay().atOffset(OffsetDateTime.now().getOffset()));
            }
        } catch (Exception ignored) {}

        return dto;
    }

    private BigDecimal parseBigDecimal(String valor) {
        try {
            return new BigDecimal(valor.replace(",", ".").replaceAll("[^0-9.]", ""));
        } catch (Exception e) {
            return null;
        }
    }

    private String value(Object obj) {
        return (obj == null || (obj instanceof String s && s.isBlank())) ? "N/A" : obj.toString();
    }

    private String date(OffsetDateTime dt) {
        return dt == null ? "N/A" : dt.toLocalDate().format(dateFormatter);
    }

    private String decimal(BigDecimal valor, String sufixo) {
        if (valor == null) return "N/A";
        numberFormatter.setMinimumFractionDigits(2);
        numberFormatter.setMaximumFractionDigits(2);
        return numberFormatter.format(valor) + (sufixo != null ? " " + sufixo : "");
    }

    private String fullAddress(ClienteDetailsExtendedDto c) {
        if (c.getEndereco() == null || c.getEndereco().getId() == null || c.getNPorta() == null) return "N/A";
        try {
            EnderecoDetailsExtendedDto e = enderecoService.getExtendedById(c.getEndereco().getId());
            if (e == null || e.getLocalidade() == null || e.getLocalidade().getId() == null) return "N/A";
            var l = localidadeService.getExtendedById(e.getLocalidade().getId());
            if (l == null) return "N/A";
            return e.getRua() + ", nº" + c.getNPorta() + ", " + l.getCodigoPostal() + " " + l.getNome();
        } catch (Exception ex) {
            return "N/A";
        }
    }

    @FXML
    private void onSolicitacoesClick() {
        BaseLayoutController baseLayout = (BaseLayoutController)
                basePane.getScene().getRoot().getProperties().get("baseController");

        if (baseLayout != null) {
            SolicitacaoEnergeticaFiltroDto filtro = new SolicitacaoEnergeticaFiltroDto();
            filtro.setIdsCliente(Set.of(clienteId));

            baseLayout.navigateTo("/views/SolicitacoesEnergeticas.fxml", controller -> {
                if (controller instanceof SolicitacoesEnergeticasController c) {
                    c.setFiltroInicial(filtro);
                }
            }, true);
        } else {
            System.err.println("BaseLayoutController não encontrado na scene.");
        }
    }

    private BaseLayoutController getBaseController() {
        var scene = basePane.getScene();
        if (scene == null) return null;
        return (BaseLayoutController) scene.getRoot().getProperties().get("baseController");
    }

    @Override
    public void setLoadingOverlayController(LoadingOverlayController controller) {
        this.loadingOverlayController = controller;
    }

    @Override
    public void setToastNotificationController(ToastNotificationController controller) {
        this.toastNotificationController = controller;
    }
}
