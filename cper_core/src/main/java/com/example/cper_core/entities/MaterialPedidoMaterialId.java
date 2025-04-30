package com.example.cper_core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MaterialPedidoMaterialId implements Serializable {
    private static final long serialVersionUID = -1353344903709778511L;
    @Column(name = "id_pedido", nullable = false)
    private Integer idPedido;

    @Column(name = "id_material", nullable = false)
    private Integer idMaterial;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MaterialPedidoMaterialId entity = (MaterialPedidoMaterialId) o;
        return Objects.equals(this.idMaterial, entity.idMaterial) &&
                Objects.equals(this.idPedido, entity.idPedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMaterial, idPedido);
    }

}