package com.example.cper_core.dtos.endereco;

import com.example.cper_core.dtos.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EnderecoDetailsDto extends EnderecoDto {

    @NotBlank(groups = OnCreate.class, message = "A rua é obrigatória")
    @Size(max = 255, groups = OnCreate.class, message = "A rua não pode ter mais de 255 caracteres")
    private String rua;
}
