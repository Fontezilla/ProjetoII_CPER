package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum TipoEnergiaRenovavel implements EnumWithId<TipoEnergiaRenovavel> {
    SOLAR(1),
    EOLICA(2),
    HIDRICA(3),
    BIOMASSA(4),
    GEOTERMICA(5),
    MAREMOTRIZ(6),
    OCEANICA(7),
    HIDROGENIO_VERDE(8),
    OUTRA(99);

    private final int id;

    TipoEnergiaRenovavel(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(TipoEnergiaRenovavel tipoEnergiaRenovavel) { return EnumWithId.getIdFromEnum(tipoEnergiaRenovavel); }

    public static TipoEnergiaRenovavel fromId(int id) {
        return EnumWithId.fromId(TipoEnergiaRenovavel.class, id);
    }

    public static TipoEnergiaRenovavel fromName(String name) {
        return EnumWithId.fromName(TipoEnergiaRenovavel.class, name);
    }

    public static TipoEnergiaRenovavel fromLabel(String label) {
        return EnumWithId.fromLabel(TipoEnergiaRenovavel.class, label);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
