package com.example.cper_core.dtos.localidade;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalidadeDto implements Serializable {
    @NotNull(groups = OnUpdate.class, message = "O id da localidade não pode ser nulo")
    private Integer id;
}
