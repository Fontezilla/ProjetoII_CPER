package com.example.cper_core.dtos.avaria;

import com.example.cper_core.enums.EstadoAvaria;
import com.example.cper_core.enums.Prioridade;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class AvariaFiltroDto {

    private Integer id;

    private String titulo;

    private String descricao;

    private OffsetDateTime dataInicioInicio;
    private OffsetDateTime dataInicioFim;

    private Prioridade gravidade;

    private EstadoAvaria estado;

    private BigDecimal impactoProducaoMin;
    private BigDecimal impactoProducaoMax;

    private Integer impactoPercentualMin;
    private Integer impactoPercentualMax;

    private Boolean isDeleted = false;

    private Set<Integer> idsCentroProducao;
    private Set<Integer> idsInspecao;
}
