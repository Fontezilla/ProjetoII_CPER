package com.example.cper_core.enums;

public enum EstadoPedidoGeracao {
    APAGADO(0),
    CRIADO(1),
    EM_ANALISE(2),
    APROVADO(3),
    EM_EXECUCAO(4),
    CONCLUIDO(5),
    CANCELADO(6);

    private final int id;

    EstadoPedidoGeracao(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoPedidoGeracao fromId(int id) {
        for (EstadoPedidoGeracao estado : values()) {
            if (estado.id == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoPedidoGeracao inválido: " + id);
    }

    public static EstadoPedidoGeracao fromName(String name) {
        for (EstadoPedidoGeracao estado : values()) {
            if (estado.name().equalsIgnoreCase(name)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoPedidoGeracao inválido: " + name);
    }

    @Override
    public String toString() {
        return name();
    }
}
