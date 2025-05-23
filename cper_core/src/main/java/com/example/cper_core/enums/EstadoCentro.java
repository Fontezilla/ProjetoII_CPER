package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum EstadoCentro implements EnumWithId<EstadoCentro> {
    PLANEAMENTO(1),
    EM_CONSTRUCAO(2),
    OPERACIONAL(3),
    EM_MANUTENCAO(4),
    DESATIVADO(5),
    EM_EXPANSAO(6);

    private final int id;

    EstadoCentro(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(EstadoCentro estadoCentro) { return EnumWithId.getIdFromEnum(estadoCentro); }

    public static EstadoCentro fromId(int id) {
        return EnumWithId.fromId(EstadoCentro.class, id);
    }

    public static EstadoCentro fromName(String name) {
        return EnumWithId.fromName(EstadoCentro.class, name);
    }

    @Override
    public String toString() {
        return name();
    }
}
