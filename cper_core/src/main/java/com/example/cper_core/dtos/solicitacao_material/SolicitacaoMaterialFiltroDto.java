package com.example.cper_core.dtos.solicitacao_material;

import com.example.cper_core.enums.EstadoSolicitacaoMaterial;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class SolicitacaoMaterialFiltroDto {

    private Integer id;

    private String codigo;

    private OffsetDateTime dataPedidoInicio;
    private OffsetDateTime dataPedidoFim;

    private String descricao;

    private EstadoSolicitacaoMaterial estado;

    private Boolean isDeleted = false;

    private Set<Integer> idsMaterialSolicitacao;
}
