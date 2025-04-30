package com.example.cper_core.dtos.endereco;

import com.example.cper_core.dtos.centro_producao.CentroProducaoDto;
import com.example.cper_core.entities.Endereco;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Endereco}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class EnderecoWithCentroDto extends EnderecoDto implements Serializable {
    private Set<CentroProducaoDto> centroProducaos;

    public EnderecoWithCentroDto(Integer id, Set<CentroProducaoDto> centroProducaos) {
        super(id);
        this.centroProducaos = centroProducaos;
    }
}