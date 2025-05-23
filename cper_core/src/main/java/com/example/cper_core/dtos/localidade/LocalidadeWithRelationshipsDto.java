package com.example.cper_core.dtos.localidade;

import com.example.cper_core.dtos.endereco.EnderecoDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LocalidadeWithRelationshipsDto extends LocalidadeDto {

    @Valid
    private Set<EnderecoDto> enderecos;
}