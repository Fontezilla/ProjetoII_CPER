package com.example.cper_desktop.controllers;

import com.example.cper_core.enums.Setor;
import com.example.cper_desktop.utils.Navigation;
import com.example.cper_desktop.utils.SessionStorage;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class BaseLayoutController {

    @FXML private VBox menuVBox;
    @FXML private VBox menuVBox1;
    @FXML private StackPane mainContent;
    @FXML private AnchorPane menuOverlay;
    @FXML private AnchorPane menuPane;
    @FXML private Label setorLabel;

    private boolean menuAberto = false;
    private final List<Button> botoesSetores = new ArrayList<>();
    private final List<Button> botoesMenu = new ArrayList<>();
    private Button botaoAtual = null;

    @FXML
    public void initialize() {
        menuOverlay.setVisible(false);
        carregarMenuSetor();
        prepararAnimacaoFechoInicial();
    }

    private void carregarMenuSetor() {
        menuVBox.getChildren().clear();
        botoesMenu.clear();
        botoesSetores.clear();

        Integer setorPrincipalId = SessionStorage.getSetorPrincipal();
        Set<Integer> setoresAssociados = SessionStorage.getSetoresAssociados();

        if (setorPrincipalId == null || setorPrincipalId == -1) return;

        // Mostra botão para mudar de setor se o setor for 0 (ADMINISTRATIVO) ou tiver outros setores disponíveis
        if (setorPrincipalId == 0 || (setoresAssociados != null && !setoresAssociados.isEmpty())) {
            Button mudarSetorBtn = new Button("Mudar de Setor");
            mudarSetorBtn.setPrefWidth(180);
            mudarSetorBtn.setOnAction(e -> mostrarSetoresDisponiveis());
            menuVBox.getChildren().add(mudarSetorBtn);
        }

        Setor setorPrincipal = Setor.fromId(setorPrincipalId);
        if (setorPrincipal == null) return;

        switch (setorPrincipal) {
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
    }

    private void mostrarSetoresDisponiveis() {
        fadeOut(menuVBox);

        menuVBox1.getChildren().clear();

        Label titulo = new Label("Selecionar Setor");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        menuVBox1.getChildren().add(titulo);

        Set<Integer> setoresAssociados = SessionStorage.getSetoresAssociados();
        if (setoresAssociados != null && !setoresAssociados.isEmpty()) {
            for (Integer setorId : setoresAssociados) {
                Setor setor = Setor.fromId(setorId);
                if (setor != null) {
                    Button setorBtn = new Button(setor.name());
                    setorBtn.setPrefWidth(180);
                    setorBtn.setOnAction(ev -> {
                        SessionStorage.setSetorPrincipal(setor.getId());
                        Navigation.reloadBaseLayout();
                    });
                    menuVBox1.getChildren().add(setorBtn);
                }
            }
        }
        Button voltarBtn = new Button("← Voltar");
        voltarBtn.setPrefWidth(180);
        voltarBtn.setOnAction(e -> voltarAoMenuPrincipal());
        menuVBox1.getChildren().add(voltarBtn);

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
        Button btn = new Button(nome);
        btn.setPrefWidth(180);
        btn.setOnAction(e -> {
            carregarPagina(fxmlPath);
            highlightMenu(nome);
        });
        menuVBox.getChildren().add(btn);
        botoesMenu.add(btn);

        System.out.println("Botão adicionado: " + nome); // ← DEBUG
    }


    private void carregarPagina(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();
            mainContent.getChildren().setAll(view);
            toggleMenu();
        } catch (IOException e) {
            e.printStackTrace();
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
        // Garante que o menuVBox1 (selector de setores) não aparece por cima
        menuVBox1.setVisible(false);
        menuVBox1.setManaged(false);

        // Garante que o menuVBox (menu do setor atual) aparece
        menuVBox.setVisible(true);
        menuVBox.setManaged(true);

        menuOverlay.setVisible(true); // agora o overlay pode ser ativado sem problemas

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
                botaoAtual = btn;
            } else {
                btn.setStyle(null);
            }
        }
    }

    // Menus

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
