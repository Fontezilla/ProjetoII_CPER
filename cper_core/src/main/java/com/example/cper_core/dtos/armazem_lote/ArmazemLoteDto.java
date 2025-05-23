package com.example.cper_core.dtos.armazem_lote;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.OnUpdate;
import com.example.cper_core.entities.ArmazemLoteId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArmazemLoteDto implements Serializable {

    @NotNull(groups = OnUpdate.class, message = "O identificador composto é obrigatório")
    @Valid
    private ArmazemLoteId id;
}

