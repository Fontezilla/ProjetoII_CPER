package com.example.cper_core.dtos.anomalia;

import com.example.cper_core.dtos.nota.NotaDto;
import jakarta.validation.Valid;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AnomaliaWithRelationshipsDto extends AnomaliaDto {

    @Valid
    private Set<NotaDto> notas;
}