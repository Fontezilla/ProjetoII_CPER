package com.example.cper_core.enums;

public enum EstadoAnomalia {
    APAGADA(0),
    DETECTADA(1),
    EM_ANALISE(2),
    EM_RESOLUCAO(3),
    RESOLVIDA(4),
    IGNORADA(5),
    CANCELADA(6);

    private final int id;

    EstadoAnomalia(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoAnomalia fromId(int id) {
        for (EstadoAnomalia estado : values()) {
            if (estado.id == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado inválido: " + id);
    }

    public static EstadoAnomalia fromName(String name) {
        for (EstadoAnomalia estado : values()) {
            if (estado.name().equalsIgnoreCase(name)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado inválido: " + name);
    }

    @Override
    public String toString() {
        return name();
    }
}
