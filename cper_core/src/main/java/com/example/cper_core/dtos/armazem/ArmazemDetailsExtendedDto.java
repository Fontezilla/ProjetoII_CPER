package com.example.cper_core.dtos.armazem;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.departamento.DepartamentoDto;
import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArmazemDetailsExtendedDto extends ArmazemDetailsDto {

    @NotNull(groups = OnCreate.class, message = "O departamento é obrigatório")
    @Valid
    private DepartamentoDto departamento;

    @NotBlank(groups = OnCreate.class, message = "O número da porta é obrigatório")
    private String nPorta;

    @Valid
    @NotNull(groups = OnCreate.class, message = "O endereco é obrigatório")
    private EnderecoDto endereco;

    @Valid
    private FuncionarioDto responsavel;

    private Boolean isDeleted;
}

