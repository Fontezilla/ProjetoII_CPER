package com.example.cper_core.dtos.ticket;

import com.example.cper_core.entities.Ticket;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Ticket}
 */

@Data
@NoArgsConstructor
public class TicketDto implements Serializable {
    private Integer id;

    public TicketDto(Integer id) {
        this.id = id;
    }
}