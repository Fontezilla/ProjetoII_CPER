package com.example.cper_desktop.controllers;

import com.example.cper_core.enums.Setor;
import com.example.cper_desktop.utils.Navigation;
import com.example.cper_desktop.utils.SessionStorage;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.cper_desktop.reusable_components.MenuButtonItem;

@Component
public class BaseLayoutController {

    @FXML private VBox menuVBox;
    @FXML private VBox menuVBox1;
    @FXML private StackPane mainContent;
    @FXML private AnchorPane menuOverlay;
    @FXML private AnchorPane menuPane;
    @FXML private Label setorLabel;

    private boolean menuAberto = false;
    private final List<Button> botoesMenu = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(BaseLayoutController.class.getName());
    private Integer setorMenuAtivo = null;

    @FXML
    public void initialize() {
        menuOverlay.setVisible(false);
        prepararAnimacaoFechoInicial();

        // Primeiro obtém o setor principal
        setorMenuAtivo = SessionStorage.getSetorPrincipal();

        // Só carrega menu se estiver definido
        if (setorMenuAtivo != null) {
            carregarMenuSetor();
        } else {
            logger.warning("Setor principal está nulo. Não foi possível carregar o menu.");
        }

        Platform.runLater(() -> {
            if (mainContent.getScene() != null) {
                mainContent.getScene().getStylesheets().add(
                        Objects.requireNonNull(getClass().getResource("/styles/MenuButtonItem.css")).toExternalForm()
                );
            }
        });
    }

    private void carregarMenuSetor() {
        if (setorMenuAtivo == null) {
            logger.warning("setorMenuAtivo está nulo ao tentar carregar o menu.");
            return;
        }

        menuVBox.getChildren().clear();
        botoesMenu.clear();

        Setor setorAtual = Setor.fromId(setorMenuAtivo);
        Integer setorPrincipal = SessionStorage.getSetorPrincipal();
        Set<Integer> setoresDisponiveis = SessionStorage.getSetoresDisponiveis();

        // Mostrar botão "Mudar de Setor" se houver mais de um setor possível
        if (setoresDisponiveis != null && setoresDisponiveis.size() > 1) {
            MenuButtonItem mudarSetorItem = new MenuButtonItem("Mudar de Setor", this::mostrarSetoresDisponiveis);
            menuVBox.getChildren().add(mudarSetorItem);
        }

        // Mostrar os menus específicos do setor
        switch (setorAtual) {
            case ADMINISTRATIVO -> menusAdministrativo();
            case COMERCIAL -> menusComercial();
            case FINANCEIRO -> menusFinanceiro();
            case PRODUCAO_CENTRAL -> menusProducaoCentral();
            case PRODUCAO_GERADOR -> menusProducaoGerador();
            case INSPECAO -> menusInspecao();
            case PLANEAMENTO -> menusPlaneamento();
            case MANUTENCAO -> menusManutencao();
            case ARMAZEM -> menusArmazem();
            case CONSTRUCAO -> menusConstrucao();
            case RH -> menusRh();
        }

        // Mostrar botão "Voltar ao Setor Principal" se estivermos noutro setor
        if (setorPrincipal != null && !setorMenuAtivo.equals(setorPrincipal)) {
            MenuButtonItem voltarPrincipal = new MenuButtonItem("Voltar ao Setor Principal", () -> {
                this.setorMenuAtivo = setorPrincipal;
                carregarMenuSetor();
            });
            menuVBox.getChildren().add(voltarPrincipal);
        }
    }

    private void mostrarSetoresDisponiveis() {
        fadeOut(menuVBox);
        menuVBox1.getChildren().clear();

        Label titulo = new Label("Selecionar Setor");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        menuVBox1.getChildren().add(titulo);

        Set<Integer> setoresDisponiveis = SessionStorage.getSetoresDisponiveis();

        for (Integer setorId : setoresDisponiveis) {
            Setor setor = Setor.fromId(setorId);

            MenuButtonItem setorItem = new MenuButtonItem(setor.name(), () -> {
                this.setorMenuAtivo = setor.getId();
                carregarMenuSetor();
                voltarAoMenuPrincipal();
            });

            menuVBox1.getChildren().add(setorItem);
        }

        MenuButtonItem voltarItem = new MenuButtonItem("Voltar", this::voltarAoMenuPrincipal);
        menuVBox1.getChildren().add(voltarItem);

        fadeIn(menuVBox1);
    }

    private void voltarAoMenuPrincipal() {
        fadeOut(menuVBox1);
        fadeIn(menuVBox);
    }

    private void fadeOut(VBox vbox) {
        FadeTransition ft = new FadeTransition(Duration.millis(200), vbox);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setOnFinished(e -> {
            vbox.setVisible(false);
            vbox.setManaged(false);
        });
        ft.play();
    }

    private void fadeIn(VBox vbox) {
        vbox.setOpacity(0.0);
        vbox.setVisible(true);
        vbox.setManaged(true);

        FadeTransition ft = new FadeTransition(Duration.millis(200), vbox);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    private void addMenuButton(String nome, String fxmlPath) {
        MenuButtonItem item = new MenuButtonItem(nome, () -> {
            carregarPagina(fxmlPath);
            highlightMenu(nome);
        });
        menuVBox.getChildren().add(item);
        botoesMenu.add(item.getButton());

        System.out.println("Botão adicionado: " + nome);
    }

    private void carregarPagina(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();
            mainContent.getChildren().setAll(view);
            toggleMenu();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao carregar página FXML: " + fxmlPath, e);
        }
    }

    @FXML
    private void toggleMenu() {
        if (menuAberto) {
            fecharMenuAnimado();
        } else {
            abrirMenuAnimado();
        }
        menuAberto = !menuAberto;
    }

    private void abrirMenuAnimado() {
        menuVBox1.setVisible(false);
        menuVBox1.setManaged(false);

        menuVBox.setOpacity(1.0);
        menuVBox.setVisible(true);
        menuVBox.setManaged(true);

        menuVBox.getChildren().clear();
        carregarMenuSetor();

        menuOverlay.setVisible(true);

        TranslateTransition trans = new TranslateTransition(Duration.millis(200), menuPane);
        trans.setFromX(-350);
        trans.setToX(0);
        trans.play();
    }

    private void fecharMenuAnimado() {
        TranslateTransition trans = new TranslateTransition(Duration.millis(200), menuPane);
        trans.setFromX(0);
        trans.setToX(-350);
        trans.setOnFinished(e -> menuOverlay.setVisible(false));
        trans.play();
    }

    private void prepararAnimacaoFechoInicial() {
        menuPane.setTranslateX(-350);
    }

    @FXML
    private void onSettingsClick() {
        carregarPagina("/views/configuracoes.fxml");
        highlightMenu("Definições");
    }

    @FXML
    private void onLogOutClick() {
        SessionStorage.clear();
        Navigation.goTo("Login.fxml");
    }

    private void highlightMenu(String titulo) {
        for (Button btn : botoesMenu) {
            if (btn.getText().equalsIgnoreCase(titulo)) {
                btn.setStyle("-fx-background-color: #f1c40f; -fx-text-fill: white;");
            } else {
                btn.setStyle(null);
            }
        }
    }

    // Menus por setor

    private void menusAdministrativo() {
        setorLabel.setText("Administrativo");
        addMenuButton("Utilizadores", "/views/utilizadores.fxml");
        addMenuButton("Logs do Sistema", "/views/logs.fxml");
    }

    private void menusComercial() {
        setorLabel.setText("Comercial");
        addMenuButton("Clientes", "/views/clientes.fxml");
        addMenuButton("Contratos", "/views/contratos.fxml");
        addMenuButton("Vendas", "/views/vendas.fxml");
    }

    private void menusFinanceiro() {
        setorLabel.setText("Financeiro");
        addMenuButton("Faturas", "/views/faturas.fxml");
        addMenuButton("Pagamentos", "/views/pagamentos.fxml");
    }

    private void menusProducaoCentral() {
        setorLabel.setText("Produção - Central");
        addMenuButton("Centrais", "/views/centrais.fxml");
        addMenuButton("Geradores", "/views/geradores.fxml");
    }

    private void menusProducaoGerador() {
        setorLabel.setText("Produção - Gerador");
        addMenuButton("Geradores", "/views/geradores.fxml");
        addMenuButton("Produção", "/views/producao.fxml");
    }

    private void menusInspecao() {
        setorLabel.setText("Inspeção");
        addMenuButton("Inspeções", "/views/inspecoes.fxml");
        addMenuButton("Relatórios Inspeção", "/views/relatorios_inspecao.fxml");
    }

    private void menusPlaneamento() {
        setorLabel.setText("Planeamento");
        addMenuButton("Planeamento", "/views/planeamento.fxml");
        addMenuButton("Cronogramas", "/views/cronogramas.fxml");
    }

    private void menusManutencao() {
        setorLabel.setText("Manutenção");
        addMenuButton("Avarias", "/views/avarias.fxml");
        addMenuButton("Intervenções", "/views/intervencoes.fxml");
    }

    private void menusArmazem() {
        setorLabel.setText("Armazém");
        addMenuButton("Materiais", "/views/materiais.fxml");
        addMenuButton("Stock", "/views/stock.fxml");
    }

    private void menusConstrucao() {
        setorLabel.setText("Construção");
        addMenuButton("Projetos de Obra", "/views/projetos_obra.fxml");
        addMenuButton("Relatórios de Obra", "/views/relatorios_obra.fxml");
    }

    private void menusRh() {
        setorLabel.setText("Recursos Humanos");
        addMenuButton("Funcionários", "/views/funcionarios.fxml");
        addMenuButton("Presenças", "/views/presencas.fxml");
    }
}
