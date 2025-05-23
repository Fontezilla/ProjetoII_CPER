package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum TipoTicket implements EnumWithId<TipoTicket> {
    INCIDENTE(1),
    PEDIDO(2),
    PROBLEMA(3),
    RECLAMACAO(4),
    OUTRO(5);

    private final int id;

    TipoTicket(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(CategoriaConsumo categoriaConsumo) { return EnumWithId.getIdFromEnum(categoriaConsumo); }

    public static TipoTicket fromId(int id) { return EnumWithId.fromId(TipoTicket.class, id); }

    public static TipoTicket fromName(String name) {
        return EnumWithId.fromName(TipoTicket.class, name);
    }

    @Override
    public String toString() {
        return name();
    }
}