package com.example.cper_core.enums;

public enum EstadoArmazem {
    APAGADO(0),
    ATIVO(1),
    EM_MANUTENCAO(2),
    ENCERRADO(3),
    EM_CONSTRUCAO(4),
    INATIVO(5),;

    private final int id;

    EstadoArmazem(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoArmazem fromId(int id) {
        for (EstadoArmazem estado : values()) {
            if (estado.id == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado inválido: " + id);
    }

    public static EstadoArmazem fromName(String name) {
        for (EstadoArmazem estado : values()) {
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
