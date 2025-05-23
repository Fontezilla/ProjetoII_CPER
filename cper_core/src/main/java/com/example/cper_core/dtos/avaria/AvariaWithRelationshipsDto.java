package com.example.cper_core.dtos.avaria;

import com.example.cper_core.dtos.equipa.EquipaDto;
import com.example.cper_core.dtos.nota.NotaDto;
import com.example.cper_core.dtos.pedido_material.PedidoMaterialDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AvariaWithRelationshipsDto extends AvariaDto {

    @Valid
    private Set<NotaDto> notas;

    @Valid
    private Set<EquipaDto> equipas;

    @Valid
    private Set<PedidoMaterialDto> pedidoMateriais;
}
