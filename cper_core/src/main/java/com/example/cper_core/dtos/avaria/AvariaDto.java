package com.example.cper_core.dtos.avaria;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvariaDto implements Serializable {
    @NotNull(groups = OnUpdate.class, message = "O ID da avaria é obrigatória")
    private Integer id;
}
