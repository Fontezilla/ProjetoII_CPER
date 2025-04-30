package com.example.cper_core.dtos.anomalia;

import com.example.cper_core.dtos.nota.NotaDto;
import com.example.cper_core.entities.Anomalia;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Anomalia}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AnomaliaWithNotasDto extends AnomaliaDto implements Serializable {
    private Set<NotaDto> notas;

    public AnomaliaWithNotasDto(Integer id, Set<NotaDto> notas) {
        super(id);
        this.notas = notas;
    }
}