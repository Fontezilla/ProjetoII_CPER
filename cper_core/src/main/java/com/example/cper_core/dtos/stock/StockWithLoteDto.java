package com.example.cper_core.dtos.stock;

import com.example.cper_core.entities.Lote;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.example.cper_core.entities.Stock}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class StockWithLoteDto extends StockDto implements Serializable {
    private Set<Lote> lotes = new LinkedHashSet<>();

    public StockWithLoteDto(Integer id, Set<Lote> lotes) {
        super(id);
        this.lotes = lotes;
    }
}