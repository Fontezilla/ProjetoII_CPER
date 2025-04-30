package com.example.cper_core.enums;

public enum EstadoContrato {
    APAGADO(0),
    ATIVO(1),
    SUSPENSO(2),
    CANCELADO(3),
    TERMINADO(4),
    EM_ANALISE(5);

    private final int id;

    EstadoContrato(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoContrato fromId(int id) {
        for (EstadoContrato estado : values()) {
            if (estado.id == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoContrato inválido: " + id);
    }

    public static EstadoContrato fromName(String name) {
        for (EstadoContrato estado : values()) {
            if (estado.name().equalsIgnoreCase(name)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoContrato inválido: " + name);
    }

    @Override
    public String toString() {
        return name();
    }
}
