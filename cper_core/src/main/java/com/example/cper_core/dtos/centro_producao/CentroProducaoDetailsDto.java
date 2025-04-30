package com.example.cper_core.dtos.centro_producao;

import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.entities.CentroProducao;
import com.example.cper_core.enums.EstadoCentro;
import com.example.cper_core.enums.TipoEnergiaRenovavel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link CentroProducao}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CentroProducaoDetailsDto extends CentroProducaoDto implements Serializable {
    @NotBlank(message = "O campo nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "O campo tipo de energia não pode estar vazio")
    private String tipoEnergia;

    @NotNull(message = "O campo capacidade máxima não pode ser nulo")
    @Positive(message = "A capacidade máxima deve ser um valor positivo")
    private BigDecimal capacidadeMax;

    @NotBlank(message = "O campo estado não pode estar vazio")
    private String estado;

    @NotNull(message = "O campo número da porta não pode ser nulo")
    @Positive(message = "O número da porta deve ser positivo")
    private Integer nPorta;

    @NotNull(message = "O campo endereço não pode ser nulo")
    private EnderecoDto endereco;


    public CentroProducaoDetailsDto(Integer id, String nome, String tipoEnergia, BigDecimal capacidadeMax,
                                    String estado, Integer nPorta, EnderecoDto endereco) {
        super(id);
        this.nome = nome;
        this.tipoEnergia = tipoEnergia != null ? tipoEnergia : TipoEnergiaRenovavel.OUTRA.toString();
        this.capacidadeMax = capacidadeMax;
        this.estado = estado != null ? estado : EstadoCentro.OPERACIONAL.toString();
        this.nPorta = nPorta;
        this.endereco = endereco;
    }
}