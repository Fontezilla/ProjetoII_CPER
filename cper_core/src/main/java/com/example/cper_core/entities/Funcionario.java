package com.example.cper_core.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "funcionario")
@ToString(onlyExplicitlyIncluded = true)
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_id_gen")
    @SequenceGenerator(name = "funcionario_id_gen", sequenceName = "funcionario_id_funcionario_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_funcionario", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 256)
    private String nome;

    @Column(name = "data_nascimento")
    private OffsetDateTime dataNascimento;

    @Column(name = "nif", length = 15)
    private String nif;

    @Column(name = "email", length = 256)
    private String email;

    @Column(name = "password", length = 256)
    private String password;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "data_contratacao", nullable = false)
    private OffsetDateTime dataContratacao;

    @Column(name = "salario", nullable = false, precision = 20, scale = 2)
    private BigDecimal salario;

    @Column(name = "cargo", length = 256)
    private String cargo;

    @Column(name = "n_porta", length = 10)
    private String nPorta;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @OneToMany(mappedBy = "funcionario")
    @Builder.Default
    private Set<Anomalia> responsavelAnomalias = new LinkedHashSet<>();

    @OneToMany(mappedBy = "funcionario")
    @Builder.Default
    private Set<Contrato> responsavelContratos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "funcionario")
    @Builder.Default
    private Set<PedidoGeracao> responsavelPedidos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "funcionario")
    @Builder.Default
    private Set<Ticket> responsavelTickets = new LinkedHashSet<>();

    @OneToMany(mappedBy = "funcionario")
    @Builder.Default
    private Set<Equipa> responsavelEquipas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "funcionario")
    @Builder.Default
    private Set<Fatura> responsavelFaturas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "funcionario")
    @Builder.Default
    private Set<Resposta> respostas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "responsavel")
    @Builder.Default
    private Set<Armazem> responsavelArmazens = new LinkedHashSet<>();

    @OneToMany(mappedBy = "responsavel")
    @Builder.Default
    private Set<CentroProducao> responsavelCentros = new LinkedHashSet<>();

    @OneToMany(mappedBy = "funcionario")
    @Builder.Default
    private Set<NotificacaoDestinatario> notificacoes = new LinkedHashSet<>();

    // Relações Many-to-Many
    @ManyToMany
    @JoinTable(name = "funcionario_armazem",
            joinColumns = @JoinColumn(name = "id_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "id_armazem"))
    @Builder.Default
    private Set<Armazem> armazens = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "funcionario_centro",
            joinColumns = @JoinColumn(name = "id_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "id_centro"))
    @Builder.Default
    private Set<CentroProducao> centrosProducao = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "funcionario_solicitacao",
            joinColumns = @JoinColumn(name = "id_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "id_solicitacao"))
    @Builder.Default
    private Set<SolicitacaoEnergetica> solicitacoesEnergeticas = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "funcionario_equipa",
            joinColumns = @JoinColumn(name = "id_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "id_equipa"))
    @Builder.Default
    private Set<Equipa> equipas = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "funcionario_departamento",
            joinColumns = @JoinColumn(name = "id_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "id_departamento"))
    @Builder.Default
    private Set<Departamento> departamentos = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Funcionario that = (Funcionario) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}