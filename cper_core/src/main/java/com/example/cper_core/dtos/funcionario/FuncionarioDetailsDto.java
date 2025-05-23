package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.enums.EstadoFuncionario;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FuncionarioDetailsDto extends FuncionarioDto {

    @NotBlank(groups = OnCreate.class, message = "O nome é obrigatório")
    private String nome;

    @NotBlank(groups = OnCreate.class, message = "O NIF é obrigatório")
    @Pattern(regexp = "\\d{9}", message = "O NIF deve ter 9 dígitos numéricos", groups = OnCreate.class)
    private String nif;

    @NotBlank(groups = OnCreate.class, message = "O email é obrigatório")
    @Email(groups = OnCreate.class, message = "O email deve ser válido")
    private String email;

    @NotBlank(groups = OnCreate.class, message = "A password é obrigatória")
    @Size(min = 6, message = "A password deve ter pelo menos 6 caracteres", groups = OnCreate.class)
    private String password;

    @NotBlank(groups = OnCreate.class, message = "O telefone é obrigatório")
    @Pattern(regexp = "\\d{9}", message = "O telefone deve ter 9 dígitos numéricos", groups = OnCreate.class)
    private String telefone;

    private String cargo;

    @NotNull(groups = OnCreate.class, message = "O estado é obrigatório")
    private EstadoFuncionario estado;
}