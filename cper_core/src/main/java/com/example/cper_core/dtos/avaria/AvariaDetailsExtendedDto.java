package com.example.cper_core.dtos.avaria;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.centro_producao.CentroProducaoDto;
import com.example.cper_core.dtos.inspecao.InspecaoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AvariaDetailsExtendedDto extends AvariaDetailsDto {

    private String descricao;

    private OffsetDateTime dataResolucao;

    @NotNull(groups = OnCreate.class, message = "O centro de produção é obrigatório")
    @Valid
    private CentroProducaoDto centroProducao;

    @NotNull(groups = OnCreate.class, message = "A inspeção é obrigatória")
    @Valid
    private InspecaoDto inspecao;

    private Boolean isDeleted;
}