package com.example.cper_core.dtos.inspecao;

import com.example.cper_core.dtos.centro_producao.CentroProducaoDto;
import com.example.cper_core.entities.Inspecao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Inspecao}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InspecaoDetailsDto extends InspecaoDto implements Serializable {
    @NotBlank(message = "A descrição não pode estar vazia")
    private String descricao;

    @NotNull(message = "O tipo de inspeção não pode ser nulo")
    @Positive(message = "O tipo de inspeção deve ser um número positivo")
    private String tipo;

    @NotNull(message = "O estado da inspeção não pode ser nulo")
    @Positive(message = "O estado da inspeção deve ser um número positivo")
    private String estado;

    @NotNull(message = "O centro de produção não pode ser nulo")
    private CentroProducaoDto centroProducao;

    public InspecaoDetailsDto(Integer id, String descricao, String tipo, String estado, CentroProducaoDto centroProducao) {
        super(id);
        this.descricao = descricao;
        this.tipo = tipo;
        this.estado = estado;
        this.centroProducao = centroProducao;
    }
}