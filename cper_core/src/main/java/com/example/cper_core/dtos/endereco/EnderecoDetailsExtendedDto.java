package com.example.cper_core.dtos.endereco;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.localidade.LocalidadeDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EnderecoDetailsExtendedDto extends EnderecoDetailsDto {

    @NotNull(groups = OnCreate.class, message = "A localidade é obrigatória")
    @Valid
    private LocalidadeDto localidade;
}
