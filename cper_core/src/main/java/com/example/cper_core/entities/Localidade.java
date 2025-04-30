package com.example.cper_core.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "localidade")
public class Localidade {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "localidade_id_gen")
    @SequenceGenerator(name = "localidade_id_gen", sequenceName = "localidade_id_localidade_seq", allocationSize = 1)
    @Column(name = "id_localidade", nullable = false)
    private Integer id;

    @Column(name = "pais", length = 256)
    private String pais;

    @Column(name = "distrito", length = 256)
    private String distrito;

    @Column(name = "localidade", length = 256)
    private String localidade;

    @Column(name = "codigo_postal", length = 256)
    private String codigoPostal;

    // Relationships

    @OneToMany(mappedBy = "localidade")
    private Set<Endereco> enderecos = new LinkedHashSet<>();

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}