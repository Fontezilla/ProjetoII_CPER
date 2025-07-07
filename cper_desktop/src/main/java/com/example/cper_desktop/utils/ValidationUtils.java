package com.example.cper_desktop.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.*;
import java.util.stream.Collectors;

public class ValidationUtils {

    public record ValidationMap(Map<String, Control> campos, Map<String, Label> labels) {}

    public static <T> Set<ConstraintViolation<T>> getViolations(Set<ConstraintViolation<T>> violacoes, T dto) {
        BeanWrapper wrapper = new BeanWrapperImpl(dto);
        return violacoes.stream()
                .filter(v -> {
                    Object valor = wrapper.getPropertyValue(v.getPropertyPath().toString());
                    return valor != null && !(valor instanceof String s && s.isBlank());
                })
                .collect(Collectors.toSet());
    }

    public static StringBuilder generateErrorMessage(Set<? extends ConstraintViolation<?>> violacoes) {
        StringBuilder erros = new StringBuilder("Erro ao salvar alterações:\n");
        for (var v : violacoes) {
            erros.append(" • ")
                    .append(v.getPropertyPath())
                    .append(": ")
                    .append(v.getMessage())
                    .append("\n");
        }
        return erros;
    }

    public static void applyInvalidStyles(Set<String> propriedadesInvalidas, ValidationMap map) {
        propriedadesInvalidas.forEach(prop -> {
            Control campo = map.campos().get(prop);
            if (campo != null) StyleUtils.markInvalid(campo);
        });
    }

    public static void applyErrorMessages(Set<? extends ConstraintViolation<?>> violacoes,
                                          ValidationMap map,
                                          boolean mostrarTodos) {
        map.labels().forEach((prop, label) -> {
            violacoes.stream()
                    .filter(v -> v.getPropertyPath().toString().equals(prop))
                    .findFirst()
                    .ifPresentOrElse(
                            v -> {
                                label.setText(v.getMessage());
                                label.setVisible(true);
                            },
                            () -> {
                                if (mostrarTodos) {
                                    label.setText("");
                                    label.setVisible(false);
                                }
                            }
                    );
        });
    }

    public static <T> boolean validateAndMark(T dto,
                                              Set<ConstraintViolation<T>> allViolations,
                                              ValidationMap map,
                                              boolean mostrarTodos) {
        Set<ConstraintViolation<T>> reais = getViolations(allViolations, dto);
        Set<String> props = reais.stream()
                .map(v -> v.getPropertyPath().toString())
                .collect(Collectors.toSet());

        applyInvalidStyles(props, map);
        if (map.labels() != null) applyErrorMessages(reais, map, mostrarTodos);

        return reais.isEmpty();
    }

    public static <T> boolean validateAndMark(T dto,
                                              Set<ConstraintViolation<T>> allViolations,
                                              Map<String, Control> camposInvalidaveis,
                                              Map<String, Label> labelsErro,
                                              boolean mostrarTodos) {
        Set<ConstraintViolation<T>> reais = getViolations(allViolations, dto);
        Set<String> props = reais.stream()
                .map(v -> v.getPropertyPath().toString())
                .collect(Collectors.toSet());

        ValidationMap map = new ValidationMap(camposInvalidaveis, labelsErro);
        applyInvalidStyles(props, map);
        applyErrorMessages(reais, map, true);

        return reais.isEmpty();
    }

    public record ValidationResult(boolean valido, Set<ConstraintViolation<?>> violacoes) {}

    public static <T> ValidationResult validar(T dto, Validator validator, Class<?>... grupos) {
        Set<ConstraintViolation<T>> violacoes = validator.validate(dto, grupos);
        Set<ConstraintViolation<?>> reais = getViolations(violacoes, dto).stream()
                .map(v -> (ConstraintViolation<?>) v)
                .collect(Collectors.toSet());

        return new ValidationResult(reais.isEmpty(), reais);
    }

}
