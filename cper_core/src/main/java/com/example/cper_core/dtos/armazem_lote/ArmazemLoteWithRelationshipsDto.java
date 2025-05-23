package com.example.cper_core.dtos.armazem_lote;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArmazemLoteWithRelationshipsDto extends ArmazemLoteDto {
    // Nada aqui, pois não há relacionamentos adicionais
}
