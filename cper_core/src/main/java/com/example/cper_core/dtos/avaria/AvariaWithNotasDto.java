package com.example.cper_core.dtos.avaria;

import com.example.cper_core.dtos.nota.NotaDto;
import com.example.cper_core.entities.Avaria;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * DTO for {@link Avaria}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AvariaWithNotasDto extends AvariaDto implements Serializable {
    private Set<NotaDto> notas;

    public AvariaWithNotasDto(Integer id, Set<NotaDto> notas) {
        super(id);
        this.notas = notas;
    }
}