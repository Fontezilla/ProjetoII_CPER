package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum Setor implements EnumWithId<Setor> {
    SEM_SETOR(-1),
    ADMINISTRATIVO(0),
    COMERCIAL(1),
    FINANCEIRO(2),
    PRODUCAO_CENTRAL(3),
    PRODUCAO_GERADOR(4),
    INSPECAO(5),
    PLANEAMENTO(6),
    MANUTENCAO(7),
    ARMAZEM(8),
    CONSTRUCAO(9),
    RH(10);

    private final int id;

    Setor(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(Setor setor) { return EnumWithId.getIdFromEnum(setor); }

    public static Setor fromId(int id) {
        return EnumWithId.fromId(Setor.class, id);
    }

    public static Setor fromName(String name) {
        return EnumWithId.fromName(Setor.class, name);
    }

    @Override
    public String toString() {
        return name();
    }
}
