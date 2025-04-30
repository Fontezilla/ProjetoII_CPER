package com.example.cper_core.enums;

public enum EstadoCentro {
    APAGADO(0),
    PLANEAMENTO(1),
    EM_CONSTRUCAO(2),
    OPERACIONAL(3),
    EM_MANUTENCAO(4),
    DESATIVADO(5),
    EM_EXPANSAO(6);

    private final int id;

    EstadoCentro(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoCentro fromId(int id) {
        for (EstadoCentro estado : values()) {
            if (estado.id == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoCentro inválido: " + id);
    }

    public static EstadoCentro fromName(String name) {
        for (EstadoCentro estado : values()) {
            if (estado.name().equalsIgnoreCase(name)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoCentro inválido: " + name);
    }

    @Override
    public String toString() {
        return name();
    }
}
