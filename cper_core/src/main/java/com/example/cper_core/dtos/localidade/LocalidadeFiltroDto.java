package com.example.cper_core.dtos.localidade;

import lombok.Data;

@Data
public class LocalidadeFiltroDto {

    private Integer id;
    private String pais;
    private String distrito;
    private String localidade;
    private String codigoPostal;
}
