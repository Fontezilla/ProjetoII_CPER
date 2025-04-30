package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.anomalia.AnomaliaDto;
import com.example.cper_core.entities.Funcionario;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Funcionario}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class FuncionarioWithR_AnomaliaDto extends FuncionarioDto implements Serializable {
    private Set<AnomaliaDto> responsavelAnomalias;

    public FuncionarioWithR_AnomaliaDto(Integer id, Set<AnomaliaDto> responsavelAnomalias) {
        super(id);
        this.responsavelAnomalias = responsavelAnomalias;
    }
}