package com.example.cper_core.dtos.material;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.enums.UnidadePeso;
import com.example.cper_core.enums.UnidadeVolume;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MaterialDetailsExtendedDto extends MaterialDetailsDto {

    private String descricao;

    @NotNull(groups = OnCreate.class, message = "O peso é obrigatório")
    private BigDecimal peso;

    @NotNull(groups = OnCreate.class, message = "A unidade de medida de peso é obrigatória")
    private UnidadePeso uniMedidaPeso;

    @NotNull(groups = OnCreate.class, message = "O volume é obrigatório")
    private BigDecimal volume;

    @NotNull(groups = OnCreate.class, message = "A unidade de medida de volume é obrigatória")
    private UnidadeVolume uniMedidaVolume;

    private Boolean isDeleted;
}
