package com.example.cper_core.dtos.material;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MaterialFiltroDto {

    private Integer id;
    private String nome;
    private String descricao;
    private String categoria;
    private String uniMedida;
    private BigDecimal custoUniMin;
    private BigDecimal custoUniMax;
    private BigDecimal pesoMin;
    private BigDecimal pesoMax;
    private BigDecimal volumeMin;
    private BigDecimal volumeMax;
}
