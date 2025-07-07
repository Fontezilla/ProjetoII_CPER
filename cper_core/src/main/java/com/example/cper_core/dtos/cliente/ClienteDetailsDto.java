package com.example.cper_core.dtos.cliente;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.OnUpdate;
import com.example.cper_core.enums.EstadoCliente;
import com.example.cper_core.enums.TipoCliente;
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
public class ClienteDetailsDto extends ClienteDto {

    private String codigo;

    @NotNull(groups = OnCreate.class, message = "O tipo de cliente é obrigatório")
    private TipoCliente tipoCliente;

    @NotBlank(groups = OnCreate.class, message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres", groups = {OnCreate.class, OnUpdate.class})
    private String nome;

    @NotBlank(groups = OnCreate.class, message = "O NIF é obrigatório")
    @Pattern(regexp = "\\d{9}", message = "O NIF deve ter exatamente 9 dígitos", groups = {OnCreate.class, OnUpdate.class})
    private String nif;

    @NotBlank(groups = OnCreate.class, message = "O e-mail é obrigatório")
    @Email(message = "O e-mail deve ser válido", groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @NotBlank(groups = OnCreate.class, message = "A password é obrigatória")
    @ValidPassword(groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @NotBlank(groups = OnCreate.class, message = "O telefone é obrigatório")
    @Pattern(regexp = "\\d{9}", message = "O telefone deve ter exatamente 9 dígitos", groups = {OnCreate.class, OnUpdate.class})
    private String telefone;

    @NotNull(groups = OnCreate.class, message = "O estado é obrigatório")
    private EstadoCliente estado;
}
