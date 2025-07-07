package com.example.cper_core.dtos.solicitacao_energetica;

import com.example.cper_core.enums.EstadoSolicitacaoEnergetica;
import com.example.cper_core.enums.Prioridade;
import com.example.cper_core.enums.TipoEnergiaRenovavel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class SolicitacaoEnergeticaFiltroDto {

    private Integer id;

    private String codigo;

    private OffsetDateTime dataSolicitacaoInicio;
    private OffsetDateTime dataSolicitacaoFim;

    private TipoEnergiaRenovavel tipoEnergia;

    private BigDecimal qtdSolicitadaMin;
    private BigDecimal qtdSolicitadaMax;

    private Prioridade prioridade;

    private EstadoSolicitacaoEnergetica estado;

    private Boolean isDeleted = false;

    private Set<Integer> idsCliente;
    private Set<Integer> idsContrato;
}