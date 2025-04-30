package com.example.cper_core.enums;

public enum CategoriaConsumo {
    RESIDENCIAL(1),
    COMERCIAL(2),
    INDUSTRIAL(3),
    AGRICOLA(4),
    PUBLICO(5),
    OUTRO(6);

    private final int id;

    CategoriaConsumo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static CategoriaConsumo fromId(int id) {
        for (CategoriaConsumo categoria : values()) {
            if (categoria.id == id) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("CategoriaConsumo inválida: " + id);
    }

    public static CategoriaConsumo fromName(String name) {
        for (CategoriaConsumo categoria : values()) {
            if (categoria.name().equalsIgnoreCase(name)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("CategoriaConsumo inválida: " + name);
    }

    @Override
    public String toString() {
        return name();
    }
}
