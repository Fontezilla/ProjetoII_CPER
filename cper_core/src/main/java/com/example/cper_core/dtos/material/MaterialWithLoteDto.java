package com.example.cper_core.dtos.material;

import com.example.cper_core.entities.Lote;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.example.cper_core.entities.Material}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialWithLoteDto extends MaterialDto implements Serializable {
    private Set<Lote> lotes;

    public MaterialWithLoteDto(Integer id, Set<Lote> lotes) {
        super(id);
        this.lotes = lotes;
    }
}