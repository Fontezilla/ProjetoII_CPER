package com.example.cper_core.dtos.fatura;

import com.example.cper_core.dtos.contrato.ContratoDto;
import com.example.cper_core.entities.Fatura;
import com.example.cper_core.enums.EstadoFatura;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Fatura}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FaturaDetailsDto extends FaturaDto implements Serializable {
    @NotNull(message = "O campo valor total não pode ser nulo")
    @Positive(message = "O valor total deve ser um valor positivo")
    private BigDecimal vTotal;

    @NotNull(message = "O campo estado não pode ser nulo")
    private String estado;

    @NotNull(message = "O campo contrato não pode ser nulo")
    private ContratoDto contrato;


    public FaturaDetailsDto(Integer id, BigDecimal vTotal, String estado, ContratoDto contrato) {
        super(id);
        this.vTotal = vTotal;
        this.estado = estado != null ? estado : EstadoFatura.EM_ANALISE.toString();
        this.contrato = contrato;
    }
}