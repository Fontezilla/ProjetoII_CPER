package com.example.cper_core.enums;

public enum EstadoAvaria {

    APAGADA(0),
    DETECTADA(1),
    ATRIBUIDA(2),
    EM_RESOLUCAO(3),
    RESOLVIDA(4),
    IGNORADA(5),
    CANCELADA(6);

    private final int id;

    EstadoAvaria(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoAvaria fromId(int id) {
        for (EstadoAvaria estado : values()) {
            if (estado.id == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoAnomalia inválido: " + id);
    }

    public static EstadoAvaria fromName(String name) {
        for (EstadoAvaria estado : values()) {
            if (estado.name().equalsIgnoreCase(name)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoAnomalia inválido: " + name);
    }

    @Override
    public String toString() {
        return name();
    }
}
