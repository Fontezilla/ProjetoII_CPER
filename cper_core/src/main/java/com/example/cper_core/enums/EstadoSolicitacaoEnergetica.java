package com.example.cper_core.enums;

public enum EstadoSolicitacaoEnergetica {
    RECEBIDA(0),
    EM_ANALISE(1),
    APROVADA(2),
    REJEITADA(3),
    EM_ANDAMENTO(4),
    CONCLUÍDA(5),
    CANCELADA(6);

    private final int id;

    EstadoSolicitacaoEnergetica(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoSolicitacaoEnergetica fromId(int id) {
        for (EstadoSolicitacaoEnergetica estado : values()) {
            if (estado.id == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado inválido: " + id);
    }

    public static EstadoSolicitacaoEnergetica fromName(String name) {
        for (EstadoSolicitacaoEnergetica estado : values()) {
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
