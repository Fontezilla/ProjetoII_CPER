package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "equipa")
public class Equipa {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipa_id_gen")
    @SequenceGenerator(name = "equipa_id_gen", sequenceName = "equipa_id_equipa_seq", allocationSize = 1)
    @Column(name = "id_equipa", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 256)
    private String nome;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "area_atuacao", length = 256)
    private String areaAtuacao;

    // Relationships

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToMany
    @JoinTable(
            name = "funcionario_equipa",
            joinColumns = @JoinColumn(name = "id_equipa"),
            inverseJoinColumns = @JoinColumn(name = "id_funcionario")
    )
    private Set<Funcionario> funcionarios = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "equipa_avaria",
            joinColumns = @JoinColumn(name = "id_equipa"),
            inverseJoinColumns = @JoinColumn(name = "id_avaria")
    )
    private Set<Avaria> avarias = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "equipa_inspecao",
            joinColumns = @JoinColumn(name = "id_equipa"),
            inverseJoinColumns = @JoinColumn(name = "id_inspecao")
    )
    private Set<Inspecao> inspecoes = new LinkedHashSet<>();

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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public Funcionario getIdFuncionario() {
        return funcionario;
    }

    public void setIdFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Departamento getIdDepartamento() {
        return departamento;
    }

    public void setIdDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Set<Avaria> getAvarias() {
        return avarias;
    }

    public void setAvarias(Set<Avaria> avarias) {
        this.avarias = avarias;
    }

    public Set<Inspecao> getInspecoes() {
        return inspecoes;
    }

    public void setInspecoes(Set<Inspecao> inspecoes) {
        this.inspecoes = inspecoes;
    }
}