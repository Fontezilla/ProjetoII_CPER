package com.example.cper_webapi.utils;

public final class ControllerUtils {
    private ControllerUtils() {}

    public static void validateMatchingId(Integer pathId, Integer bodyId) {
        if (!pathId.equals(bodyId)) {
            throw new IllegalArgumentException("ID do corpo e da URL não coincidem.");
        }
    }
}
