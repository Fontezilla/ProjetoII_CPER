package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.OnUpdate;
import com.example.cper_core.enums.EstadoFuncionario;
import com.example.cper_core.validation.ValidPassword;
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

    private String codigo;

    @NotBlank(groups = OnCreate.class, message = "O nome é obrigatório")
    private String nome;

    @NotBlank(groups = OnCreate.class, message = "O NIF é obrigatório")
    @Pattern(regexp = "\\d{9}", message = "O NIF deve ter 9 dígitos numéricos", groups = {OnCreate.class, OnUpdate.class})
    private String nif;

    @NotBlank(groups = OnCreate.class, message = "O email é obrigatório")
    @Email(message = "O email deve ser válido", groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @NotBlank(groups = OnCreate.class, message = "A password é obrigatória")
    @ValidPassword(groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @NotBlank(groups = OnCreate.class, message = "O telefone é obrigatório")
    @Pattern(regexp = "\\d{9}", message = "O telefone deve ter 9 dígitos numéricos", groups = {OnCreate.class, OnUpdate.class})
    private String telefone;

    private String cargo;

    @NotNull(groups = OnCreate.class, message = "O estado é obrigatório")
    private EstadoFuncionario estado;
}