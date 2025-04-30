package com.example.cper_core.dtos.inspecao;

import com.example.cper_core.dtos.anomalia.AnomaliaDto;
import com.example.cper_core.dtos.centro_producao.CentroProducaoDto;
import com.example.cper_core.entities.Inspecao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Inspecao}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class InspecaoDetailsExtendedDto extends InspecaoDetailsDto implements Serializable {
    @NotNull(message = "A data da inspeção não pode ser nula")
    private LocalDate data;

    @NotBlank(message = "A área inspecionada não pode estar vazia")
    private String areaInspecionada;

    @NotBlank(message = "O campo resultados não pode estar vazio")
    private String resultados;

    private AnomaliaDto anomalia;

    public InspecaoDetailsExtendedDto(Integer id, String descricao, String tipo, String estado, CentroProducaoDto centroProducao, LocalDate data, String areaInspecionada, String resultados, AnomaliaDto anomalia) {
        super(id, descricao, tipo, estado, centroProducao);
        this.data = data;
        this.areaInspecionada = areaInspecionada;
        this.resultados = resultados;
        this.anomalia = anomalia;
    }
}