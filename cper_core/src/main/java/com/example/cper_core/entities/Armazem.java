package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "armazem")
public class Armazem {

    // -------- Attributes --------

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "armazem_id_gen")
    @SequenceGenerator(name = "armazem_id_gen", sequenceName = "armazem_id_armazem_seq", allocationSize = 1)
    @Column(name = "id_armazem", nullable = false)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_criacao")
    @ColumnDefault("CURRENT_DATE")
    private LocalDate dataCriacao;

    @Column(name = "data_update")
    private LocalDate dataUpdate;

    @Column(name = "capacidade_total")
    @ColumnDefault("0")
    private Integer capacidadeTotal;

    @Column(name = "capacidade_ocupada")
    private Integer capacidadeOcupada;

    @Column(name = "n_porta")
    private Integer nPorta;

    @Column(name = "estado")
    private Integer estado;

    // -------- Relationships --------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario responsavel;

    @ManyToMany
    @JoinTable(
            name = "funcionario_armazem",
            joinColumns = @JoinColumn(name = "id_armazem"),
            inverseJoinColumns = @JoinColumn(name = "id_funcionario")
    )
    private Set<Funcionario> funcionarios = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "armazem_stock",
            joinColumns = @JoinColumn(name = "id_armazem"),
            inverseJoinColumns = @JoinColumn(name = "id_stock")
    )
    private Set<Stock> stocks = new LinkedHashSet<>();

    // -------- Getters and Setters --------

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

    public LocalDate getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(LocalDate dataUpdate) {
        this.dataUpdate = dataUpdate;
    }

    public Integer getCapacidadeTotal() {
        return capacidadeTotal;
    }

    public void setCapacidadeTotal(Integer capacidadeTotal) {
        this.capacidadeTotal = capacidadeTotal;
    }

    public Integer getCapacidadeOcupada() {
        return capacidadeOcupada;
    }

    public void setCapacidadeOcupada(Integer capacidadeOcupada) {
        this.capacidadeOcupada = capacidadeOcupada;
    }

    public Integer getNPorta() {
        return nPorta;
    }

    public void setNPorta(Integer nPorta) {
        this.nPorta = nPorta;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }
}
