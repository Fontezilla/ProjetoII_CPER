package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum EstadoFatura implements EnumWithId<EstadoFatura> {
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

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(EstadoFatura estadoFatura) { return EnumWithId.getIdFromEnum(estadoFatura); }

    public static EstadoFatura fromId(int id) {
        return EnumWithId.fromId(EstadoFatura.class, id);
    }

    public static EstadoFatura fromName(String name) {
        return EnumWithId.fromName(EstadoFatura.class, name);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
