package com.example.cper_core.dtos.endereco;

import com.example.cper_core.dtos.localidade.LocalidadeDto;
import com.example.cper_core.entities.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Endereco}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class EnderecoDetailsDto extends EnderecoDto implements Serializable {
    @NotBlank(message = "O campo rua não pode estar vazio")
    private String rua;

    @NotNull(message = "O campo localidade não pode ser nulo")
    private LocalidadeDto localidade;

    public EnderecoDetailsDto(Integer id, String rua, LocalidadeDto localidade) {
        super(id);
        this.rua = rua;
        this.localidade = localidade;
    }
}