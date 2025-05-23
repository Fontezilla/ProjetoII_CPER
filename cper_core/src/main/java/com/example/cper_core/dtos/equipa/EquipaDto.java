package com.example.cper_core.dtos.equipa;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipaDto implements Serializable {
    @NotNull(groups = OnUpdate.class, message = "O id da equipa é obrigatório")
    private Integer id;
}
