package com.example.cper_core.enums;

public enum EstadoPedidoMaterial {
    APAGADO(0),
    PENDENTE(1),
    EM_PROCESSO(2),
    ENVIADO(3);

    private final int id;

    EstadoPedidoMaterial(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoPedidoMaterial fromId(int id) {
        for (EstadoPedidoMaterial estado : values()) {
            if (estado.id == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoPMaterial inválido: " + id);
    }

    public static EstadoPedidoMaterial fromName(String name) {
        for (EstadoPedidoMaterial estado : values()) {
            if (estado.name().equalsIgnoreCase(name)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoPMaterial inválido: " + name);
    }

    @Override
    public String toString() {
        return name();
    }
}
