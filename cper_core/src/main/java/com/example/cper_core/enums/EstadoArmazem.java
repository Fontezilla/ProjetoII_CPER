package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum EstadoArmazem implements EnumWithId<EstadoArmazem> {
    ATIVO(1),
    EM_MANUTENCAO(2),
    ENCERRADO(3),
    EM_CONSTRUCAO(4),
    INATIVO(5);

    private final int id;

    EstadoArmazem(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(EstadoArmazem estadoArmazem) { return EnumWithId.getIdFromEnum(estadoArmazem); }

    public static EstadoArmazem fromId(int id) {
        return EnumWithId.fromId(EstadoArmazem.class, id);
    }

    public static EstadoArmazem fromName(String name) {
        return EnumWithId.fromName(EstadoArmazem.class, name);
    }

    public static EstadoArmazem fromLabel(String label) {
        return EnumWithId.fromLabel(EstadoArmazem.class, label);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
