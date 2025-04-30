package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"centro producao\"")
public class CentroProducao {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "centro_producao_id_gen")
    @SequenceGenerator(name = "centro_producao_id_gen", sequenceName = "centro producao_id_centro_seq", allocationSize = 1)
    @Column(name = "id_centro", nullable = false)
    private Integer id;

    @Column(name = "nome", length = 256)
    private String nome;

    @Column(name = "tipo_energia")
    private Integer tipoEnergia;

    @ColumnDefault("0")
    @Column(name = "capacidade_max", nullable = false, precision = 20, scale = 2)
    private BigDecimal capacidadeMax;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @ColumnDefault("0")
    @Column(name = "custo_operacional", nullable = false, precision = 20, scale = 2)
    private BigDecimal custoOperacional;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "n_porta")
    private Integer nPorta;

    // Relationships

    @OneToMany(mappedBy = "centroProducao")
    private Set<Avaria> avarias = new LinkedHashSet<>();

    @OneToMany(mappedBy = "centroProducao")
    private Set<Inspecao> inspecoes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "centroProducao")
    private Set<Anomalia> anomalias = new LinkedHashSet<>();

    @OneToMany(mappedBy = "centroProducao")
    private Set<PedidoGeracao> pedidosGeracao = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_responsavel")
    private Funcionario responsavel;

    @ManyToMany
    @JoinTable(
            name = "funcionario_centro",
            joinColumns = @JoinColumn(name = "id_centro"),
            inverseJoinColumns = @JoinColumn(name = "id_funcionario")
    )
    private Set<Funcionario> funcionarios = new LinkedHashSet<>();

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

    public Integer getTipoEnergia() {
        return tipoEnergia;
    }

    public void setTipoEnergia(Integer tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }

    public BigDecimal getCapacidadeMax() {
        return capacidadeMax;
    }

    public void setCapacidadeMax(BigDecimal capacidadeMax) {
        this.capacidadeMax = capacidadeMax;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public BigDecimal getCustoOperacional() {
        return custoOperacional;
    }

    public void setCustoOperacional(BigDecimal custoOperacional) {
        this.custoOperacional = custoOperacional;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getNPorta() {
        return nPorta;
    }

    public void setNPorta(Integer nPorta) {
        this.nPorta = nPorta;
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

    public Set<Anomalia> getAnomalias() {
        return anomalias;
    }

    public void setAnomalias(Set<Anomalia> anomalias) {
        this.anomalias = anomalias;
    }

    public Set<PedidoGeracao> getPedidosGeracao() {
        return pedidosGeracao;
    }

    public void setPedidosGeracao(Set<PedidoGeracao> pedidosGeracao) {
        this.pedidosGeracao = pedidosGeracao;
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

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }
}
