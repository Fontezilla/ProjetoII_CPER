package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum EstadoContrato implements EnumWithId<EstadoContrato> {
    ATIVO(1),
    SUSPENSO(2),
    CANCELADO(3),
    TERMINADO(4),
    EM_ANALISE(5);

    private final int id;

    EstadoContrato(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(EstadoContrato estadoContrato) { return EnumWithId.getIdFromEnum(estadoContrato); }

    public static EstadoContrato fromId(int id) {
        return EnumWithId.fromId(EstadoContrato.class, id);
    }

    public static EstadoContrato fromName(String name) {
        return EnumWithId.fromName(EstadoContrato.class, name);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
