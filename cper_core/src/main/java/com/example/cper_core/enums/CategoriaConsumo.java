package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum CategoriaConsumo implements EnumWithId<CategoriaConsumo> {
    RESIDENCIAL(1),
    COMERCIAL(2),
    INDUSTRIAL(3),
    AGRICOLA(4),
    PUBLICO(5),
    OUTRO(6);

    private final int id;

    CategoriaConsumo(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(TipoTicket tipoTicket) { return EnumWithId.getIdFromEnum(tipoTicket); }

    public static CategoriaConsumo fromId(int id) {
        return EnumWithId.fromId(CategoriaConsumo.class, id);
    }

    public static CategoriaConsumo fromName(String name) {
        return EnumWithId.fromName(CategoriaConsumo.class, name);
    }

    public static CategoriaConsumo fromLabel(String label) {
        return EnumWithId.fromLabel(CategoriaConsumo.class, label);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
