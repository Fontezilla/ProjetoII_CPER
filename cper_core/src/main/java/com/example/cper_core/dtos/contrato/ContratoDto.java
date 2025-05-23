package com.example.cper_core.dtos.contrato;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratoDto implements Serializable {
    @NotNull(groups = OnUpdate.class, message = "O ID do contrato é obrigatório")
    private Integer id;
}
