package com.example.cper_core.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "endereco")
public class Endereco {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_id_gen")
    @SequenceGenerator(name = "endereco_id_gen", sequenceName = "endereco_id_endereco_seq", allocationSize = 1)
    @Column(name = "id_endereco", nullable = false)
    private Integer id;

    @Column(name = "rua", nullable = false, length = 256)
    private String rua;

    // Relationships

    @OneToMany(mappedBy = "endereco")
    private Set<Cliente> clientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "endereco")
    private Set<Funcionario> funcionarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "endereco")
    private Set<Armazem> armazens = new LinkedHashSet<>();

    @OneToMany(mappedBy = "endereco")
    private Set<CentroProducao> centroProducaos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "endereco")
    private Set<Contrato> contratoes = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_localidade")
    private Localidade localidade;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public void setLocalidade(Localidade idLocalidade) {
        this.localidade = localidade;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Set<Armazem> getArmazems() {
        return armazens;
    }

    public void setArmazems(Set<Armazem> armazens) {
        this.armazens = armazens;
    }

    public Set<CentroProducao> getCentroProducaos() {
        return centroProducaos;
    }

    public void setCentroProducaos(Set<CentroProducao> centroProducaos) {
        this.centroProducaos = centroProducaos;
    }

    public Set<Contrato> getContratoes() {
        return contratoes;
    }

    public void setContratoes(Set<Contrato> contratoes) {
        this.contratoes = contratoes;
    }
}