package com.example.cper_core.dtos.morada;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoradaSimplesDto {
    private String rua;
    private String codigoPostal;
    private String nomeLocalidade;
}
