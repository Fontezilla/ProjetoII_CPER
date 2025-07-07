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
@Table(name = "cliente")
@ToString(onlyExplicitlyIncluded = true)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_id_gen")
    @SequenceGenerator(name = "cliente_id_gen", sequenceName = "cliente_id_cliente_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_cliente", nullable = false)
    private Integer id;

    @Column(name = "demanda_contratada", precision = 20, scale = 2)
    private BigDecimal demandaContratada;

    @Column(name = "tipo_cliente")
    private Integer tipoCliente;

    @Column(name = "nome", length = 256)
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

    @Column(name = "data_cadastro")
    private OffsetDateTime dataCadastro;

    @Column(name = "consumo_medio", precision = 20, scale = 2)
    private BigDecimal consumoMedio;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "n_porta", length = 10, nullable = false)
    private String nPorta;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente")
    @Builder.Default
    private Set<HistoricoConsumo> historicoConsumos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cliente")
    @Builder.Default
    private Set<SolicitacaoEnergetica> solicitacaoEnergeticas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cliente")
    @Builder.Default
    private Set<Ticket> tickets = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cliente")
    @Builder.Default
    private Set<Resposta> respostas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cliente")
    @Builder.Default
    private Set<NotificacaoDestinatario> notificacoes = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Cliente cliente = (Cliente) o;
        return getId() != null && Objects.equals(getId(), cliente.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
