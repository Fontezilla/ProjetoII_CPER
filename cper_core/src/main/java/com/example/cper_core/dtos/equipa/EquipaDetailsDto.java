package com.example.cper_core.dtos.equipa;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipaDetailsDto extends EquipaDto {

    @NotBlank(groups = OnCreate.class, message = "O nome é obrigatório")
    @Size(max = 255, groups = OnCreate.class, message = "O nome deve ter no máximo 255 caracteres")
    private String nome;

    @NotNull(groups = OnCreate.class, message = "O responsável é obrigatório")
    @Valid
    private FuncionarioDto funcionario;

    private String areaAtuacao;
}

