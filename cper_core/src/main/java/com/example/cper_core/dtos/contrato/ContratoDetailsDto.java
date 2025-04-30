package com.example.cper_core.dtos.contrato;

import com.example.cper_core.entities.Contrato;
import com.example.cper_core.enums.EstadoContrato;
import com.example.cper_core.enums.TipoContrato;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Contrato}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContratoDetailsDto extends ContratoDto implements Serializable {
    @NotNull(message = "O campo tipo de contrato não pode ser nulo")
    private String tipoContrato;

    @NotNull(message = "O campo quantidade de energia não pode ser nulo")
    @Positive(message = "A quantidade de energia deve ser um valor positivo")
    private BigDecimal qtdEnergia;

    @NotBlank(message = "O campo estado não pode estar vazio")
    private String estado;

    public ContratoDetailsDto(Integer id, String tipoContrato, BigDecimal qtdEnergia, String estado) {
        super(id);
        this.tipoContrato = tipoContrato != null ? tipoContrato : TipoContrato.VENDA_POTENCIA_FIXA.toString();
        this.qtdEnergia = qtdEnergia;
        this.estado = estado != null ? estado : EstadoContrato.EM_ANALISE.toString();
    }
}