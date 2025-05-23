package com.example.cper_core.dtos.armazem_lote;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
public class ArmazemLoteFiltroDto {

    private BigDecimal quantidadeArmazenadaMin;
    private BigDecimal quantidadeArmazenadaMax;

    private Boolean isDeleted = false;

    private Set<Integer> idsArmazem;
    private Set<Integer> idsLote;
}
