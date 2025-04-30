package com.example.cper_core.enums;

public enum Prioridade {
    MUITO_BAIXA(1),
    BAIXA(2),
    NORMAL(3),
    ALTA(4),
    URGENTE(5);

    private final int id;

    Prioridade(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Prioridade fromId(int id) {
        for (Prioridade prioridade : values()) {
            if (prioridade.id == id) {
                return prioridade;
            }
        }
        throw new IllegalArgumentException("Prioridade inválida: " + id);
    }

    public static Prioridade fromName(String name) {
        for (Prioridade prioridade : values()) {
            if (prioridade.name().equalsIgnoreCase(name)) {
                return prioridade;
            }
        }
        throw new IllegalArgumentException("Prioridade inválida: " + name);
    }

    @Override
    public String toString() {
        return name();
    }
}
