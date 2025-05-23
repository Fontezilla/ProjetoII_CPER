package com.example.cper_core.dtos.localidade;

import com.example.cper_core.dtos.OnCreate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LocalidadeDetailsDto extends LocalidadeDto {

    @NotBlank(groups = OnCreate.class, message = "O nome é obrigatório")
    private String nome;

    @NotBlank(groups = OnCreate.class, message = "O código postal é obrigatório")
    private String codigoPostal;

    @NotBlank(groups = OnCreate.class, message = "O distrito é obrigatório")
    private String distrito;

    @NotBlank(groups = OnCreate.class, message = "O país é obrigatório")
    private String pais;
}