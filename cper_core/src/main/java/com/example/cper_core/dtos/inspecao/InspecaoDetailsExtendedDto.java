package com.example.cper_core.dtos.inspecao;

import com.example.cper_core.dtos.anomalia.AnomaliaDto;
import com.example.cper_core.dtos.centro_producao.CentroProducaoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InspecaoDetailsExtendedDto extends InspecaoDetailsDto {

    private String descricao;

    private String areaInspecionada;

    private String resultados;

    @Valid
    private AnomaliaDto anomalia;

    @NotNull(message = "O centro de produção é obrigatório")
    @Valid
    private CentroProducaoDto centroProducao;

    private Boolean isDeleted;
}