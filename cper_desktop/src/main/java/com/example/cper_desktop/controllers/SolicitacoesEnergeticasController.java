package com.example.cper_desktop.controllers;

import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaDetailsDto;
import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaFiltroDto;
import com.example.cper_core.enums.EstadoSolicitacaoEnergetica;
import com.example.cper_core.enums.Prioridade;
import com.example.cper_core.services.SolicitacaoEnergeticaService;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.ZoneOffset;
import java.util.List;

import static com.example.cper_desktop.utils.StyleUtils.*;

@Component
public class SolicitacoesEnergeticasController extends AbstractListController<SolicitacaoEnergeticaDetailsDto, SolicitacaoEnergeticaFiltroDto> {

    @FXML private Button searchBtn;
    @FXML private Button dataAscBtn, dataDescBtn, prioridadeAscBtn, prioridadeDescBtn, estadoAscBtn, estadoDescBtn;
    @FXML private DatePicker dataIni, dataFim;
    @FXML private ComboBox<String> prioridadeCombo, estadoCombo;

    @Autowired private SolicitacaoEnergeticaService solicitacaoService;

    @FXML
    @Override
    public void initialize() {
        this.sortField = "dataSolicitacao";
        this.sortDirection = SortDirection.DESC;
        super.initialize();

        searchBtn.setOnAction(e -> applyFilters());

        dataAscBtn.setOnAction(e -> sortBy("dataSolicitacao", SortDirection.ASC));
        dataDescBtn.setOnAction(e -> sortBy("dataSolicitacao", SortDirection.DESC));
        prioridadeAscBtn.setOnAction(e -> sortBy("prioridade", SortDirection.ASC));
        prioridadeDescBtn.setOnAction(e -> sortBy("prioridade", SortDirection.DESC));
        estadoAscBtn.setOnAction(e -> sortBy("estado", SortDirection.ASC));
        estadoDescBtn.setOnAction(e -> sortBy("estado", SortDirection.DESC));

        for (Prioridade p : Prioridade.values()) prioridadeCombo.getItems().add(p.getLabel());
        for (EstadoSolicitacaoEnergetica e : EstadoSolicitacaoEnergetica.values()) estadoCombo.getItems().add(e.getLabel());

        applyStyles();
    }

    @Override
    protected SolicitacaoEnergeticaFiltroDto createEmptyFilter() {
        SolicitacaoEnergeticaFiltroDto filtro = new SolicitacaoEnergeticaFiltroDto();
        filtro.setIsDeleted(false);
        return filtro;
    }


    private void applyFilters() {
        filtroAtual = filtroAtual != null ? filtroAtual : createEmptyFilter();

        filtroAtual.setDataSolicitacaoInicio(null);
        filtroAtual.setDataSolicitacaoFim(null);
        filtroAtual.setPrioridade(null);
        filtroAtual.setEstado(null);

        if (dataIni.getValue() != null)
            filtroAtual.setDataSolicitacaoInicio(dataIni.getValue().atStartOfDay().atOffset(ZoneOffset.UTC));
        if (dataFim.getValue() != null)
            filtroAtual.setDataSolicitacaoFim(dataFim.getValue().plusDays(1).atStartOfDay().atOffset(ZoneOffset.UTC));

        if (prioridadeCombo.getValue() != null)
            filtroAtual.setPrioridade(Prioridade.valueOf(prioridadeCombo.getValue()));
        if (estadoCombo.getValue() != null)
            filtroAtual.setEstado(EstadoSolicitacaoEnergetica.valueOf(estadoCombo.getValue()));

        currentPage = 0;
        toggleFilters();
        clearFilters();
        carregarLista(filtroAtual);
    }

    private void clearFilters() {
        dataIni.setValue(null);
        dataFim.setValue(null);
        prioridadeCombo.setValue(null);
        estadoCombo.setValue(null);
    }

    private void applyStyles() {
        applyEditStyle(dataIni, true);
        applyEditStyle(dataFim, true);

        bloquearDatePickerSeNaoEditavel(dataIni, () -> true);
        bloquearDatePickerSeNaoEditavel(dataFim, () -> true);

        dataIni.getStyleClass().add("custom-transparent");
        dataFim.getStyleClass().add("custom-transparent");

        applyEditStyle(prioridadeCombo, true);
        applyEditStyle(estadoCombo, true);

        bloquearComboBoxSeNaoEditavel(prioridadeCombo, () -> true);
        bloquearComboBoxSeNaoEditavel(estadoCombo, () -> true);
    }

    @Override
    protected void carregarLista(SolicitacaoEnergeticaFiltroDto filtro) {
        try {
            Sort.Direction springDirection = sortDirection == SortDirection.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
            PageRequest pageRequest = PageRequest.of(currentPage, 20, Sort.by(springDirection, sortField));
            Page<SolicitacaoEnergeticaDetailsDto> page = solicitacaoService.listFiltered(pageRequest, filtro);

            totalPages = page.getTotalPages();
            paginationBarController.updatePagination();

            renderList(page.getContent());
        } catch (Exception e) {
            System.err.println("Erro ao carregar solicitações:");
            e.printStackTrace();
        }
    }

    @Override
    protected Node createListItem(SolicitacaoEnergeticaDetailsDto dto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/components/ListItem.fxml"));
            Node itemNode = loader.load();
            ListItemController controller = loader.getController();

            controller.setDataComLarguras(
                    List.of(
                            dto.getTipoEnergia() != null ? dto.getTipoEnergia().name() : "N/A",
                            dto.getQtdSolicitada() != null ? dto.getQtdSolicitada().toString() : "N/A",
                            dto.getQtdSolicitadaH() != null ? dto.getQtdSolicitadaH().toString() : "N/A",
                            dto.getPrioridade() != null ? dto.getPrioridade().name() : "N/A",
                            dto.getEstado() != null ? dto.getEstado().name() : "N/A"
                    ),
                    List.of(182.0, 182.0, 182.0, 182.0, 182.0),
                    clicked -> openSolicitacaoPage(dto.getId())
            );
            return itemNode;
        } catch (IOException e) {
            System.err.println("Erro ao carregar item da solicitação: " + dto.getId());
            e.printStackTrace();
            return null;
        }
    }

    private void openSolicitacaoPage(Integer id) {
//        var scene = basePane.getScene();
//        if (scene == null) return;
//
//        var base = scene.getRoot().getProperties().get("baseController");
//        if (base instanceof BaseLayoutController baseLayout) {
//            baseLayout.navigateTo("/views/solicitacao.fxml", controller -> {
//                if (controller instanceof SolicitacaoController sc) {
//                    sc.setSolicitacaoId(id);
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

        List.of(dataAscBtn, dataDescBtn, prioridadeAscBtn, prioridadeDescBtn, estadoAscBtn, estadoDescBtn)
                .forEach(StyleUtils::applyButtonHoverEffect);
    }
}
