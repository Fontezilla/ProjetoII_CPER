package com.example.cper_core.dtos.cliente;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
public class ClienteFiltroDto {

    private Integer id;
    private String nome;
    private String nif;
    private String email;
    private String telefone;
    private LocalDate dataNascimentoInicio;
    private LocalDate dataNascimentoFim;
    private LocalDate dataCadastroInicio;
    private LocalDate dataCadastroFim;
    private BigDecimal demandaContratadaMin;
    private BigDecimal demandaContratadaMax;
    private BigDecimal consumoMedioMin;
    private BigDecimal consumoMedioMax;

    // Relationships

    private Set<Integer> catConsumo;
    private Set<Integer> tipoEnergia;
    private Set<Integer> estado;
    private Set<Integer> idEndereco;
    private Set<Integer> nPorta;
}
