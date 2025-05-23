package com.example.cper_core.dtos.nota;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotaDto implements Serializable {
    @NotNull(groups = OnUpdate.class, message = "O id da nota é obrigatório")
    private Integer id;
}