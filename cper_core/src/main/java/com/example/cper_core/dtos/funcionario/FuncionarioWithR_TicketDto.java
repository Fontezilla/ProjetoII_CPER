package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.ticket.TicketDto;
import com.example.cper_core.entities.Funcionario;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Funcionario}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class FuncionarioWithR_TicketDto extends FuncionarioDto implements Serializable {
    private Set<TicketDto> responsavelTickets;

    public FuncionarioWithR_TicketDto(Integer id, Set<TicketDto> responsavelTickets) {
        super(id);
        this.responsavelTickets = responsavelTickets;
    }
}