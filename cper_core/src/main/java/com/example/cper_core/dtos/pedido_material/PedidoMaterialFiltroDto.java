package com.example.cper_core.dtos.pedido_material;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PedidoMaterialFiltroDto {

    private Integer id;
    private LocalDate dataCriacaoInicio;
    private LocalDate dataCriacaoFim;
    private String descricao;
    private Integer prioridade;
    private Integer estado;
}

