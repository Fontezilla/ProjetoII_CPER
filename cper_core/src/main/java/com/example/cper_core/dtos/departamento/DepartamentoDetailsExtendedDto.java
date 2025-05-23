package com.example.cper_core.dtos.departamento;

import com.example.cper_core.dtos.OnCreate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DepartamentoDetailsExtendedDto extends DepartamentoDetailsDto {

    private String descricao;

    @NotNull(groups = OnCreate.class, message = "A data de criação é obrigatória")
    private OffsetDateTime dataCriacao;

    private BigDecimal orcamento;

    private Boolean isDeleted;
}