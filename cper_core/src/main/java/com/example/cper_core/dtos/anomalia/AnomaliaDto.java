package com.example.cper_core.dtos.anomalia;

import com.example.cper_core.entities.Anomalia;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Anomalia}
 */

@Data
@NoArgsConstructor
public class AnomaliaDto implements Serializable {
    private Integer id;

    public AnomaliaDto(Integer id) {
        this.id = id;
    }
}