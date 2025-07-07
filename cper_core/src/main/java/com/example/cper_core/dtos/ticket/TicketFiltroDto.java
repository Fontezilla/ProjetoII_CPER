package com.example.cper_core.dtos.ticket;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class TicketFiltroDto {

    private Integer id;

    private String codigo;

    private OffsetDateTime dataIniInicio;
    private OffsetDateTime dataIniFim;

    private OffsetDateTime dataFimInicio;
    private OffsetDateTime dataFimFim;

    private String descricao;

    private String comentario;

    private Integer tipoTicket;

    private Integer prioridade;

    private Integer estado;

    private Boolean isClosed;

    private Boolean isDeleted = false;

    private Set<Integer> idsFuncionario;
    private Set<Integer> idsCliente;
}