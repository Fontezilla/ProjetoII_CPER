package com.example.cper_core.dtos.material_solicitacao_material;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialSolicitacaoMaterialFiltroDto {

    private Integer idMaterial;

    private Integer idSolicitacao;

    private Integer qtdMin;
    private Integer qtdMax;

    private Boolean isDeleted = false;
}