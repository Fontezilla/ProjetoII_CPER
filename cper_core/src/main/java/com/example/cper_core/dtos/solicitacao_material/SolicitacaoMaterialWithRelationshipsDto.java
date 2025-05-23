package com.example.cper_core.dtos.solicitacao_material;

import com.example.cper_core.dtos.material_solicitacao_material.MaterialSolicitacaoMaterialDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SolicitacaoMaterialWithRelationshipsDto extends SolicitacaoMaterialDto {

    @Valid
    private Set<MaterialSolicitacaoMaterialDto> materialSolicitacoes;
}

