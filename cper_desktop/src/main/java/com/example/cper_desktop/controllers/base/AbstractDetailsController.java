package com.example.cper_desktop.controllers.base;

import com.example.cper_desktop.controllers.reusable_components.LoadingOverlayController;
import com.example.cper_desktop.controllers.reusable_components.ToastNotificationController;
import com.example.cper_desktop.utils.FormatterUtils;
import com.example.cper_desktop.utils.ReusableComponentsAware;
import com.example.cper_desktop.utils.StyleUtils;
import jakarta.validation.Validator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;

public abstract class AbstractDetailsController<T> implements ReusableComponentsAware {

    protected LoadingOverlayController loadingOverlayController;
    protected ToastNotificationController toastNotificationController;
    protected final Validator validator;
    protected boolean editMode = false;
    protected boolean createMode = false;
    protected Integer id;

    protected AbstractDetailsController(Validator validator) {
        this.validator = validator;
    }

    public void setCreateMode(boolean createMode) {
        this.createMode = createMode;
        this.editMode = createMode;
    }
    @Override
    public void setLoadingOverlayController(LoadingOverlayController controller) {
        this.loadingOverlayController = controller;
    }

    @Override
    public void setToastNotificationController(ToastNotificationController controller) {
        this.toastNotificationController = controller;
    }

    // === Ciclo de vida base ===

    @FXML
    public final void initialize() {
        Platform.runLater(() -> {
            if (loadingOverlayController != null) loadingOverlayController.show();

            if (!createMode && id != null) {
                loadData();
            }
            postInitialize();

            if (loadingOverlayController != null) loadingOverlayController.hide();
        });
    }

    protected void postInitialize() {
        setupFields();
        setupButtons();
        applyInitialStyle();
        applyPermissionRules();
    }

    // === Métodos obrigatórios ===

    protected abstract void loadData();
    protected abstract T updateData();

    // === Hooks opcionais ===

    protected void setupFields() {}
    protected void setupButtons() {}
    protected void applyPermissionRules() {}

    // === Estilo e validação ===

    protected void toggleEditMode(boolean edit) {
        this.editMode = edit;
    }

    protected void applyFieldStyle(TextField field, boolean editable) {
        field.setEditable(editable);
        StyleUtils.applyEditStyle(field, editable);
    }

    protected void applyFieldStyles(boolean editable, TextField... fields) {
        for (TextField field : fields) {
            applyFieldStyle(field, editable);
        }
    }

    protected <E> void applyComboBoxStyle(ComboBox<E> comboBox, boolean editable) {
        StyleUtils.applyEditStyle(comboBox, editable);
        comboBox.setDisable(false);
    }

    protected void applyDatePickerStyle(DatePicker datePicker, boolean editable) {
        StyleUtils.applyEditStyle(datePicker, editable);
        datePicker.setDisable(false);
    }

    protected void markInvalidField(TextField field) {
        StyleUtils.markInvalid(field);
    }

    protected void clearInvalidField(TextField field) {
        StyleUtils.clearInvalid(field, editMode);
    }

    protected void applyPermissions(Map<Control, Boolean> permissionMap, boolean edit) {
        for (var entry : permissionMap.entrySet()) {
            Control control = entry.getKey();
            boolean canEdit = edit && entry.getValue();
            StyleUtils.applyEditStyle(control, canEdit);
        }
    }

    // === Formatadores comuns ===

    protected String value(Object obj) {
        return FormatterUtils.value(obj);
    }

    protected String date(OffsetDateTime data) {
        return FormatterUtils.date(data);
    }

    protected String currency(BigDecimal valor) {
        return FormatterUtils.currency(valor);
    }

    protected String decimal(BigDecimal valor, String sufixo) {
        return FormatterUtils.decimal(valor, sufixo);
    }

    // === Toasts ===

    protected void showSuccessToast(String titulo, String descricao) {
        if (toastNotificationController != null)
            toastNotificationController.showSuccess(titulo, descricao);
    }

    protected void showErrorToast(String titulo, String descricao) {
        if (toastNotificationController != null)
            toastNotificationController.showError(titulo, descricao);
    }
}