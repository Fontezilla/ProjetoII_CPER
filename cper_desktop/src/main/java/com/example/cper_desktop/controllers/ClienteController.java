package com.example.cper_desktop.controllers;

import com.example.cper_core.dtos.cliente.ClienteDetailsExtendedDto;
import com.example.cper_core.dtos.contrato.ContratoFiltroDto;
import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.dtos.fatura.FaturaFiltroDto;
import com.example.cper_core.dtos.historico_consumo.HistoricoConsumoFiltroDto;
import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaFiltroDto;
import com.example.cper_core.enums.EstadoCliente;
import com.example.cper_core.enums.Setor;
import com.example.cper_core.enums.TipoCliente;
import com.example.cper_core.services.interfaces.IClienteService;
import com.example.cper_core.services.interfaces.IEnderecoService;
import com.example.cper_core.services.interfaces.ILocalidadeService;
import com.example.cper_core.services.interfaces.IMoradaService;
import com.example.cper_desktop.controllers.base.AbstractDetailsController;
import com.example.cper_desktop.utils.FormatterUtils;
import com.example.cper_desktop.utils.InternalNavigationUtils;
import com.example.cper_desktop.utils.SessionStorage;
import com.example.cper_desktop.utils.StyleUtils;
import com.example.cper_desktop.utils.ValidationUtils;
import jakarta.validation.Validator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.*;

import static com.example.cper_desktop.utils.FormatterUtils.*;

@Component
public class ClienteController extends AbstractDetailsController<ClienteDetailsExtendedDto> {

    @FXML private StackPane basePane, backBtn;
    @FXML private Label clienteId;
    @FXML private TextField clienteNome, clienteNif, clienteEmail, clienteTelefone, clienteMorada, clienteDemanda,
            clienteConsumo, clienteDtCad;
    @FXML private DatePicker clienteDtNas;
    @FXML private ComboBox<TipoCliente> clienteTipo;
    @FXML private ComboBox<EstadoCliente> clienteEstado;
    @FXML private Button editarBtn, cancelarBtn, salvarBtn, solicitacoesBtn, contratosBtn, faturasBtn, historicoBtn;

    private final IClienteService clienteService;
    private final IEnderecoService enderecoService;
    private final ILocalidadeService localidadeService;
    private final IMoradaService moradaService;


    private ClienteDetailsExtendedDto cliente;

    public ClienteController(IClienteService clienteService,
                             IEnderecoService enderecoService,
                             ILocalidadeService localidadeService,
                             Validator validator, IMoradaService moradaService) {
        super(validator);
        this.clienteService = clienteService;
        this.enderecoService = enderecoService;
        this.localidadeService = localidadeService;
        this.moradaService = moradaService;
    }

    @Override
    protected void loadData() {
        cliente = clienteService.getExtendedById(id);

        clienteId.setText(value(cliente.getCodigo()));
        clienteNome.setText(value(cliente.getNome()));
        clienteNif.setText(value(cliente.getNif()));
        clienteTipo.setValue(cliente.getTipoCliente());
        clienteDtNas.setValue(cliente.getDataNascimento() != null ? cliente.getDataNascimento().toLocalDate() : null);
        clienteEmail.setText(value(cliente.getEmail()));
        clienteTelefone.setText(value(cliente.getTelefone()));
        clienteMorada.setText(formatSimpleAddress(
                cliente.getEndereco() != null ? cliente.getEndereco() : null,
                cliente.getNPorta(), enderecoService, localidadeService));
        clienteDemanda.setText(decimal(cliente.getDemandaContratada(), "kVA"));
        clienteConsumo.setText(decimal(cliente.getConsumoMedio(), "kWh"));
        clienteDtCad.setText(date(cliente.getDataCadastro()));
        clienteEstado.setValue(cliente.getEstado());

        applyPermissionBySetor();
        toggleEditMode(false);
    }

    @Override
    protected ClienteDetailsExtendedDto updateData() {
        ClienteDetailsExtendedDto dto = FormatterUtils.getDetails(
                new ClienteDetailsExtendedDto(),
                clienteEmail.getText(),
                clienteTelefone.getText(),
                cliente.getNPorta(),
                clienteMorada.getText(),
                FormatterUtils.validFormatAddress(clienteMorada.getText()),
                moradaService,
                extended -> new EnderecoDto(extended.getId()),
                ClienteDetailsExtendedDto::setEndereco,
                ClienteDetailsExtendedDto::setNPorta
        );

        dto.setId(cliente.getId());
        dto.setNome(clienteNome.getText());
        dto.setNif(clienteNif.getText());
        dto.setTipoCliente(clienteTipo.getValue());
        dto.setDemandaContratada(parseDecimal(clienteDemanda.getText()));
        dto.setConsumoMedio(parseDecimal(clienteConsumo.getText()));
        dto.setEstado(clienteEstado.getValue());

        if (clienteDtNas.getValue() != null)
            dto.setDataNascimento(clienteDtNas.getValue().atStartOfDay().atOffset(OffsetDateTime.now().getOffset()));

        return dto;
    }

    @Override
    protected void postInitialize() {
        backBtn.setOnMouseClicked(e -> InternalNavigationUtils.goBack());

        editarBtn.setOnAction(e -> toggleEditMode(true));
        cancelarBtn.setOnAction(e -> {
            toggleEditMode(false);
            if (!createMode) loadData();
        });
        salvarBtn.setOnAction(e -> onSaveClick());

        solicitacoesBtn.setOnAction(e -> {
            var filtro = new SolicitacaoEnergeticaFiltroDto();
            filtro.setIdsCliente(Set.of(cliente.getId()));
            InternalNavigationUtils.navigateWith("/views/SolicitacoesEnergeticas.fxml", filtro, SolicitacoesEnergeticasController::setFiltroInicial);
        });

        historicoBtn.setOnAction(e -> {
            var filtro = new HistoricoConsumoFiltroDto();
            filtro.setIdsCliente(Set.of(cliente.getId()));
            InternalNavigationUtils.navigateWith("/views/HistoricosConsumo.fxml", filtro, HistoricoConsumoController::setFiltroInicial);
        });

        contratosBtn.setOnAction(e -> {
            var filtro = new ContratoFiltroDto();
            filtro.setIdsFuncionario(Set.of(cliente.getId()));
            InternalNavigationUtils.navigateWith("/views/Contratos.fxml", filtro, ContratosController::setFiltroInicial);
        });

        faturasBtn.setOnAction(e -> {
            var filtro = new FaturaFiltroDto();
            filtro.setIdsContrato(Set.of(cliente.getId()));
            InternalNavigationUtils.navigateWith("/views/Faturas.fxml", filtro, FaturasController::setFiltroInicial);
        });

        clienteTipo.getItems().setAll(TipoCliente.values());
        clienteEstado.getItems().setAll(EstadoCliente.values());

        StyleUtils.bloquearComboBoxSeNaoEditavel(clienteTipo, () -> editMode);
        StyleUtils.bloquearComboBoxSeNaoEditavel(clienteEstado, () -> editMode);
        StyleUtils.bloquearDatePickerSeNaoEditavel(clienteDtNas, () -> editMode);

        clienteDtNas.setConverter(StyleUtils.getDatePickerConverter());

        StyleUtils.ensureStylesheetApplied(basePane, "/styles/style-fixed.css");
        StyleUtils.ensureStylesheetApplied(basePane, "/styles/Toast.css");
        StyleUtils.applyEditStylesOnSceneAvailable(basePane, clienteTipo, clienteEstado, clienteDtNas);

        applyHoverEffects();

        clienteId.setVisible(!createMode);
        clienteConsumo.setVisible(!createMode);
        clienteDtCad.setVisible(!createMode);

        if (createMode) {
            toggleEditMode(true);
        }
    }


    @Override
    protected void toggleEditMode(boolean edit) {
        super.toggleEditMode(edit);

        Map<Control, Boolean> permissoes = Map.of(
                clienteNome, true,
                clienteNif, true,
                clienteEmail, true,
                clienteTelefone, true,
                clienteMorada, true,
                clienteTipo, true,
                clienteDtNas, true,
                clienteEstado, true
        );

        applyPermissions(permissoes, edit);
        salvarBtn.setVisible(edit);
        cancelarBtn.setVisible(edit);
        editarBtn.setDisable(edit);
    }

    private void onSaveClick() {
        loadingOverlayController.show();
        ClienteDetailsExtendedDto dto = updateData();

        var grupoValidacao = createMode
                ? com.example.cper_core.dtos.OnCreate.class
                : com.example.cper_core.dtos.OnUpdate.class;

        var result = ValidationUtils.validar(dto, validator, grupoValidacao);

        if (!result.valido()) {
            showErrorToast("Erro de Validação", ValidationUtils.generateErrorMessage(result.violacoes()).toString());
            loadingOverlayController.hide();
            return;
        }

        try {
            if (createMode) {
                cliente = clienteService.create(dto);
                showSuccessToast("Criado", "O cliente foi criado com sucesso.");
            } else {
                clienteService.update(cliente.getId(), dto);
                cliente = clienteService.getExtendedById(cliente.getId());
                showSuccessToast("Guardado", "As alterações foram guardadas com sucesso.");
            }

            loadData();
            toggleEditMode(false);
        } catch (Exception e) {
            showErrorToast("Erro ao guardar", "");
            System.out.println(Optional.ofNullable(e.getMessage()).orElse("Erro inesperado ao guardar."));
        } finally {
            loadingOverlayController.hide();
        }
    }


    private void applyPermissionBySetor() {
        int setorPrincipal = SessionStorage.getSetorPrincipal();
        Set<Integer> setoresAssociados = SessionStorage.getSetoresAssociados();
        boolean isSoFaturacao = setorPrincipal == Setor.FINANCEIRO.getId() &&
                setoresAssociados.stream().noneMatch(id -> id == Setor.ADMINISTRATIVO.getId() || id == Setor.COMERCIAL.getId());
        solicitacoesBtn.setDisable(isSoFaturacao);
        editarBtn.setDisable(isSoFaturacao);
        historicoBtn.setDisable(isSoFaturacao);
        contratosBtn.setDisable(false);
        faturasBtn.setDisable(false);
    }

    private void applyHoverEffects() {
        StyleUtils.applyStackPaneHoverEffect(backBtn);
        List.of(editarBtn, cancelarBtn, salvarBtn, solicitacoesBtn, contratosBtn, faturasBtn, historicoBtn)
                .forEach(StyleUtils::applySubtleOverlayHoverEffect);
    }

    public void setClienteId(Integer id) {
        this.id = id;
        this.createMode = (id == null);
        this.editMode = createMode;

        if (!createMode) {
            loadData();
        } else {
            cliente = new ClienteDetailsExtendedDto();
        }
    }
}