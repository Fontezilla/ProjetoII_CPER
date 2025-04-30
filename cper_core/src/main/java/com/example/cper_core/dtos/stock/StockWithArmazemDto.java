package com.example.cper_core.dtos.stock;

import com.example.cper_core.dtos.armazem.ArmazemDto;
import com.example.cper_core.entities.Stock;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Stock}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class StockWithArmazemDto extends StockDto implements Serializable {
    private Set<ArmazemDto> armazems;

    public StockWithArmazemDto(Integer id, Set<ArmazemDto> armazems) {
        super(id);
        this.armazems = armazems;
    }
}