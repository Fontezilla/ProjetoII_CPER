package com.example.cper_core.dtos.fatura;

import com.example.cper_core.dtos.contrato.ContratoDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FaturaWithRelationshipsDto extends FaturaDto {
    // Nada adicionado aqui, apenas para manter a estrutura {futuras extensões}
}