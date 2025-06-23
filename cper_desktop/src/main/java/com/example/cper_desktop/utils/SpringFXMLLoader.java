package com.example.cper_desktop.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.net.URL;

public class SpringFXMLLoader {

    private final ApplicationContext context;

    public SpringFXMLLoader(ApplicationContext context) {
        this.context = context;
    }

    public Parent load(URL url) throws IOException {
        FXMLLoader loader = new FXMLLoader(url);
        loader.setControllerFactory(context::getBean);
        return loader.load();
    }

    public Parent load(String resourcePath) throws IOException {
        URL url = getClass().getResource(resourcePath);
        if (url == null) throw new IOException("FXML não encontrado: " + resourcePath);
        return load(url);
    }
}