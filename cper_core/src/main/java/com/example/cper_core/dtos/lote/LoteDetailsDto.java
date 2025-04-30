package com.example.cper_core.dtos.lote;

import com.example.cper_core.dtos.material.MaterialDto;
import com.example.cper_core.dtos.stock.StockDto;
import com.example.cper_core.entities.Lote;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Lote}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class LoteDetailsDto extends LoteDto implements Serializable {
    @NotNull(message = "A quantidade não pode ser nula")
    @PositiveOrZero(message = "A quantidade deve ser zero ou positiva")
    private Integer qtd;

    private LocalDate dataValidade;

    @NotNull(message = "A data de adição não pode ser nula")
    private LocalDate dataAdicao;

    public LoteDetailsDto(StockDto stock, MaterialDto material, Integer qtd, LocalDate dataValidade, LocalDate dataAdicao) {
        super(stock, material);
        this.qtd = qtd;
        this.dataValidade = dataValidade;
        this.dataAdicao = dataAdicao;
    }
}