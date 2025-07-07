package com.example.cper_core.dtos.material;

import com.example.cper_core.enums.UnidadePeso;
import com.example.cper_core.enums.UnidadeVolume;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MaterialFiltroDto {

    private Integer id;

    private String codigo;

    private String nome;
    private String categoria;

    private UnidadePeso uniMedidaPeso;
    private UnidadeVolume uniMedidaVolume;

    private BigDecimal custoUniMin;
    private BigDecimal custoUniMax;

    private BigDecimal pesoMin;
    private BigDecimal pesoMax;

    private BigDecimal volumeMin;
    private BigDecimal volumeMax;

    private Boolean isDeleted = false;
}
