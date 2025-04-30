package com.example.cper_core.dtos.cliente;

import com.example.cper_core.entities.Cliente;
import com.example.cper_core.enums.TipoEnergiaRenovavel;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Cliente}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClienteDetailsDto extends ClienteDto implements Serializable {
    @NotBlank(message = "O campo nome não pode estar vazio")
    @Size(max = 256, message = "O campo nome deve conter no máximo 256 caracteres")
    private String nome;

    @NotBlank(message = "O campo NIF não pode estar vazio")
    @Size(min = 9, max = 9, message = "O NIF deve conter exatamente 9 dígitos")
    @Pattern(regexp = "\\d+", message = "O NIF deve conter apenas números")
    private String nif;

    @NotBlank(message = "O campo email não pode estar vazio")
    @Email(message = "O campo email deve ser válido")
    @Size(max = 256, message = "O campo email deve conter no máximo 256 caracteres")
    private String email;

    @NotBlank(message = "O campo telefone não pode estar vazio")
    @Pattern(regexp = "\\d+", message = "O telefone deve conter apenas números")
    @Size(min = 9, max = 15, message = "O campo telefone deve ter entre 9 e 15 dígitos")
    private String telefone;

    @NotBlank(message = "O campo tipo de energia não pode estar vazio")
    private String tipoEnergia;

    public ClienteDetailsDto(Integer id, String nome, String nif, String email, String telefone, String tipoEnergia) {
        super(id);
        this.nome = nome;
        this.nif = nif;
        this.email = email;
        this.telefone = telefone;
        this.tipoEnergia = tipoEnergia != null ? tipoEnergia : TipoEnergiaRenovavel.OUTRA.toString();
    }
}