package com.example.cper_core.dtos.material;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDto implements Serializable {
    @NotNull(groups = OnUpdate.class, message = "O id material não pode ser nulo")
    private Integer id;
}
