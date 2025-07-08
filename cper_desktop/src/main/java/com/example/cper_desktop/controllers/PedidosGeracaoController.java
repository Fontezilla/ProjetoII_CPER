package com.example.cper_desktop.controllers;

import com.example.cper_core.dtos.pedido_geracao.PedidoGeracaoDetailsDto;
import com.example.cper_core.dtos.pedido_geracao.PedidoGeracaoFiltroDto;
import com.example.cper_core.enums.EstadoPedidoGeracao;
import com.example.cper_core.enums.Prioridade;
import com.example.cper_core.enums.TipoEnergiaRenovavel;
import com.example.cper_core.services.interfaces.IPedidoGeracaoService;
import com.example.cper_desktop.controllers.base.AbstractListController;
import com.example.cper_desktop.controllers.reusable_components.ListItemController;
import com.example.cper_desktop.utils.FormatterUtils;
import com.example.cper_desktop.utils.InternalNavigationUtils;
import com.example.cper_desktop.utils.StyleUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.ZoneOffset;
import java.util.List;

import static com.example.cper_desktop.utils.StyleUtils.*;

@Component
public class PedidosGeracaoController extends AbstractListController<PedidoGeracaoDetailsDto, PedidoGeracaoFiltroDto> {

    @FXML private Button searchBtn;
    @FXML private Button dataAscBtn, dataDescBtn,
            prioridadeAscBtn, prioridadeDescBtn,
            estadoAscBtn, estadoDescBtn,
            tipoEnergiaAscBtn, tipoEnergiaDescBtn;
    @FXML private DatePicker dataPrevisaoDe, dataPrevisaoAte;
    @FXML private ComboBox<String> tipoCombo, estadoCombo, prioridadeCombo;
    @FXML private StackPane novoPedidoBtn;

    @Autowired private IPedidoGeracaoService pedidoGeracaoService;

    @FXML
    @Override
    public void initialize() {
        this.sortField = "dataCriacao";
        this.sortDirection = SortDirection.DESC;
        super.initialize();

        searchBtn.setOnAction(e -> applyFilters());

        dataAscBtn.setOnAction(e -> sortBy("dataCriacao", SortDirection.ASC));
        dataDescBtn.setOnAction(e -> sortBy("dataCriacao", SortDirection.DESC));
        prioridadeAscBtn.setOnAction(e -> sortBy("prioridade", SortDirection.ASC));
        prioridadeDescBtn.setOnAction(e -> sortBy("prioridade", SortDirection.DESC));
        estadoAscBtn.setOnAction(e -> sortBy("estado", SortDirection.ASC));
        estadoDescBtn.setOnAction(e -> sortBy("estado", SortDirection.DESC));
        tipoEnergiaAscBtn.setOnAction(e -> sortBy("tipoEnergia", SortDirection.ASC));
        tipoEnergiaDescBtn.setOnAction(e -> sortBy("tipoEnergia", SortDirection.DESC));

        for (TipoEnergiaRenovavel tipo : TipoEnergiaRenovavel.values()) tipoCombo.getItems().add(tipo.getLabel());
        for (EstadoPedidoGeracao estado : EstadoPedidoGeracao.values()) estadoCombo.getItems().add(estado.getLabel());
        for (Prioridade prioridade : Prioridade.values()) prioridadeCombo.getItems().add(prioridade.getLabel());

        novoPedidoBtn.setOnMouseClicked(e -> onNewPedidoClick());

        applyStyles();
    }

    @Override
    protected PedidoGeracaoFiltroDto createEmptyFilter() {
        PedidoGeracaoFiltroDto filtro = new PedidoGeracaoFiltroDto();
        filtro.setIsDeleted(false);
        return filtro;
    }

    private void applyFilters() {
        filtroAtual = filtroAtual != null ? filtroAtual : createEmptyFilter();

        filtroAtual.setDataPrevisaoInicio(null);
        filtroAtual.setDataPrevisaoFim(null);
        filtroAtual.setTipoEnergia(null);
        filtroAtual.setPrioridade(null);
        filtroAtual.setEstado(null);

        if (dataPrevisaoDe.getValue() != null)
            filtroAtual.setDataPrevisaoInicio(dataPrevisaoDe.getValue().atStartOfDay().atOffset(ZoneOffset.UTC).toLocalDate());
        if (dataPrevisaoAte.getValue() != null)
            filtroAtual.setDataPrevisaoFim(dataPrevisaoAte.getValue().plusDays(1).atStartOfDay().atOffset(ZoneOffset.UTC).toLocalDate());

        if (tipoCombo.getValue() != null)
            filtroAtual.setTipoEnergia(TipoEnergiaRenovavel.fromLabel(tipoCombo.getValue()));

        if (estadoCombo.getValue() != null)
            filtroAtual.setEstado(EstadoPedidoGeracao.fromLabel(estadoCombo.getValue()));

        if (prioridadeCombo.getValue() != null)
            filtroAtual.setPrioridade(Prioridade.fromLabel(prioridadeCombo.getValue()));

        currentPage = 0;
        toggleFilters();
        carregarLista(filtroAtual);
    }

    private void applyStyles() {
        applyEditStyle(dataPrevisaoDe, true);
        applyEditStyle(dataPrevisaoAte, true);
        bloquearDatePickerSeNaoEditavel(dataPrevisaoDe, () -> true);
        bloquearDatePickerSeNaoEditavel(dataPrevisaoAte, () -> true);
        applyEditStyle(tipoCombo, true);
        applyEditStyle(estadoCombo, true);
        applyEditStyle(prioridadeCombo, true);
        bloquearComboBoxSeNaoEditavel(tipoCombo, () -> true);
        bloquearComboBoxSeNaoEditavel(estadoCombo, () -> true);
        bloquearComboBoxSeNaoEditavel(prioridadeCombo, () -> true);
    }

    @Override
    protected void carregarLista(PedidoGeracaoFiltroDto filtro) {
        try {
            PageRequest pageRequest = buildPageRequest();
            Page<PedidoGeracaoDetailsDto> page = pedidoGeracaoService.listFiltered(pageRequest, filtro);

            totalPages = page.getTotalPages();
            paginationBarController.updatePagination();

            renderList(page.getContent());
        } catch (Exception e) {
            System.err.println("Erro ao carregar pedidos de geração:");
            e.printStackTrace();
        }
    }

    @Override
    protected Node createListItem(PedidoGeracaoDetailsDto dto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/components/ListItem.fxml"));
            Node itemNode = loader.load();
            ListItemController controller = loader.getController();

            controller.setDataComLarguras(
                    List.of(
                            FormatterUtils.date(dto.getDataCriacao()),
                            FormatterUtils.decimal(dto.getQtdEnergia(), "kWh"),
                            FormatterUtils.decimal(dto.getQtdEnergiaH(), "kWh"),
                            FormatterUtils.label(dto.getTipoEnergia()),
                            FormatterUtils.label(dto.getPrioridade()),
                            FormatterUtils.label(dto.getEstado())
                    ),
                    List.of(150.0, 150.0, 150.0, 150.0, 150.0, 150.0),
                    clicked -> onPedidoClick(dto.getId())
            );

            return itemNode;
        } catch (IOException e) {
            System.err.println("Erro ao carregar item do pedido: " + dto.getId());
            e.printStackTrace();
            return null;
        }
    }

    private void onPedidoClick(Integer id) {
        InternalNavigationUtils.goTo("/views/PedidoGeracao.fxml", controller -> {
            if (controller instanceof PedidoGeracaoController pedidoController) {
                pedidoController.setPedidoId(id);
            }
        });
    }

    private void onNewPedidoClick() {
        InternalNavigationUtils.goTo("/views/PedidoGeracao.fxml", controller -> {
            if (controller instanceof PedidoGeracaoController pedidoController) {
                pedidoController.setPedidoId(null);
            }
        });
    }

    @Override
    protected void applyHoverEffects() {
        applyStackPaneHoverEffect(filtersBtn);
        applyStackPaneHoverEffect(sortBtn);
        applyStackPaneHoverEffect(backBtn);
        applyStackPaneHoverEffect(novoPedidoBtn);

        applySubtleOverlayHoverEffect(searchBtn);

        List.of(dataAscBtn, dataDescBtn, prioridadeAscBtn, prioridadeDescBtn,
                        estadoAscBtn, estadoDescBtn, tipoEnergiaAscBtn,
                        tipoEnergiaDescBtn)
                .forEach(StyleUtils::applyButtonHoverEffect);
    }
}