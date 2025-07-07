package com.example.cper_desktop.utils;

import com.example.cper_desktop.controllers.BaseLayoutController;
import com.example.cper_desktop.controllers.reusable_components.LoadingOverlayController;
import com.example.cper_desktop.controllers.reusable_components.ToastNotificationController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class InternalNavigationUtils {

    private static final Logger logger = Logger.getLogger(InternalNavigationUtils.class.getName());

    private static StackPane mainContent;
    private static LoadingOverlayController loadingOverlayController;
    private static ToastNotificationController toastNotificationController;
    private static BaseLayoutController baseController;

    private static final Deque<NavigationEntry> navigationStack = new ArrayDeque<>();
    private static boolean preventGoBack = false;

    private static SpringFXMLLoader springFXMLLoader;

    private static class NavigationEntry {
        final String fxmlPath;
        final Consumer<Object> controllerCallback;

        NavigationEntry(String fxmlPath, Consumer<Object> callback) {
            this.fxmlPath = fxmlPath;
            this.controllerCallback = callback;
        }
    }

    @Autowired
    public void setSpringFXMLLoader(SpringFXMLLoader loader) {
        InternalNavigationUtils.springFXMLLoader = loader;
    }

    public static void initialize(BaseLayoutController controller,
                                  StackPane mainPane,
                                  LoadingOverlayController loading,
                                  ToastNotificationController toast) {
        baseController = controller;
        mainContent = mainPane;
        loadingOverlayController = loading;
        toastNotificationController = toast;
    }

    public static void goTo(String fxmlPath) {
        goTo(fxmlPath, null);
    }

    public static void goTo(String fxmlPath, Consumer<Object> controllerCallback) {
        if (mainContent == null || springFXMLLoader == null) {
            logger.severe("❌ Navegação não inicializada corretamente.");
            return;
        }

        showLoading();

        Task<Parent> loadTask = new Task<>() {
            @Override
            protected Parent call() throws Exception {
                Parent[] rootOut = new Parent[1];
                Object controller = springFXMLLoader.loadWithController(fxmlPath, rootOut);

                if (controller instanceof ReusableComponentsAware reusable) {
                    reusable.setLoadingOverlayController(loadingOverlayController);
                    reusable.setToastNotificationController(toastNotificationController);
                    reusable.applyInitialStyle();
                }

                if (controllerCallback != null) {
                    Platform.runLater(() -> controllerCallback.accept(controller));
                }

                return rootOut[0];
            }
        };

        loadTask.setOnSucceeded(e -> {
            navigationStack.push(new NavigationEntry(fxmlPath, controllerCallback));
            mainContent.getChildren().setAll(loadTask.getValue());
            hideLoading();
        });

        loadTask.setOnFailed(e -> {
            hideLoading();
            logger.log(Level.SEVERE, "Erro ao carregar FXML: " + fxmlPath, loadTask.getException());
        });

        new Thread(loadTask).start();
    }

    public static <C, F> void navigateWith(String fxml, F filtro, BiConsumer<C, F> filtroApplier) {
        goTo(fxml, controller -> {
            try {
                @SuppressWarnings("unchecked")
                C castedController = (C) controller;
                filtroApplier.accept(castedController, filtro);
            } catch (ClassCastException e) {
                System.err.println("Erro ao aplicar filtro: " + e.getMessage());
            }
        });
    }

    public static void goBack() {
        if (preventGoBack) {
            System.out.println("⚠ Navegação para trás está desativada.");
            return;
        }

        if (navigationStack.isEmpty()) {
            System.out.println("⚠ Stack de navegação vazia.");
            return;
        }

        navigationStack.pop(); // remove página atual

        if (navigationStack.isEmpty()) {
            System.out.println("⚠ Nenhuma página para voltar.");
            return;
        }

        NavigationEntry anterior = navigationStack.peek();

        showLoading();

        Task<Parent> reloadTask = new Task<>() {
            @Override
            protected Parent call() throws Exception {
                Parent[] rootOut = new Parent[1];
                Object controller = springFXMLLoader.loadWithController(anterior.fxmlPath, rootOut);

                if (controller instanceof ReusableComponentsAware reusable) {
                    reusable.setLoadingOverlayController(loadingOverlayController);
                    reusable.setToastNotificationController(toastNotificationController);
                    reusable.applyInitialStyle();
                }

                if (anterior.controllerCallback != null) {
                    anterior.controllerCallback.accept(controller);
                }

                return rootOut[0];
            }
        };

        reloadTask.setOnSucceeded(e -> {
            mainContent.getChildren().setAll(reloadTask.getValue());
            hideLoading();
        });

        reloadTask.setOnFailed(e -> {
            hideLoading();
            logger.log(Level.SEVERE, "Erro ao recarregar FXML (goBack): " + anterior.fxmlPath, reloadTask.getException());
        });

        new Thread(reloadTask).start();
    }

    public static void clearHistory() {
        navigationStack.clear();
    }

    public static void setPreventGoBack(boolean prevent) {
        preventGoBack = prevent;
    }

    public static boolean isPreventGoBack() {
        return preventGoBack;
    }

    private static void showLoading() {
        if (loadingOverlayController != null) {
            Platform.runLater(loadingOverlayController::show);
        }
    }

    private static void hideLoading() {
        if (loadingOverlayController != null) {
            Platform.runLater(loadingOverlayController::hide);
        }
    }
}