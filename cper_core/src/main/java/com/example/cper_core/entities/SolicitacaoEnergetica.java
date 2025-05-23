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
@Table(name = "solicitacao_energetica")
@ToString(onlyExplicitlyIncluded = true)
public class SolicitacaoEnergetica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitacao_energetica_id_gen")
    @SequenceGenerator(name = "solicitacao_energetica_id_gen", sequenceName = "solicitacao energetica_id_solicitacao_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_solicitacao", nullable = false)
    private Integer id;

    @Column(name = "data_solicitacao")
    private OffsetDateTime dataSolicitacao;

    @Column(name = "tipo_energia", nullable = false)
    private Integer tipoEnergia;

    @Column(name = "qtd_solicitada", nullable = false, precision = 20, scale = 2)
    private BigDecimal qtdSolicitada;

    @Column(name = "qtd_solicitada_h", nullable = false, precision = 20, scale = 2)
    private BigDecimal qtdSolicitadaH;

    @Column(name = "prazo_entrega", nullable = false)
    private OffsetDateTime prazoEntrega;

    @Column(name = "prioridade")
    private Integer prioridade;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato")
    private Contrato contrato;

    @OneToMany(mappedBy = "solicitacaoEnergetica")
    @Builder.Default
    private Set<Nota> notas = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "funcionario_solicitacao",
            joinColumns = @JoinColumn(name = "id_solicitacao"),
            inverseJoinColumns = @JoinColumn(name = "id_funcionario")
    )
    @Builder.Default
    private Set<Funcionario> funcionarios = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SolicitacaoEnergetica that = (SolicitacaoEnergetica) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
