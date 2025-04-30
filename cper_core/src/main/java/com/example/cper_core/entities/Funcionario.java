package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_id_gen")
    @SequenceGenerator(name = "funcionario_id_gen", sequenceName = "funcionario_id_funcionario_seq", allocationSize = 1)
    @Column(name = "id_funcionario", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 256)
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "nif", length = 15)
    private String nif;

    @Column(name = "email", length = 256)
    private String email;

    @Column(name = "password", length = 256)
    private String password;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "data_contratacao", nullable = false)
    private LocalDate dataContratacao = LocalDate.now();

    @ColumnDefault("0")
    @Column(name = "salario", nullable = false, precision = 20, scale = 2)
    private BigDecimal salario;

    @Column(name = "cargo", length = 256)
    private String cargo;

    @Column(name = "n_porta")
    private Integer nPorta;

    @ColumnDefault("1")
    @Column(name = "estado", nullable = false)
    private Integer estado;

    // Relationships

    @OneToMany(mappedBy = "funcionario")
    private Set<Anomalia> responsavelAnomalias = new LinkedHashSet<>();

    @OneToMany(mappedBy = "responsavel")
    private Set<Armazem> responsavelArmazems = new LinkedHashSet<>();

    @OneToMany(mappedBy = "funcionario")
    private Set<Contrato> responsavelContratos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "funcionario")
    private Set<PedidoGeracao> responsavelPedidoGeracoes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "funcionario")
    private Set<Ticket> responsavelTickets = new LinkedHashSet<>();

    @OneToMany(mappedBy = "funcionario")
    private Set<Equipa> responsavelEquipas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "funcionario")
    private Set<Fatura> responsavelFaturas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "funcionario")
    private Set<Comentario> comentario = new LinkedHashSet<>();

    @OneToMany(mappedBy = "funcionario")
    private Set<Resposta> respostas = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToMany
    @JoinTable(
            name = "funcionario_armazem",
            joinColumns = @JoinColumn(name = "id_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "id_armazem")
    )
    private Set<Armazem> armazens = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "funcionario_centro",
            joinColumns = @JoinColumn(name = "id_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "id_centro")
    )
    private Set<CentroProducao> centroProducoes = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "funcionario_solicitacao",
            joinColumns = @JoinColumn(name = "id_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "id_solicitacao")
    )
    private Set<SolicitacaoEnergetica> solicitacaoEnergeticas = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "funcionario_equipa",
            joinColumns = @JoinColumn(name = "id_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "id_equipa")
    )
    private Set<Equipa> equipas = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "funcionario_departamento",
            joinColumns = @JoinColumn(name = "id_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "id_departamento")
    )
    private Set<Departamento> Departamentos = new LinkedHashSet<>();

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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getNPorta() {
        return nPorta;
    }

    public void setNPorta(Integer nPorta) {
        this.nPorta = nPorta;
    }

    public Endereco getIdEndereco() {
        return endereco;
    }

    public void setIdEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setIdDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getnPorta() {
        return nPorta;
    }

    public void setnPorta(Integer nPorta) {
        this.nPorta = nPorta;
    }

    public Set<Departamento> getDepartamentos() {
        return Departamentos;
    }

    public void setDepartamentos(Set<Departamento> departamentos) {
        Departamentos = departamentos;
    }

    public Set<Equipa> getEquipas() {
        return equipas;
    }

    public void setEquipas(Set<Equipa> equipas) {
        this.equipas = equipas;
    }

    public Set<SolicitacaoEnergetica> getSolicitacaoEnergeticas() {
        return solicitacaoEnergeticas;
    }

    public void setSolicitacaoEnergeticas(Set<SolicitacaoEnergetica> solicitacaoEnergeticas) {
        this.solicitacaoEnergeticas = solicitacaoEnergeticas;
    }

    public Set<CentroProducao> getCentroProducoes() {
        return centroProducoes;
    }

    public void setCentroProducoes(Set<CentroProducao> centroProducoes) {
        this.centroProducoes = centroProducoes;
    }

    public Set<Armazem> getArmazems() {
        return armazens;
    }

    public void setArmazems(Set<Armazem> armazens) {
        this.armazens = armazens;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Set<Fatura> getResponsavelFaturas() {
        return responsavelFaturas;
    }

    public void setResponsavelFaturas(Set<Fatura> responsavelFaturas) {
        this.responsavelFaturas = responsavelFaturas;
    }

    public Set<Equipa> getResponsavelEquipas() {
        return responsavelEquipas;
    }

    public void setResponsavelEquipas(Set<Equipa> responsavelEquipas) {
        this.responsavelEquipas = responsavelEquipas;
    }

    public Set<Ticket> getResponsavelTickets() {
        return responsavelTickets;
    }

    public void setResponsavelTickets(Set<Ticket> responsavelTickets) {
        this.responsavelTickets = responsavelTickets;
    }

    public Set<PedidoGeracao> getResponsavelPedidoGeracoes() {
        return responsavelPedidoGeracoes;
    }

    public void setResponsavelPedidoGeracoes(Set<PedidoGeracao> responsavelPedidoGeracoes) {
        this.responsavelPedidoGeracoes = responsavelPedidoGeracoes;
    }

    public Set<Anomalia> getResponsavelAnomalias() {
        return responsavelAnomalias;
    }

    public void setResponsavelAnomalias(Set<Anomalia> responsavelAnomalias) {
        this.responsavelAnomalias = responsavelAnomalias;
    }

    public Set<Armazem> getResponsavelArmazems() {
        return responsavelArmazems;
    }

    public void setResponsavelArmazems(Set<Armazem> responsavelArmazems) {
        this.responsavelArmazems = responsavelArmazems;
    }

    public Set<Contrato> getResponsavelContratos() {
        return responsavelContratos;
    }

    public void setResponsavelContratos(Set<Contrato> responsavelContratos) {
        this.responsavelContratos = responsavelContratos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}