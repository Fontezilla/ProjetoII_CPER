package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "departamento")
public class Departamento {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departamento_id_gen")
    @SequenceGenerator(name = "departamento_id_gen", sequenceName = "departamento_id_departamento_seq", allocationSize = 1)
    @Column(name = "id_departamento", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name= "setor")
    private Integer setor;

    @Column(name = "descricao")
    private String descricao;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @ColumnDefault("0")
    @Column(name = "num_funcionarios")
    private Integer numFuncionarios;

    @ColumnDefault("0")
    @Column(name = "orcamento")
    private BigDecimal orcamento;

    // Relationships

    @OneToMany(mappedBy = "departamento")
    private Set<CentroProducao> centrosProducao = new LinkedHashSet<>();

    @OneToMany(mappedBy = "departamento")
    private Set<Equipa> equipas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "departamento")
    private Set<Funcionario> funcionarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "departamento")
    private Set<Armazem> armazens = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "funcionario_departamento",
            joinColumns = @JoinColumn(name = "id_departamento"),
            inverseJoinColumns = @JoinColumn(name = "id_funcionario")
    )
    private Set<Funcionario> responsaveis = new LinkedHashSet<>();

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Integer getNumFuncionarios() {
        return numFuncionarios;
    }

    public void setNumFuncionarios(Integer numFuncionarios) {
        this.numFuncionarios = numFuncionarios;
    }

    public BigDecimal getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(BigDecimal orcamento) {
        this.orcamento = orcamento;
    }

    public Set<CentroProducao> getCentrosProducao() {
        return centrosProducao;
    }

    public void setCentrosProducao(Set<CentroProducao> centrosProducao) {
        this.centrosProducao = centrosProducao;
    }

    public Set<Equipa> getEquipas() {
        return equipas;
    }

    public void setEquipas(Set<Equipa> equipas) {
        this.equipas = equipas;
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

    public void setArmazems(Set<Armazem> armazems) {
        this.armazens = armazems;
    }

    public Set<Funcionario> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(Set<Funcionario> responsaveis) {
        this.responsaveis = responsaveis;
    }

    public Integer getSetor() {
        return setor;
    }

    public void setSetor(Integer setor) {
        this.setor = setor;
    }
}