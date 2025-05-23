package com.example.cper_core.dtos.armazem;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.enums.EstadoArmazem;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArmazemDetailsDto extends ArmazemDto {


    @NotBlank(groups = OnCreate.class, message = "O nome do armazém e obrigatório")
    @Size(max = 255, message = "O nome do armazém deve ter no máximo 255 caracteres", groups = OnCreate.class)
    private String nome;

    @NotBlank(groups = OnCreate.class, message = "A capacidade total e obrigatoria")
    @DecimalMin(value = "0.0", inclusive = true, message = "A capacidade total não pode ser negativa", groups = OnCreate.class)
    private BigDecimal capacidadeTotal;

    private BigDecimal capacidadeOcupada;

    @NotNull(groups = OnCreate.class, message = "O estado do armazem e obrigatorio")
    private EstadoArmazem estado;
}

