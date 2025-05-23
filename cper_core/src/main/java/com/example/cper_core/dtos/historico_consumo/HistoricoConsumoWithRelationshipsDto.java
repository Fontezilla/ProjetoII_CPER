package com.example.cper_core.dtos.historico_consumo;

import com.example.cper_core.dtos.cliente.ClienteDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HistoricoConsumoWithRelationshipsDto extends HistoricoConsumoDto {
    // Nada adicionado aqui, apenas para manter a estrutura {futuras extensões}
}