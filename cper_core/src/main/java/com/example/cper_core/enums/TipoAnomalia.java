package com.example.cper_core.enums;

public enum TipoAnomalia {

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

    public int getId() {
        return id;
    }

    public static TipoAnomalia fromId(int id) {
        for (TipoAnomalia tipo : TipoAnomalia.values()) {
            if (tipo.getId() == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de Anomalia inválido: " + id);
    }

    public static TipoAnomalia fromName(String name) {
        try {
            return TipoAnomalia.valueOf(name);
        } catch (Exception e) {
            throw new IllegalArgumentException("Tipo de Anomalia inválido: " + name);
        }
    }

    @Override
    public String toString() {
        return name();
    }
}
