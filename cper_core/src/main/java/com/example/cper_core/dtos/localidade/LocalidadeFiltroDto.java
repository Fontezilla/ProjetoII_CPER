package com.example.cper_core.dtos.localidade;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocalidadeFiltroDto {

    private Integer id;

    private String nome;

    private String codigoPostal;

    private String distrito;

    private String pais;

    private Boolean isDeleted = false;
}