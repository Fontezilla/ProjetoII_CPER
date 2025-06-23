package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum Prioridade implements EnumWithId<Prioridade> {
    MUITO_BAIXA(1),
    BAIXA(2),
    NORMAL(3),
    ALTA(4),
    URGENTE(5);

    private final int id;

    Prioridade(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(Prioridade prioridade) { return EnumWithId.getIdFromEnum(prioridade); }

    public static Prioridade fromId(int id) {
        return EnumWithId.fromId(Prioridade.class, id);
    }

    public static Prioridade fromName(String name) {
        return EnumWithId.fromName(Prioridade.class, name);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
