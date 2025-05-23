package com.example.cper_core.dtos.pedido_geracao;

import com.example.cper_core.enums.EstadoPedidoGeracao;
import com.example.cper_core.enums.Prioridade;
import com.example.cper_core.enums.TipoEnergiaRenovavel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class PedidoGeracaoFiltroDto {

    private Integer id;

    private LocalDate dataCriacaoInicio;
    private LocalDate dataCriacaoFim;

    private LocalDate dataPrevisaoInicio;
    private LocalDate dataPrevisaoFim;

    private BigDecimal qtdEnergiaMin;
    private BigDecimal qtdEnergiaMax;

    private TipoEnergiaRenovavel tipoEnergia;
    private Prioridade prioridade;
    private EstadoPedidoGeracao estado;

    private Boolean isDeleted = false;

    private Set<Integer> idsContrato;
    private Set<Integer> idsCentroProducao;
    private Set<Integer> idsFuncionario;
}