package com.example.cper_core.dtos.pedido_material;

import com.example.cper_core.enums.EstadoPedidoMaterial;
import com.example.cper_core.enums.Prioridade;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class PedidoMaterialFiltroDto {

    private Integer id;

    private String codigo;

    private LocalDate dataCriacaoInicio;
    private LocalDate dataCriacaoFim;

    private String descricao;

    private Prioridade prioridade;

    private EstadoPedidoMaterial estado;

    private Boolean isDeleted = false;

    private Set<Integer> idsAvarias;
}