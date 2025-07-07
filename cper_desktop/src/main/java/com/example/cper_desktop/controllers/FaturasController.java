package com.example.cper_desktop.controllers;

import com.example.cper_core.dtos.fatura.FaturaDetailsDto;
import com.example.cper_core.dtos.fatura.FaturaFiltroDto;
import com.example.cper_core.enums.EstadoFatura;
import com.example.cper_core.services.FaturaService;
import com.example.cper_desktop.controllers.base.AbstractListController;
import com.example.cper_desktop.controllers.reusable_components.ListItemController;
import com.example.cper_desktop.utils.StyleUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.util.List;

import static com.example.cper_desktop.utils.StyleUtils.*;

@Component
public class FaturasController extends AbstractListController<FaturaDetailsDto, FaturaFiltroDto> {

    @FXML private Button searchBtn;
    @FXML private Button dataAscBtn, dataDescBtn, estadoAscBtn, estadoDescBtn;
    @FXML private DatePicker dataEmissaoDe, dataEmissaoAte;
    @FXML private ComboBox<String> multaCombo;
    @FXML private ComboBox<EstadoFatura> estadoCombo;

    @Autowired private FaturaService faturaService;

    @FXML
    @Override
    public void initialize() {
        this.sortField = "dataEmissao";
        this.sortDirection = SortDirection.DESC;
        super.initialize();

        searchBtn.setOnAction(e -> applyFilters());

        dataAscBtn.setOnAction(e -> sortBy("dataEmissao", SortDirection.ASC));
        dataDescBtn.setOnAction(e -> sortBy("dataEmissao", SortDirection.DESC));
        estadoAscBtn.setOnAction(e -> sortBy("estado", SortDirection.ASC));
        estadoDescBtn.setOnAction(e -> sortBy("estado", SortDirection.DESC));

        estadoCombo.getItems().addAll(EstadoFatura.values());
        multaCombo.getItems().addAll("Com multa", "Sem multa");

        applyStyles();
    }

    @Override
    protected FaturaFiltroDto createEmptyFilter() {
        FaturaFiltroDto filtro = new FaturaFiltroDto();
        filtro.setIsDeleted(false);
        return filtro;
    }

    private void applyFilters() {
        filtroAtual = filtroAtual != null ? filtroAtual : createEmptyFilter();

        filtroAtual.setDataEmissaoInicio(null);
        filtroAtual.setDataEmissaoFim(null);
        filtroAtual.setEstado(null);
        filtroAtual.setVMultaMin(null);
        filtroAtual.setVMultaMax(null);

        if (dataEmissaoDe.getValue() != null)
            filtroAtual.setDataEmissaoInicio(dataEmissaoDe.getValue().atStartOfDay().atOffset(ZoneOffset.UTC));

        if (dataEmissaoAte.getValue() != null)
            filtroAtual.setDataEmissaoFim(dataEmissaoAte.getValue().plusDays(1).atStartOfDay().atOffset(ZoneOffset.UTC));

        if (estadoCombo.getValue() != null)
            filtroAtual.setEstado(estadoCombo.getValue().getId());

        if (multaCombo.getValue() != null) {
            switch (multaCombo.getValue()) {
                case "Com multa" -> filtroAtual.setVMultaMin(BigDecimal.valueOf(0.01));
                case "Sem multa" -> {
                    filtroAtual.setVMultaMin(BigDecimal.ZERO);
                    filtroAtual.setVMultaMax(BigDecimal.ZERO);
                }
            }
        }

        currentPage = 0;
        toggleFilters();
        clearFilters();
        carregarLista(filtroAtual);
    }

    private void clearFilters() {
        dataEmissaoDe.setValue(null);
        dataEmissaoAte.setValue(null);
        estadoCombo.setValue(null);
        multaCombo.setValue(null);
    }

    private void applyStyles() {
        applyEditStyle(dataEmissaoDe, true);
        applyEditStyle(dataEmissaoAte, true);

        bloquearDatePickerSeNaoEditavel(dataEmissaoDe, () -> true);
        bloquearDatePickerSeNaoEditavel(dataEmissaoAte, () -> true);

        dataEmissaoDe.getStyleClass().add("custom-transparent");
        dataEmissaoAte.getStyleClass().add("custom-transparent");

        applyEditStyle(estadoCombo, true);
        bloquearComboBoxSeNaoEditavel(estadoCombo, () -> true);

        applyEditStyle(multaCombo, true);
        bloquearComboBoxSeNaoEditavel(multaCombo, () -> true);
    }

    @Override
    protected void carregarLista(FaturaFiltroDto filtro) {
        try {
            PageRequest pageRequest = buildPageRequest();
            Page<FaturaDetailsDto> page = faturaService.listFiltered(pageRequest, filtro);

            totalPages = page.getTotalPages();
            paginationBarController.updatePagination();

            renderList(page.getContent());
        } catch (Exception e) {
            System.err.println("Erro ao carregar faturas:");
            e.printStackTrace();
        }
    }

    @Override
    protected Node createListItem(FaturaDetailsDto dto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/components/ListItem.fxml"));
            Node itemNode = loader.load();
            ListItemController controller = loader.getController();

            controller.setDataComLarguras(
                    List.of(
                            dto.getDataEmissao() != null ? dto.getDataEmissao().toLocalDate().toString() : "N/A",
                            dto.getDataVencimento() != null ? dto.getDataVencimento().toLocalDate().toString() : "N/A",
                            dto.getValorTotal() != null ? dto.getValorTotal().toString() + " €" : "N/A",
                            estadoToLabel(dto.getEstado())
                    ),
                    List.of(235.0, 235.0, 235.0, 235.0),
                    clicked -> openFaturaPage(dto.getId())
            );

            return itemNode;
        } catch (IOException e) {
            System.err.println("Erro ao carregar item da fatura: " + dto.getId());
            e.printStackTrace();
            return null;
        }
    }

    private String estadoToLabel(Integer estadoId) {
        try {
            return EstadoFatura.fromId(estadoId).getLabel();
        } catch (Exception e) {
            return "Desconhecido";
        }
    }

    private void openFaturaPage(Integer id) {
//        var scene = basePane.getScene();
//        if (scene == null) return;
//
//        var base = scene.getRoot().getProperties().get("baseController");
//        if (base instanceof BaseLayoutController baseLayout) {
//            baseLayout.navigateTo("/views/fatura.fxml", controller -> {
//                if (controller instanceof FaturaController faturaController) {
//                    faturaController.setFaturaId(id);
//                }
//            }, true);
//        } else {
//            System.err.println("BaseLayoutController não encontrado na scene.");
//        }
    }

    @Override
    protected void applyHoverEffects() {
        applyStackPaneHoverEffect(filtersBtn);
        applyStackPaneHoverEffect(sortBtn);
        applyStackPaneHoverEffect(backBtn);

        applySubtleOverlayHoverEffect(searchBtn);

        List.of(dataAscBtn, dataDescBtn, estadoAscBtn, estadoDescBtn)
                .forEach(StyleUtils::applyButtonHoverEffect);
    }
}
