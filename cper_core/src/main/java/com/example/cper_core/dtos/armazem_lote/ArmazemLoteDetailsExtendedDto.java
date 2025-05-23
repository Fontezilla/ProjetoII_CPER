package com.example.cper_core.dtos.armazem_lote;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.armazem.ArmazemDto;
import com.example.cper_core.dtos.lote.LoteDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArmazemLoteDetailsExtendedDto extends ArmazemLoteDetailsDto {

    @NotNull(groups = OnCreate.class, message = "O armazém é obrigatório")
    @Valid
    private ArmazemDto armazem;

    @NotNull(groups = OnCreate.class, message = "O lote é obrigatório")
    @Valid
    private LoteDto lote;
}

