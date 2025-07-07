package com.example.cper_desktop.controllers;

import com.example.cper_core.dtos.contrato.ContratoDetailsDto;
import com.example.cper_core.dtos.contrato.ContratoFiltroDto;
import com.example.cper_core.enums.EstadoContrato;
import com.example.cper_core.enums.TipoContrato;
import com.example.cper_core.services.ContratoService;
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
import java.time.ZoneOffset;
import java.util.List;

import static com.example.cper_desktop.utils.StyleUtils.*;

@Component
public class ContratosController extends AbstractListController<ContratoDetailsDto, ContratoFiltroDto> {

    @FXML private Button searchBtn;
    @FXML private Button dataInicioAscBtn, dataInicioDescBtn, dataFimAscBtn, dataFimDescBtn, estadoAscBtn, estadoDescBtn;
    @FXML private DatePicker dataIniDe, dataIniAte, dataFimDe, dataFimAte;
    @FXML private ComboBox<String> tipoCombo, estadoCombo;

    @Autowired private ContratoService contratoService;

    @FXML
    @Override
    public void initialize() {
        this.sortField = "dataInicio";
        this.sortDirection = SortDirection.DESC;
        super.initialize();

        searchBtn.setOnAction(e -> applyFilters());

        dataInicioAscBtn.setOnAction(e -> sortBy("dataInicio", SortDirection.ASC));
        dataInicioDescBtn.setOnAction(e -> sortBy("dataInicio", SortDirection.DESC));
        dataFimAscBtn.setOnAction(e -> sortBy("dataFim", SortDirection.ASC));
        dataFimDescBtn.setOnAction(e -> sortBy("dataFim", SortDirection.DESC));
        estadoAscBtn.setOnAction(e -> sortBy("estado", SortDirection.ASC));
        estadoDescBtn.setOnAction(e -> sortBy("estado", SortDirection.DESC));

        for (TipoContrato tipo : TipoContrato.values()) tipoCombo.getItems().add(tipo.getLabel());
        for (EstadoContrato estado : EstadoContrato.values()) estadoCombo.getItems().add(estado.getLabel());

        applyStyles();
    }

    @Override
    protected ContratoFiltroDto createEmptyFilter() {
        ContratoFiltroDto filtro = new ContratoFiltroDto();
        filtro.setIsDeleted(false);
        return filtro;
    }

    private void applyFilters() {
        filtroAtual = filtroAtual != null ? filtroAtual : createEmptyFilter();

        filtroAtual.setDataInicioInicio(null);
        filtroAtual.setDataInicioFim(null);
        filtroAtual.setDataFimInicio(null);
        filtroAtual.setDataFimFim(null);
        filtroAtual.setTipoContrato(null);
        filtroAtual.setEstado(null);

        if (dataIniDe.getValue() != null)
            filtroAtual.setDataInicioInicio(dataIniDe.getValue().atStartOfDay().atOffset(ZoneOffset.UTC));
        if (dataIniAte.getValue() != null)
            filtroAtual.setDataInicioFim(dataIniAte.getValue().plusDays(1).atStartOfDay().atOffset(ZoneOffset.UTC));

        if (dataFimDe.getValue() != null)
            filtroAtual.setDataFimInicio(dataFimDe.getValue().atStartOfDay().atOffset(ZoneOffset.UTC));
        if (dataFimAte.getValue() != null)
            filtroAtual.setDataFimFim(dataFimAte.getValue().plusDays(1).atStartOfDay().atOffset(ZoneOffset.UTC));

        if (tipoCombo.getValue() != null) {
            try {
                filtroAtual.setTipoContrato(TipoContrato.fromLabel(tipoCombo.getValue()));
            } catch (IllegalArgumentException e) {
                System.err.println("Tipo inválido: " + tipoCombo.getValue());
            }
        }

        if (estadoCombo.getValue() != null) {
            try {
                filtroAtual.setEstado(EstadoContrato.fromLabel(estadoCombo.getValue()));
            } catch (IllegalArgumentException e) {
                System.err.println("Estado inválido: " + estadoCombo.getValue());
            }
        }

        currentPage = 0;
        toggleFilters();
        clearFilters();
        carregarLista(filtroAtual);
    }

    private void clearFilters() {
        dataIniDe.setValue(null);
        dataIniAte.setValue(null);
        dataFimDe.setValue(null);
        dataFimAte.setValue(null);
        tipoCombo.setValue(null);
        estadoCombo.setValue(null);
    }

    private void applyStyles() {
        applyEditStyle(dataIniDe, true);
        applyEditStyle(dataIniAte, true);
        applyEditStyle(dataFimDe, true);
        applyEditStyle(dataFimAte, true);

        bloquearDatePickerSeNaoEditavel(dataIniDe, () -> true);
        bloquearDatePickerSeNaoEditavel(dataIniAte, () -> true);
        bloquearDatePickerSeNaoEditavel(dataFimDe, () -> true);
        bloquearDatePickerSeNaoEditavel(dataFimAte, () -> true);

        dataIniDe.getStyleClass().add("custom-transparent");
        dataIniAte.getStyleClass().add("custom-transparent");
        dataFimDe.getStyleClass().add("custom-transparent");
        dataFimAte.getStyleClass().add("custom-transparent");

        applyEditStyle(tipoCombo, true);
        applyEditStyle(estadoCombo, true);

        bloquearComboBoxSeNaoEditavel(tipoCombo, () -> true);
        bloquearComboBoxSeNaoEditavel(estadoCombo, () -> true);
    }

    @Override
    protected void carregarLista(ContratoFiltroDto filtro) {
        try {
            PageRequest pageRequest = buildPageRequest();
            Page<ContratoDetailsDto> page = contratoService.listFiltered(pageRequest, filtro);

            totalPages = page.getTotalPages();
            paginationBarController.updatePagination();

            renderList(page.getContent());
        } catch (Exception e) {
            System.err.println("Erro ao carregar contratos:");
            e.printStackTrace();
        }
    }

    @Override
    protected Node createListItem(ContratoDetailsDto dto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/components/ListItem.fxml"));
            Node itemNode = loader.load();
            ListItemController controller = loader.getController();

            controller.setDataComLarguras(
                    List.of(
                            dto.getTipoContrato() != null ? dto.getTipoContrato().getLabel() : "N/A",
                            dto.getQtdEnergia() != null ? dto.getQtdEnergia().toString() : "N/A",
                            dto.getQtdEnergiaH() != null ? dto.getQtdEnergiaH().toString() : "N/A",
                            dto.getEstado() != null ? dto.getEstado().getLabel() : "N/A"
                    ),
                    List.of(235.0, 235.0, 235.0, 235.0),
                    clicked -> openContratoPage(dto.getId())
            );

            return itemNode;
        } catch (IOException e) {
            System.err.println("Erro ao carregar item do contrato: " + dto.getId());
            e.printStackTrace();
            return null;
        }
    }

    private void openContratoPage(Integer id) {
//        var scene = basePane.getScene();
//        if (scene == null) return;
//
//        var base = scene.getRoot().getProperties().get("baseController");
//        if (base instanceof BaseLayoutController baseLayout) {
//            baseLayout.navigateTo("/views/contrato.fxml", controller -> {
//                if (controller instanceof ContratoController contratoController) {
//                    contratoController.setContratoId(id);
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

        List.of(dataInicioAscBtn, dataInicioDescBtn, dataFimAscBtn, dataFimDescBtn, estadoAscBtn, estadoDescBtn)
                .forEach(StyleUtils::applyButtonHoverEffect);
    }
}
