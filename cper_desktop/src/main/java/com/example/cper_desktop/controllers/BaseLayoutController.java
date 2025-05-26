package com.example.cper_desktop.controllers;

import com.example.cper_core.enums.Setor;
import com.example.cper_desktop.utils.Navigation;
import com.example.cper_desktop.utils.SessionStorage;
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

        // Caso não tenha setor principal válido, não faz nada
        if (setorPrincipalId == null || setorPrincipalId == -1) return;

        // Adiciona botão "Mudar de Setor" se estiver no setor 0 ou houver setores associados
        if (setorPrincipalId == 0 || (setoresAssociados != null && !setoresAssociados.isEmpty())) {
            Button mudarSetorBtn = new Button("Mudar de Setor");
            mudarSetorBtn.setPrefWidth(180);
            mudarSetorBtn.setOnAction(e -> toggleSetores());
            menuVBox.getChildren().add(mudarSetorBtn);
        }

        if (setoresAssociados != null && !setoresAssociados.isEmpty()) {
            for (Integer setorId : setoresAssociados) {
                Setor setor = Setor.fromId(setorId);
                if (setor != null) {
                    Button setorBtn = new Button("Setor: " + setor.name());
                    setorBtn.setPrefWidth(160);
                    setorBtn.setStyle("-fx-padding: 4 20 4 40; -fx-font-size: 12;");
                    setorBtn.setOnAction(ev -> {
                        SessionStorage.setSetorPrincipal(setor.getId());
                        Navigation.reloadBaseLayout();
                    });
                    setorBtn.setVisible(false);
                    setorBtn.setManaged(false);
                    botoesSetores.add(setorBtn);
                    menuVBox.getChildren().add(setorBtn);
                }
            }
        }

        if (setorPrincipalId >= 0) {
            Setor setorPrincipal = Setor.fromId(setorPrincipalId);
            if (setorPrincipal == null) return;

            switch (setorPrincipal) {
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
    }

    private void carregarPagina(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();
            mainContent.getChildren().setAll(view);
            toggleMenu(); // Fecha o menu após navegação
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
        trans.setOnFinished(e -> {
            menuOverlay.setVisible(false);
        });
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

    private void toggleSetores() {
        for (Button setorBtn : botoesSetores) {
            boolean visible = !setorBtn.isVisible();
            setorBtn.setVisible(visible);
            setorBtn.setManaged(visible);
        }
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