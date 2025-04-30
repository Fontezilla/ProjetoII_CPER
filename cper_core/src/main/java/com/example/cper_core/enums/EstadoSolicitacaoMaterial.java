package com.example.cper_core.enums;

public enum EstadoSolicitacaoMaterial {
    APAGADO(0),
    PENDENTE(1),
    ENVIADO(2);

    private final int id;

    EstadoSolicitacaoMaterial(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoSolicitacaoMaterial fromId(int id) {
        for (EstadoSolicitacaoMaterial estado : values()) {
            if (estado.id == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoPMaterial inválido: " + id);
    }

    public static EstadoSolicitacaoMaterial fromName(String name) {
        for (EstadoSolicitacaoMaterial estado : values()) {
            if (estado.name().equalsIgnoreCase(name)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("EstadoPMaterial inválido: " + name);
    }

    @Override
    public String toString() {
        return name();
    }
}
