package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum EstadoAvaria implements EnumWithId<EstadoAvaria> {
    DETECTADA(1),
    ATRIBUIDA(2),
    EM_RESOLUCAO(3),
    RESOLVIDA(4),
    IGNORADA(5),
    CANCELADA(6);

    private final int id;

    EstadoAvaria(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(EstadoAvaria estadoAvaria) { return EnumWithId.getIdFromEnum(estadoAvaria); }

    public static EstadoAvaria fromId(int id) {
        return EnumWithId.fromId(EstadoAvaria.class, id);
    }

    public static EstadoAvaria fromName(String name) {
        return EnumWithId.fromName(EstadoAvaria.class, name);
    }

    @Override
    public String toString() {
        return name();
    }
}
