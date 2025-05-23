package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum EstadoPedidoMaterial implements EnumWithId<EstadoPedidoMaterial> {
    PENDENTE(1),
    EM_PROCESSO(2),
    ENVIADO(3);

    private final int id;

    EstadoPedidoMaterial(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(EstadoPedidoMaterial estadoPedidoMaterial) { return EnumWithId.getIdFromEnum(estadoPedidoMaterial); }

    public static EstadoPedidoMaterial fromId(int id) {
        return EnumWithId.fromId(EstadoPedidoMaterial.class, id);
    }

    public static EstadoPedidoMaterial fromName(String name) {
        return EnumWithId.fromName(EstadoPedidoMaterial.class, name);
    }

    @Override
    public String toString() {
        return name();
    }
}
