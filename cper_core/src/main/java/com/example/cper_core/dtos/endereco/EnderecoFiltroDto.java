package com.example.cper_core.dtos.endereco;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class EnderecoFiltroDto {

    private Integer id;

    private String rua;

    private Boolean isDeleted = false;

    private Set<Integer> idsLocalidade;
}

