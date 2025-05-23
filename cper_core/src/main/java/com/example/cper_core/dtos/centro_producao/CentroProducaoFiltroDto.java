package com.example.cper_core.dtos.centro_producao;

import com.example.cper_core.enums.EstadoCentro;
import com.example.cper_core.enums.TipoEnergiaRenovavel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class CentroProducaoFiltroDto {

    private Integer id;

    private String nome;

    private TipoEnergiaRenovavel tipoEnergia;

    private BigDecimal capacidadeMax;

    private BigDecimal capacidadeAtual;

    private OffsetDateTime dataInicioInicio;
    private OffsetDateTime dataInicioFim;

    private BigDecimal custoOperacional;

    private EstadoCentro estado;

    private String nPorta;

    private Boolean isDeleted = false;

    private Set<Integer> idsDepartamento;
    private Set<Integer> idsEndereco;
    private Set<Integer> idsFuncionario;
}
