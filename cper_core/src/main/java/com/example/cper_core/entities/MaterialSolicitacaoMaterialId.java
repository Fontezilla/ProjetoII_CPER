package com.example.cper_core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MaterialSolicitacaoMaterialId implements Serializable {
    private static final long serialVersionUID = 4911447500389143855L;
    @Column(name = "id_material", nullable = false)
    private Integer idMaterial;

    @Column(name = "id_solicitacao", nullable = false)
    private Integer idSolicitacao;

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Integer getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Integer idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MaterialSolicitacaoMaterialId entity = (MaterialSolicitacaoMaterialId) o;
        return Objects.equals(this.idMaterial, entity.idMaterial) &&
                Objects.equals(this.idSolicitacao, entity.idSolicitacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMaterial, idSolicitacao);
    }

}