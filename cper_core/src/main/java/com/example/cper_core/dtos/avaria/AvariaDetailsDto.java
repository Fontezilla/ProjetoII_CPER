package com.example.cper_core.dtos.avaria;

import com.example.cper_core.dtos.centro_producao.CentroProducaoDto;
import com.example.cper_core.entities.Avaria;
import com.example.cper_core.enums.EstadoAvaria;
import com.example.cper_core.enums.Prioridade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Avaria}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AvariaDetailsDto extends AvariaDto implements Serializable {
    @NotBlank(message = "O campo descrição não pode estar vazio")
    private String descricao;

    @NotBlank(message = "O campo gravidade não pode estar vazio")
    private String gravidade;

    @NotBlank(message = "O campo estado não pode estar vazio")
    private String estado;

    @NotNull(message = "O campo centro de produção não pode ser nulo")
    private CentroProducaoDto centroProducao;

    public AvariaDetailsDto(Integer id, String descricao, String gravidade, String estado,
                            CentroProducaoDto centroProducao) {
        super(id);
        this.descricao = descricao;
        this.gravidade = gravidade;
        this.estado = estado;
        this.centroProducao = centroProducao;
    }
}