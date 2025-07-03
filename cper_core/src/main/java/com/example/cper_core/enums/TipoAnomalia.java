package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum TipoAnomalia implements EnumWithId<TipoAnomalia> {
    FALHA_GERACAO(1),
    FALHA_DISTRIBUICAO(2),
    SOBRECARGA_SISTEMA(3),
    FALHA_EQUIPAMENTO(4),
    FALHA_COMUNICACAO(5),
    DANO_ESTRUTURAL(6),
    DETERIORACAO_COMPONENTE(7),
    DESEQUILIBRIO_REDE(8),
    CURTO_CIRCUITO(9),
    PERDA_EFICIENCIA(10),
    VAZAMENTO(11),
    VIBRACAO_EXCESSIVA(12),
    SUPERAQUECIMENTO(13),
    DESALINHAMENTO(14),
    OUTROS(99);

    private final int id;

    TipoAnomalia(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(TipoAnomalia tipoAnomalia) { return EnumWithId.getIdFromEnum(tipoAnomalia); }

    public static TipoAnomalia fromId(int id) {
        return EnumWithId.fromId(TipoAnomalia.class, id);
    }

    public static TipoAnomalia fromName(String name) {
        return EnumWithId.fromName(TipoAnomalia.class, name);
    }

    public static TipoAnomalia fromLabel(String label) {
        return EnumWithId.fromLabel(TipoAnomalia.class, label);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
