package com.example.cper_core.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MaterialSolicitacaoMaterialId implements Serializable {

    private Integer idMaterial;
    private Integer idSolicitacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaterialSolicitacaoMaterialId that)) return false;
        return Objects.equals(idMaterial, that.idMaterial) &&
                Objects.equals(idSolicitacao, that.idSolicitacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMaterial, idSolicitacao);
    }
}
