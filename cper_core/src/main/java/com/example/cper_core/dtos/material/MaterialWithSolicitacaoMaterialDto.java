package com.example.cper_core.dtos.material;

import com.example.cper_core.entities.Material;
import com.example.cper_core.entities.MaterialSolicitacaoMaterial;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Material}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialWithSolicitacaoMaterialDto extends MaterialDto implements Serializable {
    private Set<MaterialSolicitacaoMaterial> materialSolicitacaomateriais;

    public MaterialWithSolicitacaoMaterialDto(Integer id, Set<MaterialSolicitacaoMaterial> materialSolicitacaomateriais) {
        super(id);
        this.materialSolicitacaomateriais = materialSolicitacaomateriais;
    }
}