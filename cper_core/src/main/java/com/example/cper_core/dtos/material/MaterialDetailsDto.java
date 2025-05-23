package com.example.cper_core.dtos.material;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.enums.UnidadePeso;
import com.example.cper_core.enums.UnidadeVolume;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MaterialDetailsDto extends MaterialDto {

    @NotBlank(groups = OnCreate.class, message = "O nome é obrigatório")
    private String nome;

    @NotBlank(groups = OnCreate.class, message = "A categoria é obrigatória")
    private String categoria;

    @NotNull(groups = OnCreate.class, message = "O custo unitário é obrigatório")
    private BigDecimal custoUni;
}
