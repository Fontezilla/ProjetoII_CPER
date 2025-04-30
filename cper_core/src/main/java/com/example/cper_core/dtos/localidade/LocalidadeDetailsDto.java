package com.example.cper_core.dtos.localidade;

import com.example.cper_core.entities.Localidade;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * DTO for {@link Localidade}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class LocalidadeDetailsDto extends LocalidadeDto implements Serializable {
    @NotBlank(message = "O país não pode estar vazio")
    private String pais;

    @NotBlank(message = "O distrito não pode estar vazio")
    private String distrito;

    @NotBlank(message = "A localidade não pode estar vazia")
    private String localidade;

    @NotBlank(message = "O código postal não pode estar vazio")
    private String codigoPostal;

    public LocalidadeDetailsDto(Integer id, String pais, String distrito, String localidade, String codigoPostal) {
        super(id);
        this.pais = pais;
        this.distrito = distrito;
        this.localidade = localidade;
        this.codigoPostal = codigoPostal;
    }
}