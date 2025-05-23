package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum EstadoAnomalia implements EnumWithId<EstadoAnomalia> {
    DETECTADA(1),
    EM_ANALISE(2),
    EM_RESOLUCAO(3),
    RESOLVIDA(4),
    IGNORADA(5),
    CANCELADA(6);

    private final int id;

    EstadoAnomalia(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(EstadoAnomalia estadoAnomalia) { return EnumWithId.getIdFromEnum(estadoAnomalia); }

    public static EstadoAnomalia fromId(int id) {
        return EnumWithId.fromId(EstadoAnomalia.class, id);
    }

    public static EstadoAnomalia fromName(String name) {
        return EnumWithId.fromName(EstadoAnomalia.class, name);
    }

    @Override
    public String toString() {
        return name();
    }
}
