package com.example.cper_core.enums;

public enum EstadoInspecao {
    APAGADA(0),
    PLANEADA(1),
    ANOMALIA_DETECTADA(2),
    ATRIBUIDA(3),
    EM_PROGRESSO(4),
    CONCLUIDA(5),
    CANCELADA(6);

    private final int id;

    EstadoInspecao(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoInspecao fromId(int id) {
        for (EstadoInspecao estado : values()) {
            if (estado.id == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoInspecao inválido: " + id);
    }

    public static EstadoInspecao fromName(String name) {
        for (EstadoInspecao estado : values()) {
            if (estado.name().equalsIgnoreCase(name)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoInspecao inválido: " + name);
    }

    @Override
    public String toString() {
        return name();
    }
}
