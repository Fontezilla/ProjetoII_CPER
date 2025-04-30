package com.example.cper_core.dtos.pedido_geracao;

import com.example.cper_core.entities.PedidoGeracao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * DTO for {@link PedidoGeracao}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PedidoGeracaoDetailsDto extends PedidoGeracaoDto implements Serializable {
    @NotNull(message = "O campo quantidade de energia não pode ser nulo")
    @Positive(message = "A quantidade de energia deve ser positiva")
    private BigDecimal qtdEnergia;

    @NotBlank(message = "O campo tipo de energia não pode estar vazio")
    private String tipoEnergia;

    @NotBlank(message = "O campo prioridade não pode estar vazio")
    private String prioridade;

    @NotBlank(message = "O campo estado não pode estar vazio")
    private String estado;

    public PedidoGeracaoDetailsDto(Integer id, BigDecimal qtdEnergia, String tipoEnergia, String prioridade, String estado) {
        super(id);
        this.qtdEnergia = qtdEnergia;
        this.tipoEnergia = tipoEnergia;
        this.prioridade = prioridade;
        this.estado = estado;
    }
}