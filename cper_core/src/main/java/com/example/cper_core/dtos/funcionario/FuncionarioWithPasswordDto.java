package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.departamento.DepartamentoDto;
import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.entities.Funcionario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link Funcionario}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class FuncionarioWithPasswordDto extends FuncionarioDetailsExtendedDto implements Serializable {
    @NotBlank(message = "O campo password não pode estar vazio")
    @Size(min = 8, max = 128, message = "A password deve conter entre 8 e 128 caracteres")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z]).*$",
            message = "A password deve conter pelo menos uma letra minúscula e uma letra maiúscula"
    )
    private String password;

    public FuncionarioWithPasswordDto(Integer id, String nome, String nif, String email, String telefone, String cargo, String estado, LocalDate dataNascimento, LocalDate dataContratacao, BigDecimal salario, Integer nPorta, DepartamentoDto departamento, EnderecoDto endereco, String password) {
        super(id, nome, nif, email, telefone, cargo, estado, dataNascimento, dataContratacao, salario, nPorta, departamento, endereco);
        this.password = password;
    }
}