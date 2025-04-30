package com.example.cper_core.dtos.solicitacao_energetica;

import com.example.cper_core.dtos.cliente.ClienteDto;
import com.example.cper_core.dtos.comentario.ComentarioDto;
import com.example.cper_core.dtos.contrato.ContratoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.entities.SolicitacaoEnergetica;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * DTO for {@link SolicitacaoEnergetica}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SolicitacaoEnergeticaDetailsExtendedDto extends SolicitacaoEnergeticaDetailsDto implements Serializable {
    private LocalDate prazoEntrega;

    @NotNull(message = "O campo contrato não pode ser nulo")
    private ContratoDto contrato;

    private Set<ComentarioDto> comentarios = new LinkedHashSet<>();

    @NotNull(message = "O campo cliente não pode ser nulo")
    private ClienteDto cliente;

    @NotNull(message = "O campo funcionários não pode ser nulo")
    private Set<FuncionarioDto> funcionarios = new LinkedHashSet<>();

    public SolicitacaoEnergeticaDetailsExtendedDto(Integer id, LocalDate dataSolicitacao, Integer tipoEnergia, BigDecimal qtdSolicitada, String prioridade, String estado, LocalDate prazoEntrega, ContratoDto contrato, Set<ComentarioDto> comentarios, ClienteDto cliente, Set<FuncionarioDto> funcionarios) {
        super(id, dataSolicitacao, tipoEnergia, qtdSolicitada, prioridade, estado);
        this.prazoEntrega = prazoEntrega;
        this.contrato = contrato;
        this.comentarios = comentarios;
        this.cliente = cliente;
        this.funcionarios = funcionarios;
    }
}