package com.example.cper_desktop.controllers;

import com.example.cper_core.dtos.cliente.ClienteFiltroDto;
import com.example.cper_core.dtos.contrato.ContratoFiltroDto;
import com.example.cper_core.dtos.fatura.FaturaFiltroDto;
import com.example.cper_core.dtos.pedido_geracao.PedidoGeracaoFiltroDto;
import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaFiltroDto;
import com.example.cper_core.enums.Setor;
import com.example.cper_desktop.controllers.reusable_components.LoadingOverlayController;
import com.example.cper_desktop.controllers.reusable_components.MenuButtonItem;
import com.example.cper_desktop.controllers.reusable_components.ToastNotificationController;
import com.example.cper_desktop.utils.InternalNavigationUtils;
import com.example.cper_desktop.utils.NavigationUtils;
import com.example.cper_desktop.utils.SessionStorage;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class BaseLayoutController {

    private static final double MENU_WIDTH = 350;
    private static final Duration ANIM_DURATION = Duration.millis(200);
    private static final Duration FADE_DURATION = Duration.millis(200);

    private static final Logger logger = Logger.getLogger(BaseLayoutController.class.getName());

    @FXML private StackPane rootStack;
    @FXML private StackPane mainContent;
    @FXML private VBox menuVBox;
    @FXML private VBox menuVBox1;
    @FXML private AnchorPane menuOverlay;
    @FXML private AnchorPane menuPane;
    @FXML private Label setorLabel;

    private LoadingOverlayController loadingOverlayController;
    private ToastNotificationController toastController;

    private boolean menuAberto = false;
    private Integer setorMenuAtivo;
    private final List<Button> botoesMenu = new ArrayList<>();
    private final Map<Setor, Runnable> menuBuilders = new EnumMap<>(Setor.class);

    @FXML
    public void initialize() {
        prepararAnimacaoFechoInicial();
        menuOverlay.setVisible(false);

        inicializarMenuBuilders();
        carregarOverlay();
        carregarToast();

        setorMenuAtivo = SessionStorage.getSetorPrincipal();
        if (setorMenuAtivo != null) {
            carregarMenuSetor();
        } else {
            logger.warning("Setor principal é nulo. Menu não carregado.");
        }

        // Injetar navegação centralizada
        InternalNavigationUtils.initialize(this, mainContent, loadingOverlayController, toastController);

        Platform.runLater(() -> {
            if (mainContent.getScene() != null) {
                mainContent.getScene().getStylesheets().add(
                        Objects.requireNonNull(getClass().getResource("/styles/MenuButtonItem.css")).toExternalForm());
            }

            if (rootStack.getScene() != null) {
                rootStack.getScene().getRoot().getProperties().put("baseController", this);
            }
        });
    }

    @FXML
    private void toggleMenu() {
        toggleMenu(false);
    }

    private void toggleMenu(boolean forceClose) {
        if (menuAberto) {
            fecharMenuAnimado();
        } else if (!forceClose) {
            abrirMenuAnimado();
        }
        menuAberto = !menuAberto;
    }

    @FXML
    private void onSettingsClick() {
        InternalNavigationUtils.goTo("/views/perfil.fxml");
    }

    @FXML
    private void onLogOutClick() {
        SessionStorage.clear();
        NavigationUtils.goTo("Login.fxml");
    }

    private void carregarOverlay() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/components/LoadingOverlay.fxml"));
            AnchorPane overlay = loader.load();
            loadingOverlayController = loader.getController();

            overlay.setMouseTransparent(true);
            rootStack.getChildren().add(overlay);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao carregar overlay de loading", e);
        }
    }

    private void carregarToast() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/components/ToastNotification.fxml"));
            AnchorPane toast = loader.load();
            toastController = loader.getController();

            rootStack.getChildren().add(toast);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao carregar o toast", e);
        }
    }

    private void inicializarMenuBuilders() {
        menuBuilders.put(Setor.ADMINISTRATIVO, this::menusAdministrativo);
        menuBuilders.put(Setor.COMERCIAL, this::menusComercial);
        menuBuilders.put(Setor.FINANCEIRO, this::menusFinanceiro);
        menuBuilders.put(Setor.PRODUCAO_CENTRAL, this::menusProducaoCentral);
//        menuBuilders.put(Setor.PRODUCAO_GERADOR, this::menusProducaoGerador);
//        menuBuilders.put(Setor.INSPECAO, this::menusInspecao);
//        menuBuilders.put(Setor.PLANEAMENTO, this::menusPlaneamento);
//        menuBuilders.put(Setor.MANUTENCAO, this::menusManutencao);
//        menuBuilders.put(Setor.ARMAZEM, this::menusArmazem);
//        menuBuilders.put(Setor.CONSTRUCAO, this::menusConstrucao);
//        menuBuilders.put(Setor.RH, this::menusRh);
    }

    private void carregarMenuSetor() {
        menuVBox.getChildren().clear();
        botoesMenu.clear();

        if (setorMenuAtivo == null) {
            logger.warning("setorMenuAtivo nulo ao carregar menu.");
            return;
        }

        Setor setorAtual = Setor.fromId(setorMenuAtivo);
        Integer setorPrincipal = SessionStorage.getSetorPrincipal();
        Set<Integer> setoresDisponiveis = SessionStorage.getSetoresDisponiveis();

        if (setoresDisponiveis != null && setoresDisponiveis.size() > 1) {
            menuVBox.getChildren().add(new MenuButtonItem("Mudar de Setor", this::mostrarSetoresDisponiveis));
        }

        Runnable builder = menuBuilders.get(setorAtual);
        if (builder != null) builder.run();

        if (setorPrincipal != null && !setorMenuAtivo.equals(setorPrincipal)) {
            menuVBox.getChildren().add(new MenuButtonItem("Voltar ao Setor Principal", () -> {
                setorMenuAtivo = setorPrincipal;
                carregarMenuSetor();
            }));
        }
    }

    private void mostrarSetoresDisponiveis() {
        fadeOut(menuVBox);
        menuVBox1.getChildren().clear();

        Label titulo = new Label("Selecionar Setor");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family: 'Segoe UI'; -fx-padding: 10px;");
        menuVBox1.getChildren().add(titulo);

        Set<Integer> setoresDisponiveis = SessionStorage.getSetoresDisponiveis();
        for (Integer id : setoresDisponiveis) {
            if (id.equals(Setor.SEM_SETOR.getId())) continue;
            Setor s = Setor.fromId(id);
            menuVBox1.getChildren().add(
                    new MenuButtonItem(s.getLabel(), () -> {
                        setorMenuAtivo = s.getId();
                        carregarMenuSetor();
                        voltarAoMenuPrincipal();
                    }));
        }
        menuVBox1.getChildren().add(new MenuButtonItem("Voltar", this::voltarAoMenuPrincipal));
        fadeIn(menuVBox1);
    }

    private void voltarAoMenuPrincipal() {
        fadeOut(menuVBox1);
        fadeIn(menuVBox);
    }

    private void fadeOut(VBox vbox) {
        createFadeTransition(vbox, 1.0, 0.0, () -> {
            vbox.setVisible(false);
            vbox.setManaged(false);
        }).play();
    }

    private void fadeIn(VBox vbox) {
        vbox.setOpacity(0.0);
        vbox.setVisible(true);
        vbox.setManaged(true);
        createFadeTransition(vbox, 0.0, 1.0, null).play();
    }

    private FadeTransition createFadeTransition(VBox node, double from, double to, Runnable onFinished) {
        FadeTransition ft = new FadeTransition(FADE_DURATION, node);
        ft.setFromValue(from);
        ft.setToValue(to);
        if (onFinished != null) ft.setOnFinished(e -> onFinished.run());
        return ft;
    }

    private TranslateTransition createTranslateTransition(double fromX, double toX) {
        TranslateTransition tt = new TranslateTransition(ANIM_DURATION, menuPane);
        tt.setFromX(fromX);
        tt.setToX(toX);
        return tt;
    }

    private void abrirMenuAnimado() {
        menuVBox1.setVisible(false);
        menuVBox1.setManaged(false);
        menuVBox.setOpacity(1.0);
        menuVBox.setVisible(true);
        menuVBox.setManaged(true);

        carregarMenuSetor();
        menuOverlay.setVisible(true);
        createTranslateTransition(-MENU_WIDTH, 0).play();
    }

    private void fecharMenuAnimado() {
        TranslateTransition tt = createTranslateTransition(0, -MENU_WIDTH);
        tt.setOnFinished(e -> menuOverlay.setVisible(false));
        tt.play();
    }

    private void prepararAnimacaoFechoInicial() {
        menuPane.setTranslateX(-MENU_WIDTH);
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

    private final Map<String, Pair<String, Consumer<Object>>> menuItemsAdministrativo = new LinkedHashMap<>() {{
        // Adicionar os itens do menu administrativo
    }};

    private void menusAdministrativo() {
        setorLabel.setText(Setor.ADMINISTRATIVO.getLabel());

        menuItemsAdministrativo.forEach((nome, par) -> {
            String fxml = par.getKey();
            Consumer<Object> callback = par.getValue();
            addMenuButton(nome, fxml, callback);
        });
    }

    private final Map<String, Pair<String, Consumer<Object>>> menuItemsComercial = new LinkedHashMap<>() {{
        put("Clientes", new Pair<>("/views/Clientes.fxml", controller -> {
            if (controller instanceof ClientesController c) {
                ClienteFiltroDto filtro = new ClienteFiltroDto();
                filtro.setIsDeleted(false);
                c.setFiltroInicial(filtro);
            }
        }));
        put("Contratos", new Pair<>("/views/Contratos.fxml", controller -> {
            if (controller instanceof ContratosController c) {
                ContratoFiltroDto filtro = new ContratoFiltroDto();
                filtro.setIsDeleted(false);
                c.setFiltroInicial(filtro);
            }
        }));
        put("Solicitações Energéticas", new Pair<>("/views/SolicitacoesEnergeticas.fxml", controller -> {
            if (controller instanceof SolicitacoesEnergeticasController c) {
                SolicitacaoEnergeticaFiltroDto filtro = new SolicitacaoEnergeticaFiltroDto();
                filtro.setIsDeleted(false);
                c.setFiltroInicial(filtro);
            }
        }));
        put("Suporte Técnico", new Pair<>("/views/SuporteTecnico.fxml", controller -> {}));
    }};

    private void menusComercial() {
        setorLabel.setText(Setor.COMERCIAL.getLabel());

        menuItemsComercial.forEach((nome, par) -> {
            String fxml = par.getKey();
            Consumer<Object> callback = par.getValue();
            addMenuButton(nome, fxml, callback);
        });
    }

    private final Map<String, Pair<String, Consumer<Object>>> menuItemsFinanceiro = new LinkedHashMap<>() {{
        put("Clientes", new Pair<>("/views/Clientes.fxml", controller -> {
            if (controller instanceof ClientesController c) {
                ClienteFiltroDto filtro = new ClienteFiltroDto();
                filtro.setIsDeleted(false);
                c.setFiltroInicial(filtro);
            }
        }));
        put("Contratos", new Pair<>("/views/Contratos.fxml", controller -> {
            if (controller instanceof ContratosController c) {
                ContratoFiltroDto filtro = new ContratoFiltroDto();
                filtro.setIsDeleted(false);
                c.setFiltroInicial(filtro);
            }
        }));
        put("Faturas", new Pair<>("/views/Faturas.fxml", controller -> {
            if (controller instanceof FaturasController c) {
                FaturaFiltroDto filtro = new FaturaFiltroDto();
                filtro.setIsDeleted(false);
                c.setFiltroInicial(filtro);
            }
        }));
    }};

    private void menusFinanceiro() {
        setorLabel.setText(Setor.FINANCEIRO.getLabel());

        menuItemsFinanceiro.forEach((nome, par) -> {
            String fxml = par.getKey();
            Consumer<Object> callback = par.getValue();
            addMenuButton(nome, fxml, callback);
        });
    }

    private final Map<String, Pair<String, Consumer<Object>>> menuItemsProducaoCentral= new LinkedHashMap<>() {{
        put("Centrais", new Pair<>("/views/Centrais.fxml", controller -> {
            if (controller instanceof ClientesController c) {
                ClienteFiltroDto filtro = new ClienteFiltroDto();
                filtro.setIsDeleted(false);
                c.setFiltroInicial(filtro);
            }
        }));
        put("Contratos", new Pair<>("/views/Contratos.fxml", controller -> {
            if (controller instanceof ContratosController c) {
                ContratoFiltroDto filtro = new ContratoFiltroDto();
                filtro.setIsDeleted(false);
                c.setFiltroInicial(filtro);
            }
        }));
        put("Pedidos Geração", new Pair<>("/views/PedidosGeracao.fxml", controller -> {
            if (controller instanceof PedidosGeracaoController c) {
                PedidoGeracaoFiltroDto filtro = new PedidoGeracaoFiltroDto();
                filtro.setIsDeleted(false);
                c.setFiltroInicial(filtro);
            }
        }));
    }};

    private void menusProducaoCentral() {
        setorLabel.setText(Setor.PRODUCAO_CENTRAL.getLabel());

        menuItemsProducaoCentral.forEach((nome, par) -> {
            String fxml = par.getKey();
            Consumer<Object> callback = par.getValue();
            addMenuButton(nome, fxml, callback);
        });
    }

    private void addMenuButton(String nome, String fxmlPath, Consumer<Object> controllerCallback) {
        MenuButtonItem item = new MenuButtonItem(nome, () -> {
            InternalNavigationUtils.goTo(fxmlPath, controllerCallback);
            highlightMenu(nome);
        });
        menuVBox.getChildren().add(item);
        botoesMenu.add(item.getButton());
    }

//    private void menusProducaoCentral() {
//        setorLabel.setText("Produção - Central");
//        addMenuButton("Centrais", "/views/Centrais.fxml");
//        addMenuButton("Contratos", "/views/Contratos.fxml");
//    }
//
//    private void menusProducaoGerador() {
//        setorLabel.setText("Produção - Gerador");
//        addMenuButton("Pedidos Geração", "/views/PedidosGeracao.fxml");
//    }
//
//    private void menusInspecao() {
//        setorLabel.setText("Inspeção");
//        addMenuButton("Inspeções", "/views/inspecoes.fxml");
//        addMenuButton("Relatórios Inspeção", "/views/relatorios_inspecao.fxml");
//    }
//
//    private void menusPlaneamento() {
//        setorLabel.setText("Planeamento");
//        addMenuButton("Planeamento", "/views/planeamento.fxml");
//        addMenuButton("Cronogramas", "/views/cronogramas.fxml");
//    }
//
//    private void menusManutencao() {
//        setorLabel.setText("Manutenção");
//        addMenuButton("Avarias", "/views/avarias.fxml");
//        addMenuButton("Intervenções", "/views/intervencoes.fxml");
//    }
//
//    private void menusArmazem() {
//        setorLabel.setText("Armazém");
//        addMenuButton("Materiais", "/views/materiais.fxml");
//        addMenuButton("Stock", "/views/stock.fxml");
//    }
//
//    private void menusConstrucao() {
//        setorLabel.setText("Construção");
//        addMenuButton("Projetos de Obra", "/views/projetos_obra.fxml");
//        addMenuButton("Relatórios de Obra", "/views/relatorios_obra.fxml");
//    }
//
//    private void menusRh() {
//        setorLabel.setText("Recursos Humanos");
//        addMenuButton("Funcionários", "/views/funcionarios.fxml");
//        addMenuButton("Presenças", "/views/presencas.fxml");
//    }
}
