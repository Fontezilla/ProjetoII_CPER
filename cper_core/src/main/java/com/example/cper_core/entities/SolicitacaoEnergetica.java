package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"solicitacao energetica\"")
public class SolicitacaoEnergetica {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitacao_energetica_id_gen")
    @SequenceGenerator(name = "solicitacao_energetica_id_gen", sequenceName = "solicitacao energetica_id_solicitacao_seq", allocationSize = 1)
    @Column(name = "id_solicitacao", nullable = false)
    private Integer id;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data_solicitacao", nullable = false)
    private LocalDate dataSolicitacao;

    @Column(name = "tipo_energia")
    private Integer tipoEnergia;

    @ColumnDefault("0")
    @Column(name = "qtd_solicitada", nullable = false, precision = 20)
    private BigDecimal qtdSolicitada;

    @Column(name = "prazo_entrega")
    private LocalDate prazoEntrega;

    @ColumnDefault("1")
    @Column(name = "prioridade", nullable = false)
    private Integer prioridade;

    @ColumnDefault("1")
    @Column(name = "estado", nullable = false)
    private Integer estado;

    // Relationships

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato", unique = true)
    private Contrato contrato;

    @OneToMany(mappedBy = "solicitacaoEnergetica")
    private Set<Comentario> comentarios = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "funcionario_solicitacao",
            joinColumns = @JoinColumn(name = "id_solicitacao"),
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

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Integer getTipoEnergia() {
        return tipoEnergia;
    }

    public void setTipoEnergia(Integer tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }

    public BigDecimal getQtdSolicitada() {
        return qtdSolicitada;
    }

    public void setQtdSolicitada(BigDecimal qtdSolicitada) {
        this.qtdSolicitada = qtdSolicitada;
    }

    public LocalDate getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(LocalDate prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
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

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}