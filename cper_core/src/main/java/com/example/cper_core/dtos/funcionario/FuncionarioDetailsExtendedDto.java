package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.OnUpdate;
import com.example.cper_core.dtos.departamento.DepartamentoDto;
import com.example.cper_core.dtos.endereco.EnderecoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FuncionarioDetailsExtendedDto extends FuncionarioDetailsDto {

    @NotNull(groups = OnCreate.class, message = "A data de nascimento é obrigatória")
    private OffsetDateTime dataNascimento;

    @NotNull(groups = OnCreate.class, message = "A data de contratação é obrigatória")
    private OffsetDateTime dataContratacao;

    @NotNull(groups = OnCreate.class, message = "O salário é obrigatório")
    @DecimalMin(value = "0.0", inclusive = true, message = "O salário não pode ser negativo", groups = {OnCreate.class, OnUpdate.class})
    private BigDecimal salario;

    @NotNull(groups = OnCreate.class, message = "O departamento é obrigatório")
    @Valid
    private DepartamentoDto departamento;

    @NotBlank(groups = OnCreate.class, message = "O número da porta é obrigatório")
    private String nPorta;

    @NotNull(groups = OnCreate.class, message = "O endereço é obrigatório")
    @Valid
    private EnderecoDto endereco;

    private Boolean isDeleted;
}