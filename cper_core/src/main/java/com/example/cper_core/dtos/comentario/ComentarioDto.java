package com.example.cper_core.dtos.comentario;

import com.example.cper_core.entities.Comentario;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Comentario}
 */

@Data
@NoArgsConstructor
public class ComentarioDto implements Serializable {
    private Integer id;

    public ComentarioDto(Integer id) {
        this.id = id;
    }
}