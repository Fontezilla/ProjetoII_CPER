package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum EstadoFuncionario implements EnumWithId<EstadoFuncionario> {
    ATIVO(1),
    OCUPADO(2),
    AUSENTE(3),
    INATIVO(4);

    private final int id;

    EstadoFuncionario(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(EstadoFuncionario estadoFuncionario) { return EnumWithId.getIdFromEnum(estadoFuncionario); }

    public static EstadoFuncionario fromId(int id) {
        return EnumWithId.fromId(EstadoFuncionario.class, id);
    }

    public static EstadoFuncionario fromName(String name) {
        return EnumWithId.fromName(EstadoFuncionario.class, name);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
