package com.example.cper_core.dtos.departamento;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.enums.Setor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DepartamentoDetailsDto extends DepartamentoDto {

    @NotBlank(groups = OnCreate.class, message = "O nome é obrigatório")
    @Size(max = 255, groups = OnCreate.class, message = "O nome deve ter no máximo 255 caracteres")
    private String nome;

    @NotNull(groups = OnCreate.class, message = "O setor é obrigatório")
    private Setor setor;

    private Integer numFuncionarios;
}

