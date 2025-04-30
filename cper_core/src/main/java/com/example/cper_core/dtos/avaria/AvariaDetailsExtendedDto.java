package com.example.cper_core.dtos.avaria;

import com.example.cper_core.dtos.centro_producao.CentroProducaoDto;
import com.example.cper_core.dtos.inspecao.InspecaoDto;
import com.example.cper_core.entities.Avaria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link Avaria}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AvariaDetailsExtendedDto extends AvariaDetailsDto implements Serializable {
    @NotNull(message = "O campo data de início não pode ser nulo")
    private LocalDate dataInicio;

    private LocalDate dataResolucao;

    @NotBlank(message = "O campo impacto na produção não pode estar vazio")
    private String impactoProducao;

    @NotNull(message = "O campo inspeção não pode ser nulo")
    private InspecaoDto inspecao;

    public AvariaDetailsExtendedDto(Integer id, String descricao, String gravidade, String estado,
                                    CentroProducaoDto centroProducao, LocalDate dataInicio, LocalDate dataResolucao,
                                    String impactoProducao, InspecaoDto inspecao) {
        super(id, descricao, gravidade, estado, centroProducao);
        this.dataInicio = dataInicio != null ? dataInicio : LocalDate.now();
        this.dataResolucao = dataResolucao;
        this.impactoProducao = impactoProducao != null ? impactoProducao : "";
        this.inspecao = inspecao;
    }
}