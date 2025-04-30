package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fatura")
public class Fatura {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fatura_id_gen")
    @SequenceGenerator(name = "fatura_id_gen", sequenceName = "fatura_id_fatura_seq", allocationSize = 1)
    @Column(name = "id_fatura", nullable = false)
    private Integer id;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @ColumnDefault("0")
    @Column(name = "v_multa", precision = 20, scale = 2)
    private BigDecimal vMulta;

    @ColumnDefault("0")
    @Column(name = "v_total", precision = 20, scale = 2)
    private BigDecimal vTotal;

    @ColumnDefault("0")
    @Column(name = "qtd_energia", precision = 20, scale = 2)
    private BigDecimal qtdEnergia;

    @Column(name = "estado")
    private Integer estado;

    // Relationships

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato")
    private Contrato contrato;

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

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getVMulta() {
        return vMulta;
    }

    public void setVMulta(BigDecimal vMulta) {
        this.vMulta = vMulta;
    }

    public BigDecimal getVTotal() {
        return vTotal;
    }

    public void setVTotal(BigDecimal vTotal) {
        this.vTotal = vTotal;
    }

    public BigDecimal getQtdEnergia() {
        return qtdEnergia;
    }

    public void setQtdEnergia(BigDecimal qtdEnergia) {
        this.qtdEnergia = qtdEnergia;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

}