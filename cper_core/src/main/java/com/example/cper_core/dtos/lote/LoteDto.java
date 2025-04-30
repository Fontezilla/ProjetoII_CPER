package com.example.cper_core.dtos.lote;

import com.example.cper_core.dtos.material.MaterialDto;
import com.example.cper_core.dtos.stock.StockDto;
import com.example.cper_core.entities.Lote;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Lote}
 */

@Data
@NoArgsConstructor
public class LoteDto implements Serializable {
    private  StockDto stock;
    private  MaterialDto material;

    public LoteDto(StockDto stock, MaterialDto material) {
        this.stock = stock;
        this.material = material;
    }
}