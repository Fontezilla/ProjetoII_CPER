package com.example.cper_core.dtos.equipa;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.departamento.DepartamentoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipaDetailsExtendedDto extends EquipaDetailsDto {

    @NotNull(groups = OnCreate.class, message = "O departamento é obrigatório")
    @Valid
    private DepartamentoDto departamento;

    @NotNull(groups = OnCreate.class, message = "A data de criação é obrigatória")
    private OffsetDateTime dataCriacao;

    private Boolean isDeleted;
}