package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum EstadoInspecao implements EnumWithId<EstadoInspecao> {
    PLANEADA(1),
    ANOMALIA_DETECTADA(2),
    ATRIBUIDA(3),
    EM_PROGRESSO(4),
    CONCLUIDA(5),
    CANCELADA(6);

    private final int id;

    EstadoInspecao(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(EstadoInspecao estadoInspecao) { return EnumWithId.getIdFromEnum(estadoInspecao); }

    public static EstadoInspecao fromId(int id) {
        return EnumWithId.fromId(EstadoInspecao.class, id);
    }

    public static EstadoInspecao fromName(String name) {
        return EnumWithId.fromName(EstadoInspecao.class, name);
    }

    @Override
    public String toString() {
        return name();
    }
}
