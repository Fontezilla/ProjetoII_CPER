package com.example.cper_core.dtos.resposta;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RespostaFiltroDto {

    private Integer id;
    private String resposta;
    private LocalDate dataRespostaInicio;
    private LocalDate dataRespostaFim;
    private Integer idTicket;
    private Integer idFuncionario;
    private Integer idCliente;
}

