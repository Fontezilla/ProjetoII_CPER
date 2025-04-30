package com.example.cper_core.dtos.ticket;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TicketFiltroDto {

    private Integer id;
    private LocalDate dataIniInicio;
    private LocalDate dataIniFim;
    private LocalDate dataFimInicio;
    private LocalDate dataFimFim;
    private String descricao;
    private String comentario;
    private Integer tipoTicket;
    private Integer prioridade;
    private Integer estado;
    private Integer idFuncionario;
    private Integer idCliente;
}

