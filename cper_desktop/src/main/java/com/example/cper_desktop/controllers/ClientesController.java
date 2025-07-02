package com.example.cper_desktop.controllers;

import com.example.cper_core.dtos.cliente.ClienteDetailsDto;
import com.example.cper_core.dtos.cliente.ClienteFiltroDto;
import com.example.cper_core.services.ClienteService;
import com.example.cper_desktop.controllers.base.AbstractListController;
import com.example.cper_desktop.controllers.reusable_components.ListItemController;
import com.example.cper_desktop.utils.StyleUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.example.cper_desktop.utils.StyleUtils.*;

@Component
public class ClientesController extends AbstractListController<ClienteDetailsDto, ClienteFiltroDto> {

    @FXML private TextField nomeField, telefoneField, emailField, nifField;
    @FXML private Button searchBtn;
    @FXML private Button nameAscBtn, nameDescBtn, emailAscBtn, emailDescBtn, phoneAscBtn, phoneDescBtn, nifAscBtn, nifDescBtn;

    @Autowired private ClienteService clienteService;

    @FXML
    @Override
    public void initialize() {
        this.sortField = "nome";
        super.initialize();

        searchBtn.setOnAction(e -> applyFilters());

        nameAscBtn.setOnAction(e -> sortBy("nome", SortDirection.ASC));
        nameDescBtn.setOnAction(e -> sortBy("nome", SortDirection.DESC));
        emailAscBtn.setOnAction(e -> sortBy("email", SortDirection.ASC));
        emailDescBtn.setOnAction(e -> sortBy("email", SortDirection.DESC));
        phoneAscBtn.setOnAction(e -> sortBy("telefone", SortDirection.ASC));
        phoneDescBtn.setOnAction(e -> sortBy("telefone", SortDirection.DESC));
        nifAscBtn.setOnAction(e -> sortBy("nif", SortDirection.ASC));
        nifDescBtn.setOnAction(e -> sortBy("nif", SortDirection.DESC));
    }

    @Override
    protected ClienteFiltroDto createEmptyFilter() {
        ClienteFiltroDto filtro = new ClienteFiltroDto();
        filtro.setIsDeleted(false);
        return filtro;
    }


    private void applyFilters() {
        ClienteFiltroDto filtro = this.filtroAtual != null ? this.filtroAtual : new ClienteFiltroDto();

        if (nomeField != null && nomeField.getText() != null && !nomeField.getText().isBlank())
            filtro.setNome(nomeField.getText().trim());
        else
            filtro.setNome(null);

        if (telefoneField != null && telefoneField.getText() != null && !telefoneField.getText().isBlank())
            filtro.setTelefone(telefoneField.getText().trim());
        else
            filtro.setTelefone(null);

        if (emailField != null && emailField.getText() != null && !emailField.getText().isBlank())
            filtro.setEmail(emailField.getText().trim());
        else
            filtro.setEmail(null);

        if (nifField != null && nifField.getText() != null && !nifField.getText().isBlank())
            filtro.setNif(nifField.getText().trim());
        else
            filtro.setNif(null);

        this.filtroAtual = filtro;
        this.currentPage = 0;
        toggleFilters();
        clearFilters();
        carregarLista(filtroAtual);
    }

    private void clearFilters() {
        nomeField.clear();
        telefoneField.clear();
        emailField.clear();
        nifField.clear();
    }

    @Override
    protected void carregarLista(ClienteFiltroDto filtro) {
        try {
            Sort.Direction springDirection = sortDirection == SortDirection.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
            PageRequest pageRequest = PageRequest.of(currentPage, 20, Sort.by(springDirection, sortField));
            Page<ClienteDetailsDto> page = clienteService.listFiltered(pageRequest, filtro);

            totalPages = page.getTotalPages();
            paginationBarController.updatePagination();

            renderList(page.getContent());
        } catch (Exception e) {
            System.err.println("Erro ao carregar clientes:");
            e.printStackTrace();
        }
    }

    @Override
    protected Node createListItem(ClienteDetailsDto cliente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/components/ListItem.fxml"));
            Node itemNode = loader.load();
            ListItemController controller = loader.getController();

            controller.setDataComLarguras(
                    List.of(cliente.getNif(), cliente.getNome(), cliente.getTelefone(), cliente.getEmail()),
                    List.of(153.33, 306.67, 153.33, 306.67),
                    clicked -> openClientePage(cliente.getId())
            );

            return itemNode;
        } catch (IOException e) {
            System.err.println("Erro ao carregar item da lista do cliente ID: " + cliente.getId());
            e.printStackTrace();
            return null;
        }
    }

    private void openClientePage(Integer clienteId) {
        var scene = basePane.getScene();
        if (scene == null) return;

        var base = scene.getRoot().getProperties().get("baseController");
        if (base instanceof BaseLayoutController baseLayout) {
            baseLayout.navigateTo("/views/cliente.fxml", controller -> {
                if (controller instanceof ClienteController clienteController) {
                    clienteController.setClienteId(clienteId);
                }
            }, true);
        } else {
            System.err.println("BaseLayoutController não encontrado na scene.");
        }
    }

    @Override
    protected void applyHoverEffects() {
        applyStackPaneHoverEffect(filtersBtn);
        applyStackPaneHoverEffect(sortBtn);
        applyStackPaneHoverEffect(backBtn);

        applySubtleOverlayHoverEffect(searchBtn);

        List.of(nameAscBtn, nameDescBtn, emailAscBtn, emailDescBtn,
                        phoneAscBtn, phoneDescBtn, nifAscBtn, nifDescBtn)
                .forEach(StyleUtils::applyButtonHoverEffect);
    }
}