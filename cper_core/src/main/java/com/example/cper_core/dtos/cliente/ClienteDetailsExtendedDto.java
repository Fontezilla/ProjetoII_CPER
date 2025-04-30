package com.example.cper_core.dtos.cliente;

import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.entities.Cliente;
import com.example.cper_core.enums.CategoriaConsumo;
import com.example.cper_core.enums.EstadoCliente;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link Cliente}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteDetailsExtendedDto extends ClienteDetailsDto implements Serializable {
    @NotNull(message = "O campo demandaContratada não pode ser nulo")
    @PositiveOrZero(message = "O campo demandaContratada deve ser zero ou positivo")
    private BigDecimal demandaContratada;

    @NotNull(message = "O campo dataNascimento não pode ser nulo")
    @Past(message = "A data de nascimento deve estar no passado")
    private LocalDate dataNascimento;

    @NotNull(message = "O campo dataCadastro não pode ser nulo")
    private LocalDate dataCadastro;

    @NotNull(message = "O campo consumoMedio não pode ser nulo")
    @PositiveOrZero(message = "O campo consumoMedio deve ser zero ou positivo")
    private BigDecimal consumoMedio;

    @NotBlank(message = "O campo categoria de consumo não pode estar vazio")
    private String catConsumo;

    @NotBlank(message = "O campo estado não pode estar vazio")
    private String estado;

    @NotNull(message = "O campo número da porta não pode ser nulo")
    @Positive(message = "O número da porta deve ser positivo")
    private Integer nPorta;

    @NotNull(message = "O campo endereço não pode ser nulo")
    private EnderecoDto endereco;

    public ClienteDetailsExtendedDto(Integer id, String nome, String nif, String email, String telefone,
                                     String tipoEnergia, BigDecimal demandaContratada, LocalDate dataNascimento,
                                     LocalDate dataCadastro, BigDecimal consumoMedio, String catConsumo, String estado,
                                     Integer nPorta, EnderecoDto endereco) {
        super(id, nome, nif, email, telefone, tipoEnergia);
        this.demandaContratada = demandaContratada;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.consumoMedio = consumoMedio;
        this.catConsumo = catConsumo != null ? catConsumo : CategoriaConsumo.OUTRO.toString();
        this.estado = estado != null ? estado : EstadoCliente.ATIVO.toString();
        this.nPorta = nPorta;
        this.endereco = endereco;
    }
}