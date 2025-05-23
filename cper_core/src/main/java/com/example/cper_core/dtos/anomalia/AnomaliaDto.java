package com.example.cper_core.dtos.anomalia;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnomaliaDto implements Serializable {
    @NotNull(groups = OnUpdate.class, message = "O ID da anomalia não pode ser nulo")
    private Integer id;
}