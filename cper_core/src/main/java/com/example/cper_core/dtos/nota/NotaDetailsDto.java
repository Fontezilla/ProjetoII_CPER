package com.example.cper_core.dtos.nota;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.enums.Prioridade;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotaDetailsDto extends NotaDto {

    private String codigo;

    @NotNull(groups = OnCreate.class, message = "O título é obrigatório")
    private String titulo;

    @NotNull(groups = OnCreate.class, message = "A data de criação é obrigatória")
    private OffsetDateTime dataCriacao;

    @NotNull(groups = OnCreate.class, message = "A prioridade é obrigatória")
    private Prioridade prioridade;
}