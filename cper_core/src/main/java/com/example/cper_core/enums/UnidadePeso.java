package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum UnidadePeso implements EnumWithId<UnidadePeso> {
    GRAMA(1),
    QUILOGRAMA(2),
    TONELADA(3);

    private final int id;

    UnidadePeso(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(UnidadePeso unidadePeso) { return EnumWithId.getIdFromEnum(unidadePeso); }

    public static UnidadePeso fromId(int id) {
        return EnumWithId.fromId(UnidadePeso.class, id);
    }

    public static UnidadePeso fromName(String name) {
        return EnumWithId.fromName(UnidadePeso.class, name);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}