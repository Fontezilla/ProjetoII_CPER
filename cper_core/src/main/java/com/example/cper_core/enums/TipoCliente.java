package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum TipoCliente implements EnumWithId<TipoCliente> {
    RESIDENCIAL(1),
    COMERCIAL(2),
    INDUSTRIAL(3),
    ENTIDADE_PUBLICA(4),
    AGRICOLA(5),
    OUTRO(6);

    private final int id;

    TipoCliente(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(TipoCliente tipoCliente) { return EnumWithId.getIdFromEnum(tipoCliente); }

    public static TipoCliente fromId(int id) {
        return EnumWithId.fromId(TipoCliente.class, id);
    }

    public static TipoCliente fromName(String name) {
        return EnumWithId.fromName(TipoCliente.class, name);
    }

    @Override
    public String toString() {
        return name();
    }
}
