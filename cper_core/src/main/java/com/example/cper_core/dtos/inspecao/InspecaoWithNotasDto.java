package com.example.cper_core.dtos.inspecao;

import com.example.cper_core.dtos.nota.NotaDto;
import com.example.cper_core.entities.Inspecao;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Inspecao}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class InspecaoWithNotasDto extends InspecaoDto implements Serializable {
    private Set<NotaDto> notas;

    public InspecaoWithNotasDto(Integer id, Set<NotaDto> notas) {
        super(id);
        this.notas = notas;
    }
}