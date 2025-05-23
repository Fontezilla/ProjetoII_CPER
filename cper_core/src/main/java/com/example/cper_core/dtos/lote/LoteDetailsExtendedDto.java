package com.example.cper_core.dtos.lote;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.material.MaterialDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LoteDetailsExtendedDto extends LoteDetailsDto {

    @NotNull(groups = OnCreate.class, message = "A data de chegada é obrigatória")
    private OffsetDateTime dataChegada;

    private OffsetDateTime dataValidade;

    @NotNull(groups = OnCreate.class, message = "O material é obrigatório")
    @Valid
    private MaterialDto material;

    private Boolean isDeleted;
}

