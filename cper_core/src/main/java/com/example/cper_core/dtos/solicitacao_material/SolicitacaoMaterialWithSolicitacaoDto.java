package com.example.cper_core.dtos.solicitacao_material;

import com.example.cper_core.entities.MaterialSolicitacaoMaterial;
import com.example.cper_core.entities.SolicitacaoMaterial;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link SolicitacaoMaterial}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SolicitacaoMaterialWithSolicitacaoDto extends SolicitacaoMaterialDto implements Serializable {
    private Set<MaterialSolicitacaoMaterial> materialSolicitacaomateriais;

    public SolicitacaoMaterialWithSolicitacaoDto(Integer id, Set<MaterialSolicitacaoMaterial> materialSolicitacaomateriais) {
        super(id);
        this.materialSolicitacaomateriais = materialSolicitacaomateriais;
    }
}