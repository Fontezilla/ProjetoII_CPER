package com.example.cper_core.dtos.lote;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoteDto implements Serializable {
    @NotNull(groups = OnUpdate.class, message = "O id do lote não pode ser nulo.")
    private Integer id;
}

