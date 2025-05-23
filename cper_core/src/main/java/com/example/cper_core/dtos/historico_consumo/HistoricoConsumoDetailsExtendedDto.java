package com.example.cper_core.dtos.historico_consumo;

import com.example.cper_core.dtos.cliente.ClienteDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HistoricoConsumoDetailsExtendedDto extends HistoricoConsumoDetailsDto {

    @NotNull(message = "O cliente é obrigatório")
    @Valid
    private ClienteDto cliente;

    private Boolean isDeleted;
}
