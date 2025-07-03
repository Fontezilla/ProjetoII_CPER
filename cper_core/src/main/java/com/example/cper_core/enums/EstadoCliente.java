package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum EstadoCliente implements EnumWithId<EstadoCliente> {
    ATIVO(1),
    INATIVO(2);

    private final int id;

    EstadoCliente(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(EstadoCliente estadoCliente) { return EnumWithId.getIdFromEnum(estadoCliente); }

    public static EstadoCliente fromId(int id) {
        return EnumWithId.fromId(EstadoCliente.class, id);
    }

    public static EstadoCliente fromName(String name) {
        return EnumWithId.fromName(EstadoCliente.class, name);
    }

    public static EstadoCliente fromLabel(String label) {
        return EnumWithId.fromLabel(EstadoCliente.class, label);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
