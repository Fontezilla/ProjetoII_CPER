package com.example.cper_core.enums;

public enum EstadoCliente {
    APAGADO(0),
    ATIVO(1),
    INATIVO(2);

    private final int id;

    EstadoCliente(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoCliente fromId(int id) {
        for (EstadoCliente estado : values()) {
            if (estado.id == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado inválido: " + id);
    }

    public static EstadoCliente fromName(String name) {
        for (EstadoCliente estado : values()) {
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
