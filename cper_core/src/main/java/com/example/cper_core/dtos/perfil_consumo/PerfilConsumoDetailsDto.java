package com.example.cper_core.dtos.perfil_consumo;

import com.example.cper_core.dtos.cliente.ClienteDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link com.example.cper_core.entities.PerfilConsumo}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PerfilConsumoDetailsDto extends PerfilConsumoDto implements Serializable {

    @NotNull(message = "A data de registo não pode ser nula")
    private LocalDate dataRegisto;

    @NotNull(message = "A quantidade consumida não pode ser nula")
    @Positive(message = "A quantidade consumida deve ser um valor positivo")
    private BigDecimal qtdConsumida;

    @NotNull(message = "O cliente não pode ser nulo")
    private ClienteDto cliente;

    public PerfilConsumoDetailsDto(Integer id, LocalDate dataRegisto, BigDecimal qtdConsumida, ClienteDto cliente) {
        super(id);
        this.dataRegisto = dataRegisto;
        this.qtdConsumida = qtdConsumida;
        this.cliente = cliente;
    }
}