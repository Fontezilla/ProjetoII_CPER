package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum TipoContrato implements EnumWithId<TipoContrato> {
    VENDA_POTENCIA_FIXA(0),
    VENDA_CONSUMO_VARIAVEL(1),
    VENDA_LONGO_PRAZO(2),
    VENDA_SPOT(3),
    FORNECIMENTO_VERDE_CERTIFICADO(4);

    private final int id;

    TipoContrato(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(TipoContrato tipoContrato) { return EnumWithId.getIdFromEnum(tipoContrato); }

    public static TipoContrato fromId(int id) {
        return EnumWithId.fromId(TipoContrato.class, id);
    }

    public static TipoContrato fromName(String name) {
        return EnumWithId.fromName(TipoContrato.class, name);
    }

    public static TipoContrato fromLabel(String label) {
        return EnumWithId.fromLabel(TipoContrato.class, label);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
