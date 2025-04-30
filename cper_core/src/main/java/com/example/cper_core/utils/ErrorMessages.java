package com.example.cper_core.utils;

import java.util.ResourceBundle;

public class ErrorMessages {
    private static final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public static String getMessage(String key) {
        return messages.getString(key);
    }
}
