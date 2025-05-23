package com.example.cper_core.dtos.endereco;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDto implements Serializable {
    @NotNull(groups = OnUpdate.class, message = "O id do endereço é obrigatório")
    private Integer id;
}

