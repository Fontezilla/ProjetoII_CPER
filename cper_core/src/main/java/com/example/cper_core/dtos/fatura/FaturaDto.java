package com.example.cper_core.dtos.fatura;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaturaDto implements Serializable {
    @NotNull(groups = OnUpdate.class, message = "O id da fatura é obrigatório")
    private Integer id;
}

