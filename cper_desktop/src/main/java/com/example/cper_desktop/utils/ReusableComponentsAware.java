package com.example.cper_desktop.utils;

import com.example.cper_desktop.controllers.reusable_components.LoadingOverlayController;
import com.example.cper_desktop.controllers.reusable_components.ToastNotificationController;

public interface ReusableComponentsAware {
    void setLoadingOverlayController(LoadingOverlayController controller);
    void setToastNotificationController(ToastNotificationController controller);
}