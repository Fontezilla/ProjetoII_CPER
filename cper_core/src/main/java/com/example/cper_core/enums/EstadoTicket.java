package com.example.cper_core.enums;

public enum EstadoTicket {
    APAGADO(0),
    ABERTO(1),
    EM_ANDAMENTO(2),
    RESOLVIDO(3),
    FECHADO(4),
    CANCELADO(5);

    private final int id;

    EstadoTicket(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoTicket fromId(int id) {
        for (EstadoTicket estado : values()) {
            if (estado.id == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado inválido: " + id);
    }

    public static EstadoTicket fromName(String name) {
        for (EstadoTicket estado : values()) {
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
