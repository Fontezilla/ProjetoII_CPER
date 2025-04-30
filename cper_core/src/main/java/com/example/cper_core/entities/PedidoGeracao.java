package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "\"pedido geracao\"")
public class PedidoGeracao {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_geracao_id_gen")
    @SequenceGenerator(name = "pedido_geracao_id_gen", sequenceName = "pedido geracao_id_pedidog_seq", allocationSize = 1)
    @Column(name = "\"id_pedidoG\"", nullable = false)
    private Integer id;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "data_previsao")
    private LocalDate dataPrevisao;

    @ColumnDefault("0")
    @Column(name = "qtd_energia", nullable = false, precision = 20, scale = 2)
    private BigDecimal qtdEnergia;

    @Column(name = "tipo_energia")
    private Integer tipoEnergia;

    @ColumnDefault("1")
    @Column(name = "prioridade")
    private Integer prioridade;

    @Column(name = "obs", length = Integer.MAX_VALUE)
    private String obs;

    @Column(name = "relatorio_final", length = Integer.MAX_VALUE)
    private String relatorioFinal;

    @Column(name = "estado")
    private Integer estado;

    // Relationships

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato")
    private Contrato contrato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_centro")
    private CentroProducao centroProducao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataPrevisao() {
        return dataPrevisao;
    }

    public void setDataPrevisao(LocalDate dataPrevisao) {
        this.dataPrevisao = dataPrevisao;
    }

    public BigDecimal getQtdEnergia() {
        return qtdEnergia;
    }

    public void setQtdEnergia(BigDecimal qtdEnergia) {
        this.qtdEnergia = qtdEnergia;
    }

    public Integer getTipoEnergia() {
        return tipoEnergia;
    }

    public void setTipoEnergia(Integer tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getRelatorioFinal() {
        return relatorioFinal;
    }

    public void setRelatorioFinal(String relatorioFinal) {
        this.relatorioFinal = relatorioFinal;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setIdContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Funcionario getIdFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public CentroProducao getCentro() {
        return centroProducao;
    }

    public void setCentro(CentroProducao centroProducao) {
        this.centroProducao = centroProducao;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public CentroProducao getCentroProducao() {
        return centroProducao;
    }

    public void setCentroProducao(CentroProducao centroProducao) {
        this.centroProducao = centroProducao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }
}