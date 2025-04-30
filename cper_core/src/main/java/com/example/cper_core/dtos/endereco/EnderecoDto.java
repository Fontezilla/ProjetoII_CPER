package com.example.cper_core.dtos.endereco;

import com.example.cper_core.entities.Endereco;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Endereco}
 */

@Data
@NoArgsConstructor
public class EnderecoDto implements Serializable {
    private Integer id;

    public EnderecoDto(Integer id) {
        this.id = id;
    }
}