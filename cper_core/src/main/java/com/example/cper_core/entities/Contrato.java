package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "contrato")
public class Contrato {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contrato_id_gen")
    @SequenceGenerator(name = "contrato_id_gen", sequenceName = "contrato_id_contrato_seq", allocationSize = 1)
    @Column(name = "id_contrato", nullable = false)
    private Integer id;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "tipo_contrato")
    private Integer tipoContrato;

    @ColumnDefault("0")
    @Column(name = "qtd_energia", nullable = false, precision = 20, scale = 2)
    private BigDecimal qtdEnergia;

    @Column(name = "prazo_pagamento")
    private Integer prazoPagamento;

    @Column(name = "multa_atraso")
    private Integer multaAtraso;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "n_porta")
    private Integer nPorta;

    // Relationships

    @OneToOne(mappedBy = "contrato")
    private SolicitacaoEnergetica solicitacaoEnergetica;

    @OneToMany(mappedBy = "contrato")
    private Set<Fatura> faturas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "contrato")
    private Set<PedidoGeracao> pedidosGeracao = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(Integer tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public BigDecimal getQtdEnergia() {
        return qtdEnergia;
    }

    public void setQtdEnergia(BigDecimal qtdEnergia) {
        this.qtdEnergia = qtdEnergia;
    }

    public Integer getPrazoPagamento() {
        return prazoPagamento;
    }

    public void setPrazoPagamento(Integer prazoPagamento) {
        this.prazoPagamento = prazoPagamento;
    }

    public Integer getMultaAtraso() {
        return multaAtraso;
    }

    public void setMultaAtraso(Integer multaAtraso) {
        this.multaAtraso = multaAtraso;
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

    public Funcionario getIdFuncionario() {
        return funcionario;
    }

    public void setIdFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public SolicitacaoEnergetica getSolicitacaoEnergetica() {
        return solicitacaoEnergetica;
    }

    public void setSolicitacaoEnergetica(SolicitacaoEnergetica solicitacaoEnergetica) {
        this.solicitacaoEnergetica = solicitacaoEnergetica;
    }

    public Set<Fatura> getFaturas() {
        return faturas;
    }

    public void setFaturas(Set<Fatura> faturas) {
        this.faturas = faturas;
    }

    public Set<PedidoGeracao> getPedidosGeracao() {
        return pedidosGeracao;
    }

    public void setPedidosGeracao(Set<PedidoGeracao> pedidosGeracao) {
        this.pedidosGeracao = pedidosGeracao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}