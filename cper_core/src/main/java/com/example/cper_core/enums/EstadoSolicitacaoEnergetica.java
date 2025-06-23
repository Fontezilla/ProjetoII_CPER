package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum EstadoSolicitacaoEnergetica implements EnumWithId<EstadoSolicitacaoEnergetica> {
    RECEBIDA(1),
    EM_ANALISE(2),
    APROVADA(3),
    REJEITADA(4),
    EM_ANDAMENTO(5),
    CONCLUÍDA(6),
    CANCELADA(7);

    private final int id;

    EstadoSolicitacaoEnergetica(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(EstadoSolicitacaoEnergetica estadoSolicitacaoEnergetica) { return EnumWithId.getIdFromEnum(estadoSolicitacaoEnergetica); }

    public static EstadoSolicitacaoEnergetica fromId(int id) {
        return EnumWithId.fromId(EstadoSolicitacaoEnergetica.class, id);
    }

    public static EstadoSolicitacaoEnergetica fromName(String name) {
        return EnumWithId.fromName(EstadoSolicitacaoEnergetica.class, name);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
