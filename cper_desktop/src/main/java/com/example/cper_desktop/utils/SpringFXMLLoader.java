package com.example.cper_desktop.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class SpringFXMLLoader {

    private final ApplicationContext context;

    @Autowired
    public SpringFXMLLoader(ApplicationContext context) {
        this.context = context;
    }

    public Parent load(String resourcePath) throws IOException {
        URL url = getClass().getResource(resourcePath);
        if (url == null) throw new IOException("FXML não encontrado: " + resourcePath);
        return load(url);
    }

    public Parent load(URL url) throws IOException {
        FXMLLoader loader = new FXMLLoader(url);
        loader.setControllerFactory(context::getBean);
        return loader.load();
    }

    public <T> T loadWithController(String resourcePath, Parent[] rootOut) throws IOException {
        URL url = getClass().getResource(resourcePath);
        if (url == null) throw new IOException("FXML não encontrado: " + resourcePath);

        FXMLLoader loader = new FXMLLoader(url);
        loader.setControllerFactory(context::getBean);
        Parent root = loader.load();

        if (rootOut != null && rootOut.length > 0) {
            rootOut[0] = root;
        }

        return loader.getController();
    }
}