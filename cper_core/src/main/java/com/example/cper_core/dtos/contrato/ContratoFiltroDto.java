package com.example.cper_core.dtos.contrato;

import com.example.cper_core.enums.EstadoContrato;
import com.example.cper_core.enums.TipoContrato;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class ContratoFiltroDto {

    private Integer id;

    private String codigo;

    private TipoContrato tipoContrato;
    private EstadoContrato estado;

    private OffsetDateTime dataInicioInicio;
    private OffsetDateTime dataInicioFim;

    private OffsetDateTime dataFimInicio;
    private OffsetDateTime dataFimFim;

    private BigDecimal qtdEnergiaMin;
    private BigDecimal qtdEnergiaMax;

    private BigDecimal qtdEnergiaHMin;
    private BigDecimal qtdEnergiaHMax;

    private Integer prazoPagamentoMin;
    private Integer prazoPagamentoMax;

    private Integer multaAtrasoMin;
    private Integer multaAtrasoMax;

    private Boolean isDeleted = false;

    private Set<Integer> idsFuncionario;
    private Set<Integer> idsEndereco;

    private BigDecimal vElectricidadeMin;
    private BigDecimal vElectricidadeMax;

    private Integer taxaMin;
    private Integer taxaMax;

    private Boolean requerValidacao;
}
