package com.example.cper_core.dtos.endereco;

import lombok.Data;

@Data
public class EnderecoFiltroDto {

    private Integer id;
    private String rua;
    private Integer idLocalidade;
}
