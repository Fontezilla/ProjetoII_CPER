package com.example.cper_core.utils;

import org.hibernate.Hibernate;

import java.lang.reflect.Field;

public class EntityUtils {

    public static void initializeAllRelations(Object entity) {
        if (entity == null) return;

        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(entity);
                if (value != null) {
                    Hibernate.initialize(value);
                }
            } catch (Exception ignored) {
            }
        }
    }
}
