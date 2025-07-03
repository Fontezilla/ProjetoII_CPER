package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum UnidadeVolume implements EnumWithId<UnidadeVolume> {
    LITRO(1),
    METRO_CUBICO(2);

    private final int id;

    UnidadeVolume(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(UnidadeVolume unidadeVolume) { return EnumWithId.getIdFromEnum(unidadeVolume); }

    public static UnidadeVolume fromId(int id) {
        return EnumWithId.fromId(UnidadeVolume.class, id);
    }

    public static UnidadeVolume fromName(String name) {
        return EnumWithId.fromName(UnidadeVolume.class, name);
    }

    public static UnidadeVolume fromLabel(String label) {
        return EnumWithId.fromLabel(UnidadeVolume.class, label);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}