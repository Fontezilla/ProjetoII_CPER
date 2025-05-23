package com.example.cper_core.dtos.inspecao;

import com.example.cper_core.dtos.nota.NotaDto;
import com.example.cper_core.dtos.avaria.AvariaDto;
import com.example.cper_core.dtos.equipa.EquipaDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InspecaoWithRelationshipsDto extends InspecaoDto {

    @Valid
    private Set<NotaDto> notas;

    @Valid
    private Set<AvariaDto> avarias;

    @Valid
    private Set<EquipaDto> equipas;
}