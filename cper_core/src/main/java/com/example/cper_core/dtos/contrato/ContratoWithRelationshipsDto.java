package com.example.cper_core.dtos.contrato;

import com.example.cper_core.dtos.fatura.FaturaDto;
import com.example.cper_core.dtos.pedido_geracao.PedidoGeracaoDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContratoWithRelationshipsDto extends ContratoDto {

    @Valid
    private Set<FaturaDto> faturas;

    @Valid
    private Set<PedidoGeracaoDto> pedidosGeracao;
}

