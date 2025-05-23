package com.example.cper_core.dtos.equipa;

import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.inspecao.InspecaoDto;
import com.example.cper_core.dtos.avaria.AvariaDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipaWithRelationshipsDto extends EquipaDto {

    @Valid
    private Set<FuncionarioDto> funcionarios;

    @Valid
    private Set<AvariaDto> avarias;

    @Valid
    private Set<InspecaoDto> inspecoes;
}
