package com.example.cper_core.enums;

public enum TipoEnergiaRenovavel {

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

    public int getId() {
        return id;
    }

    public static TipoEnergiaRenovavel fromId(int id) {
        for (TipoEnergiaRenovavel tipo : TipoEnergiaRenovavel.values()) {
            if (tipo.getId() == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de Energia Renovável inválido: " + id);
    }

    public static TipoEnergiaRenovavel fromName(String name) {
        try {
            return TipoEnergiaRenovavel.valueOf(name);
        } catch (Exception e) {
            throw new IllegalArgumentException("Tipo de Energia Renovável inválido: " + name);
        }
    }

    @Override
    public String toString() {
        return name();
    }
}
