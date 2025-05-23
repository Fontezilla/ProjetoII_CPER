package com.example.cper_core.dtos.ticket;

import com.example.cper_core.dtos.cliente.ClienteDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.resposta.RespostaDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TicketDetailsExtendedDto extends TicketDetailsDto {

    private OffsetDateTime dataFim;

    private String comentario;

    private Boolean isClosed;


    @Valid
    private FuncionarioDto funcionario;

    @Valid
    private ClienteDto cliente;

    private Boolean isDeleted;
}
