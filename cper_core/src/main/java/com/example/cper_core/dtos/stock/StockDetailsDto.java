package com.example.cper_core.dtos.stock;

import com.example.cper_core.entities.Stock;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Stock}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class StockDetailsDto extends StockDto implements Serializable {
    @NotNull(message = "A quantidade mínima não pode ser nula")
    @Positive(message = "A quantidade mínima deve ser um valor positivo")
    private Integer qtdMinima;

    @NotNull(message = "A quantidade máxima não pode ser nula")
    @Positive(message = "A quantidade máxima deve ser um valor positivo")
    private Integer qtdMaxima;

    @NotNull(message = "A localização não pode ser nula")
    @Size(min = 1, message = "A localização não pode ser vazia")
    private String localizacao;

    @NotNull(message = "A data de adição não pode ser nula")
    private LocalDate dataAdicao;

    public StockDetailsDto(Integer id, Integer qtdMinima, Integer qtdMaxima, String localizacao, LocalDate dataAdicao) {
        super(id);
        this.qtdMinima = qtdMinima;
        this.qtdMaxima = qtdMaxima;
        this.localizacao = localizacao;
        this.dataAdicao = dataAdicao;
    }
}