package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum EstadoSolicitacaoMaterial implements EnumWithId<EstadoSolicitacaoMaterial> {
    PENDENTE(1),
    ENVIADO(2);

    private final int id;

    EstadoSolicitacaoMaterial(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(EstadoSolicitacaoMaterial estadoSolicitacaoMaterial) { return EnumWithId.getIdFromEnum(estadoSolicitacaoMaterial); }

    public static EstadoSolicitacaoMaterial fromId(int id) {
        return EnumWithId.fromId(EstadoSolicitacaoMaterial.class, id);
    }

    public static EstadoSolicitacaoMaterial fromName(String name) {
        return EnumWithId.fromName(EstadoSolicitacaoMaterial.class, name);
    }

    public static EstadoSolicitacaoMaterial fromLabel(String label) {
        return EnumWithId.fromLabel(EstadoSolicitacaoMaterial.class, label);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
