package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.entities.Funcionario;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Funcionario}
 */

@Data
@NoArgsConstructor
public class FuncionarioDto implements Serializable {
    private Integer id;

    public FuncionarioDto(Integer id) {
        this.id = id;
    }
}