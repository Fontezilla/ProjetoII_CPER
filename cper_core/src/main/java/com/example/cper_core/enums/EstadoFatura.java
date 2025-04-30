package com.example.cper_core.enums;

public enum EstadoFatura {
    APAGADA(0),
    CRIADA(1),
    EM_ANALISE(2),
    ENVIADA(3),
    PAGA(4),
    VENCIDA(5),
    CANCELADA(6);

    private final int id;

    EstadoFatura(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoFatura fromId(int id) {
        for (EstadoFatura estado : values()) {
            if (estado.id == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoFatura inválido: " + id);
    }

    public static EstadoFatura fromName(String name) {
        for (EstadoFatura estado : values()) {
            if (estado.name().equalsIgnoreCase(name)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoFatura inválido: " + name);
    }

    @Override
    public String toString() {
        return name();
    }
}
