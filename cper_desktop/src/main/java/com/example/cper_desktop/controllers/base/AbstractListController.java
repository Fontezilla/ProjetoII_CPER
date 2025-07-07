package com.example.cper_desktop.controllers.base;

import com.example.cper_desktop.controllers.BaseLayoutController;
import com.example.cper_desktop.controllers.reusable_components.PaginationBarController;
import com.example.cper_desktop.controllers.reusable_components.interfaces.PaginatableListController;
import com.example.cper_desktop.utils.InternalNavigationUtils;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

public abstract class AbstractListController<T, F> implements PaginatableListController {

    @FXML protected StackPane basePane, noResultsBox, filtersMenu, sortMenu, filtersBtn, sortBtn, paginationBar, backBtn;
    @FXML protected ScrollPane scrollPane;
    @FXML protected Pane filtersHeaderBar;
    @FXML protected VBox list;

    protected final Duration animationDuration = Duration.millis(300);
    protected boolean filtersOpen = false;
    protected boolean sortOpen = false;
    protected boolean isAnimating = false;

    protected String sortField;
    protected SortDirection sortDirection = SortDirection.ASC;
    protected F filtroAtual;
    protected F filtroInicial;
    protected PaginationBarController paginationBarController;
    protected int currentPage = 0;
    protected int totalPages = 1;
    protected boolean filtroPendente = false;
    protected boolean initialized = false;

    public enum SortDirection {
        ASC, DESC
    }

    public void initialize() {
        filtersBtn.setOnMouseClicked(e -> toggleFilters());
        sortBtn.setOnMouseClicked(e -> toggleSort());
        backBtn.setOnMouseClicked(e -> onBackClick());

        manageNoResults(false, false);

        applyHoverEffects();
        initPaginationBar();

        filtroAtual = filtroInicial != null ? filtroInicial : createEmptyFilter();

        if (!filtroPendente) {
            carregarLista(filtroAtual);
        }

        filtroPendente = false;
        initialized = true;
    }

    protected void initPaginationBar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/components/PaginationBar.fxml"));
            Node paginationNode = loader.load();
            paginationBarController = loader.getController();
            paginationBarController.bindTo(this);
            paginationBar.getChildren().setAll(paginationNode);
        } catch (IOException e) {
            System.err.println("Erro ao carregar componente de paginação:");
            e.printStackTrace();
        }
    }

    protected void toggleFilters() {
        if (isAnimating) return;
        if (sortOpen) toggleSort();

        double offset = filtersMenu.getBoundsInParent().getHeight() - filtersHeaderBar.getHeight();
        isAnimating = true;
        slideToggle(filtersMenu, filtersOpen, offset, () -> {
            filtersOpen = !filtersOpen;
            isAnimating = false;
        });
    }

    protected void toggleSort() {
        if (isAnimating) return;
        if (filtersOpen) toggleFilters();

        double offset = sortMenu.getBoundsInParent().getHeight() - filtersHeaderBar.getHeight();
        isAnimating = true;
        slideToggle(sortMenu, sortOpen, offset, () -> {
            sortOpen = !sortOpen;
            isAnimating = false;
        });
    }

    protected void slideToggle(Node node, boolean isOpen, double offset, Runnable onFinish) {
        TranslateTransition transition = new TranslateTransition(animationDuration, node);
        transition.setFromY(node.getTranslateY());
        transition.setToY(isOpen ? node.getTranslateY() - offset : node.getTranslateY() + offset);
        transition.setOnFinished(e -> onFinish.run());
        transition.play();
    }

    protected void sortBy(String field, SortDirection direction) {
        this.sortField = field;
        this.sortDirection = direction;
        this.currentPage = 0;
        toggleSort();
        carregarLista(filtroAtual);
    }

    protected void clearList() {
        list.getChildren().clear();
    }

    protected void renderList(List<T> items) {
        clearList();

        boolean hasItems = items != null && !items.isEmpty();

        if (hasItems) {
            for (T item : items) {
                Node node = createListItem(item);
                if (node != null) list.getChildren().add(node);
            }
        }

        manageNoResults(!hasItems, hasItems);
    }

    private void manageNoResults (boolean noResults, boolean noList) {
        noResultsBox.setVisible(noResults);
        noResultsBox.setManaged(noResults);
        list.setVisible(noList);
        list.setManaged(noList);
    }

    protected void scrollToTop() {
        if (scrollPane != null) scrollPane.setVvalue(0);
    }

    protected void onBackClick() {
        InternalNavigationUtils.goBack();
    }

    protected BaseLayoutController getBaseController() {
        var scene = basePane.getScene();
        if (scene == null) return null;
        return (BaseLayoutController) scene.getRoot().getProperties().get("baseController");
    }

    public void setFiltroInicial(F filtroInicial) {
        this.filtroInicial = filtroInicial;

        if (initialized) {
            this.filtroAtual = filtroInicial;
            this.currentPage = 0;
            carregarLista(filtroAtual);
        } else {
            this.filtroPendente = true;
        }
    }

    protected abstract F createEmptyFilter();
    protected abstract void applyHoverEffects();
    protected abstract void carregarLista(F filtro);
    protected abstract Node createListItem(T dto);

    protected PageRequest buildPageRequest() {
        Sort.Direction springDirection = sortDirection == SortDirection.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
        return PageRequest.of(currentPage, 20, Sort.by(springDirection, sortField));
    }

    @Override
    public void onPageChanged(int newPage) {
        this.currentPage = newPage;
        carregarLista(filtroAtual);
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }
}