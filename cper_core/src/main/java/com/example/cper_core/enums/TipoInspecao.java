package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum TipoInspecao implements EnumWithId<TipoInspecao> {
    ROTINA(0),
    INSPECAO_EQUIPAMENTOS(1),
    INSPECAO_SEGURANCA_OPERACIONAL(2),
    INSPECAO_ELETRICA(3),
    INSPECAO_ESTRUTURAS_INFRAESTRUTURAS(4),
    INSPECAO_AMBIENTAL(5),
    INSPECAO_MANUTENCAO_PREVENTIVA(6),
    INSPECAO_PRODUCAO_EFICIENCIA(7),
    INSPECAO_REGULAMENTAR(8);

    private final int id;

    TipoInspecao(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(TipoInspecao tipoInspecao) { return EnumWithId.getIdFromEnum(tipoInspecao); }

    public static TipoInspecao fromId(int id) {
        return EnumWithId.fromId(TipoInspecao.class, id);
    }

    public static TipoInspecao fromName(String name) {
        return EnumWithId.fromName(TipoInspecao.class, name);
    }

    public static TipoInspecao fromLabel(String label) {
        return EnumWithId.fromLabel(TipoInspecao.class, label);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}