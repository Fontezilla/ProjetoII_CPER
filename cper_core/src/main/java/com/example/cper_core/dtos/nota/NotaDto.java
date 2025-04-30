package com.example.cper_core.dtos.nota;

import com.example.cper_core.entities.Nota;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Nota}
 */

@Data
@NoArgsConstructor
public class NotaDto implements Serializable {
    private Integer id;

    public NotaDto(Integer id) {
        this.id = id;
    }
}