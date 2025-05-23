package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum EstadoTicket implements EnumWithId<EstadoTicket> {
    ABERTO(1),
    EM_ANDAMENTO(2),
    RESOLVIDO(3),
    FECHADO(4),
    CANCELADO(5);

    private final int id;

    EstadoTicket(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(EstadoTicket estadoTicket) { return EnumWithId.getIdFromEnum(estadoTicket); }

    public static EstadoTicket fromId(int id) {
        return EnumWithId.fromId(EstadoTicket.class, id);
    }

    public static EstadoTicket fromName(String name) {
        return EnumWithId.fromName(EstadoTicket.class, name);
    }

    @Override
    public String toString() {
        return name();
    }
}
