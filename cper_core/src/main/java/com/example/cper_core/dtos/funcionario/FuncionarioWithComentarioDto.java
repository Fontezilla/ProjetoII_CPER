package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.comentario.ComentarioDto;
import com.example.cper_core.entities.Funcionario;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Funcionario}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class FuncionarioWithComentarioDto extends FuncionarioDto implements Serializable {
    private Set<ComentarioDto> comentario;

    public FuncionarioWithComentarioDto(Integer id, Set<ComentarioDto> comentario) {
        super(id);
        this.comentario = comentario;
    }
}