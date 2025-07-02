package com.example.cper_desktop.utils;

import javafx.scene.Node;
import javafx.scene.Parent;

public class DebugUtils {

    public static void printNodeTree(Node node, String prefix) {
        if (node == null) return;

        System.out.printf("%s%s (id=%s, class=%s): layoutX=%.2f, layoutY=%.2f, width=%.2f, height=%.2f%n",
                prefix,
                node.getClass().getSimpleName(),
                node.getId(),
                node.getStyleClass(),
                node.getLayoutX(),
                node.getLayoutY(),
                node.getBoundsInParent().getWidth(),
                node.getBoundsInParent().getHeight()
        );

        if (node instanceof Parent parent) {
            for (Node child : parent.getChildrenUnmodifiable()) {
                printNodeTree(child, prefix + "  ");
            }
        }
    }
}
