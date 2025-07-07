package com.example.cper_core.dtos.cliente;

import com.example.cper_core.enums.EstadoCliente;
import com.example.cper_core.enums.TipoCliente;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class ClienteFiltroDto {

    private Integer id;

    private String codigo;

    private String nome;
    private String nif;
    private String email;
    private String telefone;

    private TipoCliente tipoCliente;
    private EstadoCliente estado;

    private OffsetDateTime dataCadastroInicio;
    private OffsetDateTime dataCadastroFim;

    private BigDecimal consumoMedioMin;
    private BigDecimal consumoMedioMax;

    private BigDecimal demandaContratadaMin;
    private BigDecimal demandaContratadaMax;

    private Boolean isDeleted = false;

    private Set<Integer> idsEndereco;
}
