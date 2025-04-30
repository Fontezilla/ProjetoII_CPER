package com.example.cper_core.dtos.stock;

import com.example.cper_core.entities.Stock;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Stock}
 */

@Data
@NoArgsConstructor
public class StockDto implements Serializable {
    private Integer id;

    public StockDto(Integer id) {
        this.id = id;
    }
}