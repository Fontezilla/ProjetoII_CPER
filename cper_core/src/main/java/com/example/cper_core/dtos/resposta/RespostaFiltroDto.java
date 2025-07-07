package com.example.cper_core.dtos.resposta;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class RespostaFiltroDto {

    private Integer id;

    private String codigo;

    private String resposta;

    private OffsetDateTime dataRespostaInicio;
    private OffsetDateTime dataRespostaFim;

    private Boolean isCliente;

    private Boolean isDeleted = false;

    private Set<Integer> idsTicket;
    private Set<Integer> idsFuncionario;
    private Set<Integer> idsCliente;
}