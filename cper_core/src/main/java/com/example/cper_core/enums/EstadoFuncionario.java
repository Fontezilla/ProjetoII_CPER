package com.example.cper_core.enums;

public enum EstadoFuncionario {
    APAGADO(0),
    ATIVO(1),
    OCUPADO(2),
    AUSENTE(3),
    INATIVO(4);

    private final int id;

    EstadoFuncionario(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoFuncionario fromId(int id) {
        for (EstadoFuncionario estado : values()) {
            if (estado.id == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado inválido: " + id);
    }

    public static EstadoFuncionario fromName(String name) {
        for (EstadoFuncionario estado : values()) {
            if (estado.name().equalsIgnoreCase(name)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado inválido: " + name);
    }

    @Override
    public String toString() {
        return name();
    }
}
