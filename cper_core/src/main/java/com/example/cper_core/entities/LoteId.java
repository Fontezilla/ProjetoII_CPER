package com.example.cper_core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LoteId implements Serializable {
    private static final long serialVersionUID = -3278727506755421395L;
    @Column(name = "id_stock", nullable = false)
    private Integer idStock;

    @Column(name = "id_material", nullable = false)
    private Integer idMaterial;

    public Integer getIdStock() {
        return idStock;
    }

    public void setIdStock(Integer idStock) {
        this.idStock = idStock;
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
        LoteId entity = (LoteId) o;
        return Objects.equals(this.idStock, entity.idStock) &&
                Objects.equals(this.idMaterial, entity.idMaterial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStock, idMaterial);
    }

}