package com.example.cper_core.dtos.ticket;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto implements Serializable {
    @NotNull(groups = OnUpdate.class, message = "O id do ticket é obrigatório")
    private Integer id;
}