package com.example.cper_core.enums;

public enum TipoInspecao {

    ROTINA(0),                  // Inspeção periódica de rotina
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

    public int getId() {
        return id;
    }

    public static TipoInspecao fromId(int id) {
        for (TipoInspecao tipo : values()) {
            if (tipo.id == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("TipoInspecao inválido: " + id);
    }

    public static TipoInspecao fromName(String name) {
        for (TipoInspecao tipo : values()) {
            if (tipo.name().equalsIgnoreCase(name)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("TipoInspecao inválido: " + name);
    }

    @Override
    public String toString() {
        return name();
    }
}
