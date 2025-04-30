package com.example.cper_core.enums;

public enum TipoContrato {
    VENDA_POTENCIA_FIXA(0),
    VENDA_CONSUMO_VARIAVEL(1),
    VENDA_LONGO_PRAZO(2),
    VENDA_SPOT(3),
    FORNECIMENTO_VERDE_CERTIFICADO(4);

    private final int id;

    TipoContrato(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TipoContrato fromId(int id) {
        for (TipoContrato tipo : values()) {
            if (tipo.id == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("ContratoTipo inválido: " + id);
    }

    public static TipoContrato fromName(String name) {
        for (TipoContrato tipo : values()) {
            if (tipo.name().equalsIgnoreCase(name)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("ContratoTipo inválido: " + name);
    }

    @Override
    public String toString() {
        return name();
    }
}
