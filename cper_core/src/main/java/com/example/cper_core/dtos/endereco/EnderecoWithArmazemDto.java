package com.example.cper_core.dtos.endereco;

import com.example.cper_core.dtos.armazem.ArmazemDto;
import com.example.cper_core.entities.Endereco;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Endereco}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class EnderecoWithArmazemDto extends EnderecoDto implements Serializable {
    private Set<ArmazemDto> armazems;

    public EnderecoWithArmazemDto(Integer id, Set<ArmazemDto> armazems) {
        super(id);
        this.armazems = armazems;
    }
}