package com.example.cper_core.dtos.centro_producao;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.enums.EstadoCentro;
import com.example.cper_core.enums.TipoEnergiaRenovavel;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CentroProducaoDetailsDto extends CentroProducaoDto {

    private String codigo;

    @NotBlank(groups = OnCreate.class, message = "O nome do centro de produção e obrigatorio")
    @Size(max = 255, message = "O nome do centro de produção deve ter no máximo 255 caracteres")
    private String nome;

    @NotNull(groups = OnCreate.class, message = "O tipo de energia renovável e obrigatorio")
    private TipoEnergiaRenovavel tipoEnergia;

    @NotNull(groups = OnCreate.class, message = "A capacidade máxima e obrigatoria")
    @DecimalMin(value = "0.0", message = "A capacidade máxima não pode ser negativa")
    private BigDecimal capacidadeMax;

    private BigDecimal capacidadeAtual;

    @NotNull(groups = OnCreate.class, message = "O estado do centro de produção e obrigatorio")
    private EstadoCentro estado;
}

