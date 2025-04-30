package com.example.cper_core.dtos.perfil_consumo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PerfilConsumoFiltroDto {

    private Integer id;
    private LocalDate dataRegistoInicio;
    private LocalDate dataRegistoFim;
    private BigDecimal qtdConsumidaMin;
    private BigDecimal qtdConsumidaMax;
    private Integer idCliente;
}

