package com.example.cper_core.dtos.fatura;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.contrato.ContratoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FaturaDetailsExtendedDto extends FaturaDetailsDto {

    private BigDecimal vMulta;

    @NotNull(groups = OnCreate.class, message = "O contrato é obrigatório")
    @Valid
    private ContratoDto contrato;

    @NotNull(groups = OnCreate.class, message = "O funcionário é obrigatório")
    @Valid
    private FuncionarioDto funcionario;

    private Boolean isDeleted;
}
