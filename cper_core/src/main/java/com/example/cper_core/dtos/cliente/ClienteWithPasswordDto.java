package com.example.cper_core.dtos.cliente;

import com.example.cper_core.entities.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * DTO for {@link Cliente}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteWithPasswordDto extends ClienteDto implements Serializable {
    @NotBlank(message = "O campo password não pode estar vazio")
    @Size(min = 8, max = 128, message = "A password deve conter entre 8 e 128 caracteres")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z]).*$",
            message = "A password deve conter pelo menos uma letra minúscula e uma letra maiúscula"
    )
    private String password;


    public ClienteWithPasswordDto(Integer id, String password) {
        super(id);
        this.password = password;
    }
}