package com.example.cper_core.dtos.ticket;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.enums.EstadoTicket;
import com.example.cper_core.enums.Prioridade;
import com.example.cper_core.enums.TipoTicket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TicketDetailsDto extends TicketDto {

    private String codigo;

    @NotNull(groups = OnCreate.class, message = "A data de início é obrigatória")
    private OffsetDateTime dataIni;

    @NotBlank(groups = OnCreate.class, message = "A descrição é obrigatória")
    private String descricao;

    @NotNull(groups = OnCreate.class, message = "O tipo de ticket é obrigatório")
    private TipoTicket tipoTicket;

    private Prioridade prioridade;

    @NotNull(groups = OnCreate.class, message = "O estado do ticket é obrigatório")
    private EstadoTicket estado;
}