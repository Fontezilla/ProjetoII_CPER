package com.example.cper_core.dtos.armazem;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArmazemDto implements Serializable {
    @NotNull(groups = OnUpdate.class, message = "O ID do armazém não pode ser nulo")
    private Integer id;
}