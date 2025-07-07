package com.example.cper_desktop.utils;

import com.example.cper_core.enums.Setor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PermissionUtils {

    public static Set<Integer> getAllSetores() {
        Set<Integer> setores = new HashSet<>();
        Optional.ofNullable(SessionStorage.getSetoresAssociados()).ifPresent(setores::addAll);
        Optional.ofNullable(SessionStorage.getSetorPrincipal()).filter(id -> id != 0).ifPresent(setores::add);
        return setores;
    }

    public static boolean contains(Setor setor, Set<Integer> setores) {
        return setores != null && setores.contains(setor.getId());
    }

    public static boolean some(Collection<Setor> setoresNecessarios, Set<Integer> setoresUtilizador) {
        return setoresNecessarios.stream().anyMatch(s -> setoresUtilizador.contains(s.getId()));
    }

    public static boolean all(Collection<Setor> setoresNecessarios, Set<Integer> setoresUtilizador) {
        return setoresNecessarios.stream().allMatch(s -> setoresUtilizador.contains(s.getId()));
    }

    public static boolean none(Collection<Setor> setoresBloqueados, Set<Integer> setoresUtilizador) {
        return setoresBloqueados.stream().noneMatch(s -> setoresUtilizador.contains(s.getId()));
    }
}
