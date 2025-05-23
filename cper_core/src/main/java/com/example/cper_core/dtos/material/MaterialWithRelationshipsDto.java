package com.example.cper_core.dtos.material;

import com.example.cper_core.dtos.lote.LoteDto;
import com.example.cper_core.dtos.material_pedido_material.MaterialPedidoMaterialDto;
import com.example.cper_core.dtos.material_solicitacao_material.MaterialSolicitacaoMaterialDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MaterialWithRelationshipsDto extends MaterialDto {

    @Valid
    private Set<MaterialSolicitacaoMaterialDto> materiaisSolicitacaoMateriais;

    @Valid
    private Set<MaterialPedidoMaterialDto> materiaisPedidoMateriais;

    @Valid
    private Set<LoteDto> lotes;
}
