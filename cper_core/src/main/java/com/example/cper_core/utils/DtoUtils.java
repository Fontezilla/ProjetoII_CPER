package com.example.cper_core.utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class DtoUtils {

    public static void setIfPresent(String value, Consumer<String> setter) {
        if (value != null && !value.isBlank()) {
            setter.accept(value);
        }
    }

    public static <T> void setIfPresent(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}