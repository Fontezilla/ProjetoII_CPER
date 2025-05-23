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
public class MaterialPedidoMaterialId implements Serializable {

    private Integer idPedido;
    private Integer idMaterial;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaterialPedidoMaterialId that)) return false;
        return Objects.equals(idPedido, that.idPedido) &&
                Objects.equals(idMaterial, that.idMaterial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido, idMaterial);
    }
}