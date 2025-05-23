package com.example.cper_core.dtos.anomalia;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.centro_producao.CentroProducaoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.inspecao.InspecaoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AnomaliaDetailsExtendedDto extends AnomaliaDetailsDto {

    private String descricao;

    private String localizacao;

    @NotNull(groups = OnCreate.class, message = "A inspeção não pode ser nula")
    @Valid
    private InspecaoDto inspecao;

    @NotNull(groups = OnCreate.class, message = "O centro de produção não pode ser nulo")
    @Valid
    private CentroProducaoDto centroProducao;

    @NotNull(groups = OnCreate.class, message = "O funcionário não pode ser nulo")
    @Valid
    private FuncionarioDto funcionario;

    private Boolean isDeleted;
}