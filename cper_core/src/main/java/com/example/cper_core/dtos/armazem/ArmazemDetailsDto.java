package com.example.cper_core.dtos.armazem;

import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.entities.Armazem;
import com.example.cper_core.enums.EstadoArmazem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Armazem}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArmazemDetailsDto extends ArmazemDto implements Serializable {

    @NotBlank(message = "O campo nome não pode estar vazio")
    private String nome;

    @NotNull(message = "O campo capacidade total não pode ser nulo")
    @PositiveOrZero(message = "A capacidade total deve ser zero ou um valor positivo")
    private Integer capacidadeTotal;

    @PositiveOrZero(message = "A capacidade ocupada deve ser zero ou um valor positivo")
    private Integer capacidadeOcupada;

    @NotNull(message = "O campo número da porta não pode ser nulo")
    @PositiveOrZero(message = "O número da porta deve ser zero ou positivo")
    private Integer nPorta;

    @NotNull(message = "O campo endereço não pode ser nulo")
    private EnderecoDto endereco;

    @NotBlank(message = "O campo estado não pode estar vazio")
    private String estado;

    public ArmazemDetailsDto(Integer id, String nome, Integer capacidadeTotal, Integer capacidadeOcupada,
                             Integer nPorta, EnderecoDto endereco, String estado) {
        super(id);
        this.nome = nome;
        this.capacidadeTotal = capacidadeTotal;
        this.capacidadeOcupada = capacidadeOcupada != null ? capacidadeOcupada : 0;
        this.nPorta = nPorta;
        this.endereco = endereco;
        this.estado = estado != null ? estado : EstadoArmazem.ATIVO.toString();
    }
}