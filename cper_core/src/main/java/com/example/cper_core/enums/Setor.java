package com.example.cper_core.enums;

import java.util.Arrays;

public enum Setor {
    TEMPORARIO(0),
    COMERCIAL(1),
    FINANCEIRO(2),
    PRODUCAO_CENTRAL(3),
    PRODUCAO_GERADOR(4),
    INSPECAO(5),
    PLANEAMENTO(6),
    MANUTENCAO(7),
    ARMAZEM(8),
    CONSTRUCAO(9),
    RH(10),
    ADMINISTRATIVO(11);

    private final int id;

    Setor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Setor fromId(int id) {
        return Arrays.stream(values())
                .filter(setor -> setor.id == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Setor inválido: " + id));
    }

    public static Setor fromName(String name) {
        return Arrays.stream(values())
                .filter(setor -> setor.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Setor inválido: " + name));
    }

    @Override
    public String toString() {
        return name();
    }
}
