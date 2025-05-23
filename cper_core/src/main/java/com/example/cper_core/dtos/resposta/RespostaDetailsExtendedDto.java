package com.example.cper_core.dtos.resposta;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.cliente.ClienteDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.ticket.TicketDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RespostaDetailsExtendedDto extends RespostaDetailsDto {

    @Valid
    private TicketDto ticket;

    @Valid
    private FuncionarioDto funcionario;

    @Valid
    private ClienteDto cliente;

    @NotNull(groups = OnCreate.class, message = "O campo isCliente é obrigatório")
    private Boolean isCliente;

    private Boolean isDeleted;
}