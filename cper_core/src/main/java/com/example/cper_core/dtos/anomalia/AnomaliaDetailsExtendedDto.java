package com.example.cper_core.dtos.anomalia;

import com.example.cper_core.dtos.centro_producao.CentroProducaoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.inspecao.InspecaoDto;
import com.example.cper_core.entities.Anomalia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link Anomalia}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AnomaliaDetailsExtendedDto extends AnomaliaDetailsDto implements Serializable {
    @NotBlank(message = "A localização não pode estar vazio")
    private String localizacao;

    @NotNull(message = "A inspeção não pode ser nulo")
    private InspecaoDto inspecao;

    @NotNull(message = "O centro de produção não pode ser nulo")
    private CentroProducaoDto centroProducao;

    @NotNull(message = "O funcionario não pode ser nulo")
    private FuncionarioDto funcionario;

    public AnomaliaDetailsExtendedDto(Integer id, String tipoAnomalia, String descricao, LocalDate dataIdentificacao,
                                      String gravidade, String estado, String localizacao, InspecaoDto inspecao,
                                      CentroProducaoDto centroProducao, FuncionarioDto funcionario) {
        super(id, tipoAnomalia, descricao, dataIdentificacao, gravidade, estado);
        this.localizacao = localizacao;
        this.inspecao = inspecao;
        this.centroProducao = centroProducao;
        this.funcionario = funcionario;
    }
}