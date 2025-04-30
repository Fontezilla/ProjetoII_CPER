package com.example.cper_core.dtos.material;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.example.cper_core.entities.Material}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialDetailsExtendedDto extends MaterialDetailsDto implements Serializable {
    @NotBlank(message = "A unidade de medida não pode estar vazia")
    private String uniMedida;

    @NotNull(message = "O custo unitário não pode ser nulo")
    @PositiveOrZero(message = "O custo unitário deve ser positivo ou zero")
    private BigDecimal custoUni;

    @PositiveOrZero(message = "O peso deve ser positivo ou zero")
    private BigDecimal peso;

    @PositiveOrZero(message = "O volume deve ser positivo ou zero")
    private BigDecimal volume;

    public MaterialDetailsExtendedDto(Integer id, String nome, String descricao, String categoria, String uniMedida, BigDecimal custoUni, BigDecimal peso, BigDecimal volume) {
        super(id, nome, descricao, categoria);
        this.uniMedida = uniMedida;
        this.custoUni = custoUni;
        this.peso = peso;
        this.volume = volume;
    }
}