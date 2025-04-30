package com.example.cper_core.dtos.armazem;

import com.example.cper_core.dtos.stock.StockDto;
import com.example.cper_core.entities.Armazem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Armazem}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ArmazemWithStockDto extends ArmazemDto implements Serializable {
    private Set<StockDto> stocks = new LinkedHashSet<>();

    public ArmazemWithStockDto(Integer id, Set<StockDto> stocks) {
        super(id);
        this.stocks = stocks;
    }
}