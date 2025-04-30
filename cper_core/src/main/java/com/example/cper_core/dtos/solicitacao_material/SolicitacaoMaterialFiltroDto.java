package com.example.cper_core.dtos.solicitacao_material;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SolicitacaoMaterialFiltroDto {

    private Integer id;
    private LocalDate dataPedidoInicio;
    private LocalDate dataPedidoFim;
    private String descricao;
    private Integer estado;
}

