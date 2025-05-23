package com.example.cper_core.dtos.nota;

import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaDto;
import com.example.cper_core.dtos.anomalia.AnomaliaDto;
import com.example.cper_core.dtos.avaria.AvariaDto;
import com.example.cper_core.dtos.inspecao.InspecaoDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotaDetailsExtendedDto extends NotaDetailsDto {

    private String descricao;

    private Boolean isDeleted;

    @Valid
    private InspecaoDto inspecao;

    @Valid
    private AnomaliaDto anomalia;

    @Valid
    private AvariaDto avaria;

    @Valid
    private SolicitacaoEnergeticaDto solicitacaoEnergetica;
}