package com.example.cper_desktop.controllers;

import com.example.cper_core.dtos.centro_producao.CentroProducaoDetailsExtendedDto;
import com.example.cper_core.dtos.cliente.ClienteDetailsDto;
import com.example.cper_core.dtos.contrato.ContratoDetailsExtendedDto;
import com.example.cper_core.dtos.pedido_geracao.PedidoGeracaoDetailsExtendedDto;
import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaDetailsExtendedDto;
import com.example.cper_core.enums.EstadoPedidoGeracao;
import com.example.cper_core.enums.Prioridade;
import com.example.cper_core.enums.TipoEnergiaRenovavel;
import com.example.cper_core.services.ClienteService;
import com.example.cper_core.services.ContratoService;
import com.example.cper_core.services.interfaces.*;
import com.example.cper_desktop.controllers.base.AbstractDetailsController;
import com.example.cper_desktop.utils.*;
import jakarta.validation.Validator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

import static com.example.cper_desktop.utils.FormatterUtils.*;

@Component
public class PedidoGeracaoController extends AbstractDetailsController<PedidoGeracaoDetailsExtendedDto> {

    @FXML private StackPane basePane, backBtn;
    @FXML private AnchorPane obsPopup, rFinalPopup;

    @FXML private Button editarBtn, cancelarBtn, salvarBtn, clienteBtn, contratoBtn, centroBtn;
    @FXML private Button observacoesBtn, gerarBtn;
    @FXML private Button closeOPopupBtn, cancelarOPopupBtn, salvarOPopupBtn;
    @FXML private Button closeRPopupBtn, cancelarRPopupBtn, salvarRPopupBtn;

    @FXML private Label pedidoId;

    @FXML private TextField clienteCodigo, contratoCodigo, centroCodigo;
    @FXML private TextField dataCriacao, dataPrevista;
    @FXML private TextField qtdEnergia, qtdEnergiaH, qtdGerada, qtdGeradaH;
    @FXML private ComboBox<TipoEnergiaRenovavel> tipoEnergia;
    @FXML private ComboBox<Prioridade> prioridade;
    @FXML private ComboBox<EstadoPedidoGeracao> estado;

    @FXML private TextArea observacoes, relatorioFinal;

    private final IPedidoGeracaoService pedidoGeracaoService;
    private final IContratoService contratoService;
    private final ISolicitacaoEnergeticaService solicitacaoEnergeticaService;
    private final IClienteService clienteService;
    private final ICentroProducaoService centroProducaoService;

    private PedidoGeracaoDetailsExtendedDto pedido;

    public PedidoGeracaoController(IPedidoGeracaoService service, Validator validator, ClienteService clienteService,
                                   ContratoService contratoService,
                                   ISolicitacaoEnergeticaService solicitacaoEnergeticaService,
                                   ICentroProducaoService centroProducaoService) {
        super(validator);
        this.pedidoGeracaoService = service;
        this.clienteService = clienteService;
        this.contratoService = contratoService;
        this.solicitacaoEnergeticaService = solicitacaoEnergeticaService;
        this.centroProducaoService = centroProducaoService;
    }

    @Override
    protected void loadData() {
        pedido = pedidoGeracaoService.getExtendedById(id);
        ContratoDetailsExtendedDto contrato = contratoService.getExtendedById(pedido.getContrato().getId());
        SolicitacaoEnergeticaDetailsExtendedDto solicitacao = solicitacaoEnergeticaService.getExtendedById(contrato.getSolicitacaoEnergetica().getId());
        ClienteDetailsDto cliente = clienteService.getById(solicitacao.getCliente().getId());
        CentroProducaoDetailsExtendedDto centro = centroProducaoService.getExtendedById(pedido.getCentroProducao().getId());

        pedidoId.setText(value(pedido.getId()));
        clienteCodigo.setText(value(pedido.getContrato() != null ? cliente.getCodigo() : null));
        contratoCodigo.setText(value(pedido.getContrato() != null ? contrato.getCodigo() : null));
        centroCodigo.setText(value(pedido.getCentroProducao() != null ? centro.getCodigo() : null));

        dataCriacao.setText(date(pedido.getDataCriacao()));
        dataPrevista.setText(date(pedido.getDataPrevisao()));
        qtdEnergia.setText(decimal(pedido.getQtdEnergia(), "kWh"));
        qtdEnergiaH.setText(decimal(pedido.getQtdEnergiaH(), "kWh"));
        qtdGerada.setText(decimal(pedido.getQtdEnergiaProduzida(), "kWh"));
        qtdGeradaH.setText(decimal(pedido.getQtdEnergiaProduzidaH(), "kWh"));

        tipoEnergia.setValue(pedido.getTipoEnergia());
        prioridade.setValue(pedido.getPrioridade());
        estado.setValue(pedido.getEstado());

        observacoes.setText(value(pedido.getObs()));
        relatorioFinal.setText(value(pedido.getRelatorioFinal()));

        toggleEditMode(false);
    }

    @Override
    protected PedidoGeracaoDetailsExtendedDto updateData() {
        PedidoGeracaoDetailsExtendedDto dto = new PedidoGeracaoDetailsExtendedDto();

        dto.setId(pedido.getId());
        dto.setQtdEnergiaProduzida(parseDecimal(qtdGerada.getText()));
        dto.setQtdEnergiaProduzidaH(parseDecimal(qtdGeradaH.getText()));
        dto.setObs(observacoes.getText());
        dto.setRelatorioFinal(relatorioFinal.getText());
        dto.setTipoEnergia(tipoEnergia.getValue());
        dto.setPrioridade(prioridade.getValue());
        dto.setEstado(estado.getValue());

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

        observacoesBtn.setOnAction(e -> togglePopup(obsPopup, true));
        closeOPopupBtn.setOnAction(e -> togglePopup(obsPopup, false));
        cancelarOPopupBtn.setOnAction(e -> togglePopup(obsPopup, false));
        salvarOPopupBtn.setOnAction(e -> togglePopup(obsPopup, false));

        gerarBtn.setOnAction(e -> togglePopup(rFinalPopup, true));
        closeRPopupBtn.setOnAction(e -> togglePopup(rFinalPopup, false));
        cancelarRPopupBtn.setOnAction(e -> togglePopup(rFinalPopup, false));
        salvarRPopupBtn.setOnAction(e -> togglePopup(rFinalPopup, false));

        tipoEnergia.getItems().setAll(TipoEnergiaRenovavel.values());
        prioridade.getItems().setAll(Prioridade.values());
        estado.getItems().setAll(EstadoPedidoGeracao.values());

        StyleUtils.bloquearComboBoxSeNaoEditavel(tipoEnergia, () -> editMode);
        StyleUtils.bloquearComboBoxSeNaoEditavel(prioridade, () -> editMode);
        StyleUtils.bloquearComboBoxSeNaoEditavel(estado, () -> editMode);

        StyleUtils.applyTextAreaDefaultStyle(observacoes);
        StyleUtils.applyTextAreaDefaultStyle(relatorioFinal);

        Platform.runLater(() -> {
            StyleUtils.ensureStylesheetApplied(basePane, "/styles/entities-details.css");
            StyleUtils.ensureStylesheetApplied(basePane, "/styles/Toast.css");
        });

        System.out.println(createMode);

        if (createMode) {
            toggleEditMode(true);
            clienteBtn.setDisable(true);
            contratoBtn.setDisable(true);
            centroBtn.setDisable(true);
            observacoesBtn.setDisable(true);
            gerarBtn.setDisable(true);
            editarBtn.setDisable(true);
        }
    }

    @Override
    protected void toggleEditMode(boolean edit) {
        super.toggleEditMode(edit);

        Map<Control, Boolean> permissoes;

        if (createMode) {
            permissoes = Map.of(
                    qtdGerada, false,
                    qtdGeradaH, false,
                    observacoes, true,
                    relatorioFinal, true,
                    tipoEnergia, true,
                    prioridade, true,
                    estado, true
            );
        } else {
            permissoes = Map.of(
                    qtdGerada, true,
                    qtdGeradaH, true,
                    observacoes, true,
                    relatorioFinal, true,
                    tipoEnergia, true,
                    prioridade, true,
                    estado, true
            );
        }

        applyPermissions(permissoes, edit);
        salvarBtn.setVisible(edit);
        cancelarBtn.setVisible(edit);
        editarBtn.setDisable(edit);
    }

    private void onSaveClick() {
        loadingOverlayController.show();

        PedidoGeracaoDetailsExtendedDto dto = updateData();
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
                pedido = pedidoGeracaoService.create(dto);
                showSuccessToast("Criado", "O pedido foi criado com sucesso.");
            } else {
                pedidoGeracaoService.update(pedido.getId(), dto);
                pedido = pedidoGeracaoService.getExtendedById(pedido.getId());
                showSuccessToast("Guardado", "As alterações foram guardadas com sucesso.");
            }

            loadData();
            toggleEditMode(false);
        } catch (Exception e) {
            showErrorToast("Erro ao guardar", Optional.ofNullable(e.getMessage()).orElse("Erro inesperado."));
        } finally {
            loadingOverlayController.hide();
        }
    }

    private void togglePopup(AnchorPane popup, boolean show) {
        popup.setManaged(show);
        popup.setVisible(show);
    }

    public void setPedidoId(Integer id) {
        this.id = id;
        this.createMode = (id == null);
        this.editMode = createMode;

        if (!createMode) {
            loadData();
        } else {
            pedido = new PedidoGeracaoDetailsExtendedDto();
        }

        // ✅ Chamar só agora
        postInitialize();
    }


}