package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.entities.Funcionario;
import com.example.cper_core.enums.EstadoFatura;
import com.example.cper_core.enums.EstadoFuncionario;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Funcionario}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FuncionarioDetailsDto extends FuncionarioDto implements Serializable {
    @NotBlank(message = "O nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "O NIF não pode estar vazio")
    @Size(min = 9, max = 9, message = "O NIF deve ter exatamente 9 dígitos")
    @Pattern(regexp = "\\d{9}", message = "O NIF deve conter apenas números")
    private String nif;

    @NotBlank(message = "O email não pode estar vazio")
    @Email(message = "O email deve ser válido")
    private String email;

    @NotBlank(message = "O campo telefone não pode estar vazio")
    @Pattern(regexp = "\\d+", message = "O telefone deve conter apenas números")
    @Size(min = 9, max = 15, message = "O campo telefone deve ter entre 9 e 15 dígitos")
    private String telefone;

    @NotBlank(message = "O cargo não pode estar vazio")
    private String cargo;

    @NotNull(message = "O estado não pode ser nulo")
    private String estado;

    public FuncionarioDetailsDto(Integer id, String nome, String nif, String email, String telefone, String cargo, String estado) {
        super(id);
        this.nome = nome;
        this.nif = nif;
        this.email = email;
        this.telefone = telefone;
        this.cargo = cargo;
        this.estado = estado != null ? EstadoFuncionario.ATIVO.name() : null;
    }
}