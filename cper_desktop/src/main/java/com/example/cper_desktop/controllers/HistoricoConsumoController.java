package com.example.cper_desktop.controllers;

import com.example.cper_core.dtos.historico_consumo.HistoricoConsumoDetailsDto;
import com.example.cper_core.dtos.historico_consumo.HistoricoConsumoFiltroDto;
import com.example.cper_core.dtos.historico_consumo.HistoricoConsumoMetricasDto;
import com.example.cper_core.services.HistoricoConsumoService;
import com.example.cper_desktop.controllers.base.AbstractListController;
import com.example.cper_desktop.controllers.reusable_components.ListItemController;
import com.example.cper_desktop.utils.StyleUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.example.cper_desktop.utils.StyleUtils.*;

@Component
public class HistoricoConsumoController extends AbstractListController<HistoricoConsumoDetailsDto, HistoricoConsumoFiltroDto> {

    @FXML private DatePicker dataIni, dataFim;
    @FXML private Button searchBtn;
    @FXML private Button dataAscBtn, dataDescBtn;

    @FXML private Label energiaTotal, consumoMedio, registos, periodo;
    @FXML private AreaChart<String, Number> areaChart;

    @Autowired private HistoricoConsumoService historicoService;

    @FXML
    @Override
    public void initialize() {
        this.sortField = "dataRegisto";
        this.sortDirection = SortDirection.DESC;
        super.initialize();

        searchBtn.setOnAction(e -> applyFilters());
        dataAscBtn.setOnAction(e -> sortBy("dataRegisto", SortDirection.ASC));
        dataDescBtn.setOnAction(e -> sortBy("dataRegisto", SortDirection.DESC));

        applyEditStyle(dataIni, true);
        applyEditStyle(dataFim, true);
        bloquearDatePickerSeNaoEditavel(dataIni, () -> true);
        bloquearDatePickerSeNaoEditavel(dataFim, () -> true);

        dataIni.getStyleClass().add("custom-transparent");
        dataFim.getStyleClass().add("custom-transparent");
    }

    private void applyFilters() {
        if (filtroAtual == null) filtroAtual = createEmptyFilter();

        filtroAtual.setDataRegistoInicio(dataIni.getValue() != null ? dataIni.getValue().atStartOfDay().atOffset(OffsetDateTime.now().getOffset()) : null);
        filtroAtual.setDataRegistoFim(dataFim.getValue() != null ? dataFim.getValue().plusDays(1).atStartOfDay().atOffset(OffsetDateTime.now().getOffset()) : null);

        currentPage = 0;
        toggleFilters();
        clearFilters();
        carregarLista(filtroAtual);
    }

    private void clearFilters() {
        dataIni.setValue(null);
        dataFim.setValue(null);
    }

    @Override
    protected void carregarLista(HistoricoConsumoFiltroDto filtro) {
        try {
            var pageRequest = buildPageRequest();
            var page = historicoService.listFiltered(pageRequest, filtro);

            totalPages = page.getTotalPages();
            paginationBarController.updatePagination();

            List<HistoricoConsumoDetailsDto> lista = page.getContent();
            renderList(lista);
            atualizarMetricas(filtro);
            atualizarGrafico(lista);
        } catch (Exception e) {
            System.err.println("Erro ao carregar histórico de consumo:");
            e.printStackTrace();
        }
    }

    private void atualizarMetricas(HistoricoConsumoFiltroDto filtro) {
        try {
            HistoricoConsumoMetricasDto metricas = historicoService.calcularMetricas(filtro);
            energiaTotal.setText(metricas.getEnergiaTotal() != null ? metricas.getEnergiaTotal() + " kWh" : "0 kWh");
            consumoMedio.setText(metricas.getConsumoMedioPorHora() != null ? metricas.getConsumoMedioPorHora() + " kWh/h" : "0 kWh/h");
            registos.setText(String.valueOf(metricas.getTotalRegistos()));

            if (metricas.getDataMaisAntiga() != null && metricas.getDataMaisRecente() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                periodo.setText(metricas.getDataMaisAntiga().format(formatter) + " - " + metricas.getDataMaisRecente().format(formatter));
            } else {
                periodo.setText("-");
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar métricas de consumo:");
            e.printStackTrace();
        }
    }

    private void atualizarGrafico(List<HistoricoConsumoDetailsDto> dados) {
        areaChart.getData().clear();
        if (dados == null || dados.isEmpty()) return;

        final int MAX_POINTS = 20;

        dados = dados.stream()
                .sorted(Comparator.comparing(HistoricoConsumoDetailsDto::getDataRegisto))
                .toList();

        int total = dados.size();
        int groupSize = Math.max(1, total / MAX_POINTS);

        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Energia acumulada");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        BigDecimal acumulado = BigDecimal.ZERO;

        List<String> tooltips = new ArrayList<>();

        for (int i = 0; i < total; i += groupSize) {
            int end = Math.min(i + groupSize, total);
            List<HistoricoConsumoDetailsDto> grupo = dados.subList(i, end);

            BigDecimal somaGrupo = grupo.stream()
                    .map(HistoricoConsumoDetailsDto::getEnergiaTotal)
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            acumulado = acumulado.add(somaGrupo);

            OffsetDateTime inicio = grupo.getFirst().getDataRegisto();
            OffsetDateTime fim = grupo.getLast().getDataRegisto();
            String label = inicio.toLocalDate().format(formatter) + " - " + fim.toLocalDate().format(formatter);

            XYChart.Data<String, Number> dataPoint = new XYChart.Data<>(label, acumulado);
            serie.getData().add(dataPoint);

            tooltips.add("Intervalo: " + label + "\nEnergia acumulada: " +
                    acumulado.setScale(2, RoundingMode.HALF_UP) + " kWh");
        }

        areaChart.getData().add(serie);

        Platform.runLater(() -> {
            for (int i = 0; i < serie.getData().size(); i++) {
                XYChart.Data<String, Number> dataPoint = serie.getData().get(i);
                Node node = dataPoint.getNode();
                if (node != null) {
                    Tooltip tooltip = new Tooltip(tooltips.get(i));
                    Tooltip.install(node, tooltip);
                }
            }
        });
    }

    @Override
    protected HistoricoConsumoFiltroDto createEmptyFilter() {
        HistoricoConsumoFiltroDto filtro = new HistoricoConsumoFiltroDto();
        filtro.setIsDeleted(false);
        return filtro;
    }

    @Override
    protected Node createListItem(HistoricoConsumoDetailsDto dto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/components/ListItem.fxml"));
            Node node = loader.load();
            ListItemController controller = loader.getController();

            controller.setDataComLarguras(
                    List.of(
                            formatDateTime(dto.getDataRegisto()),
                            formatBigDecimal(dto.getEnergiaTotal()) + " kWh",
                            formatBigDecimal(dto.getConsumoPorHora()) + " kWh/h"
                    ),
                    List.of(270.0, 270.0, 270.0),
                    null
            );

            return node;
        } catch (IOException e) {
            System.err.println("Erro ao carregar item do histórico:");
            e.printStackTrace();
            return null;
        }
    }

    private String formatDateTime(OffsetDateTime dateTime) {
        return dateTime != null ? dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : "N/A";
    }

    private String formatBigDecimal(BigDecimal value) {
        return value != null ? value.setScale(2, RoundingMode.HALF_UP).toString() : "0.00";
    }

    @Override
    protected void applyHoverEffects() {
        applyStackPaneHoverEffect(filtersBtn);
        applyStackPaneHoverEffect(sortBtn);
        applyStackPaneHoverEffect(backBtn);
        applySubtleOverlayHoverEffect(searchBtn);

        List.of(dataAscBtn, dataDescBtn).forEach(StyleUtils::applyButtonHoverEffect);
    }
}
