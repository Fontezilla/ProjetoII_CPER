package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum EstadoContrato implements EnumWithId<EstadoContrato> {
    EM_ANALISE(1),
    ATIVO(2),
    SUSPENSO(3),
    CANCELADO(4),
    TERMINADO(5);


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

    public static EstadoContrato fromLabel(String label) {
        return EnumWithId.fromLabel(EstadoContrato.class, label);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
