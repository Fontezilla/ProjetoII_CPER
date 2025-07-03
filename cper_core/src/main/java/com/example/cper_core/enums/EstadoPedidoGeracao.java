package com.example.cper_core.enums;

import com.example.cper_core.enums.Interface.EnumWithId;

public enum EstadoPedidoGeracao implements EnumWithId<EstadoPedidoGeracao> {
    CRIADO(1),
    EM_ANALISE(2),
    APROVADO(3),
    EM_EXECUCAO(4),
    CONCLUIDO(5),
    CANCELADO(6);

    private final int id;

    EstadoPedidoGeracao(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public static int getIdFromEnum(EstadoPedidoGeracao estadoPedidoGeracao) { return EnumWithId.getIdFromEnum(estadoPedidoGeracao); }

    public static EstadoPedidoGeracao fromId(int id) {
        return EnumWithId.fromId(EstadoPedidoGeracao.class, id);
    }

    public static EstadoPedidoGeracao fromName(String name) {
        return EnumWithId.fromName(EstadoPedidoGeracao.class, name);
    }

    public static EstadoPedidoGeracao fromLabel(String label) {
        return EnumWithId.fromLabel(EstadoPedidoGeracao.class, label);
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
