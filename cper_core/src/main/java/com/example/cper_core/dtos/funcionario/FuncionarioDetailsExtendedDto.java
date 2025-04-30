package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.departamento.DepartamentoDto;
import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.entities.Funcionario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link Funcionario}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FuncionarioDetailsExtendedDto extends FuncionarioDetailsDto implements Serializable {

    @Past(message = "A data de nascimento deve ser no passado")
    @NotNull(message = "O campo data de nascimento não pode ser nulo")
    private LocalDate dataNascimento;

    @NotNull(message = "O campo data de contratação não pode ser nulo")
    private LocalDate dataContratacao;

    @PositiveOrZero(message = "O salário deve ser positivo ou zero")
    @NotNull(message = "O campo salário não pode ser nulo")
    private BigDecimal salario;

    @Positive(message = "O número da porta deve ser um número positivo")
    @NotNull(message = "O campo número da porta não pode ser nulo")
    private Integer nPorta;

    @NotNull(message = "O departamento não pode ser nulo")
    private DepartamentoDto departamento;

    @NotNull(message = "O endereço não pode ser nulo")
    private EnderecoDto endereco;

    public FuncionarioDetailsExtendedDto(Integer id, String nome, String nif, String email, String telefone, String cargo, String estado, LocalDate dataNascimento, LocalDate dataContratacao, BigDecimal salario, Integer nPorta, DepartamentoDto departamento, EnderecoDto endereco) {
        super(id, nome, nif, email, telefone, cargo, estado);
        this.dataNascimento = dataNascimento;
        this.dataContratacao = dataContratacao != null ? dataContratacao : LocalDate.now();
        this.salario = salario;
        this.nPorta = nPorta;
        this.departamento = departamento;
        this.endereco = endereco;
    }
}