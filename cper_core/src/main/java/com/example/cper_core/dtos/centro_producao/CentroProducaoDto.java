package com.example.cper_core.dtos.centro_producao;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CentroProducaoDto implements Serializable {
    @NotNull(groups = OnUpdate.class, message = "O ID do centro de produção é obrigatório")
    private Integer id;
}