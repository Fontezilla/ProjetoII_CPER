package com.example.cper_core.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

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
@Table(name = "anomalia")
@ToString(onlyExplicitlyIncluded = true)
public class Anomalia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "anomalia_id_gen")
    @SequenceGenerator(name = "anomalia_id_gen", sequenceName = "anomalia_id_anomalia_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_anomalia", nullable = false)
    private Integer id;

    @Column(name = "tipo_anomalia")
    private Integer tipoAnomalia;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao", length = Integer.MAX_VALUE)
    private String descricao;

    @Column(name = "data_identificacao")
    private OffsetDateTime dataIdentificacao;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "gravidade")
    private Integer gravidade;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    // --- Relationships ---

    @OneToOne(mappedBy = "anomalia")
    private Inspecao inspecao;

    @OneToMany(mappedBy = "anomalia")
    @Builder.Default
    private Set<Nota> notas = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_centro")
    private CentroProducao centroProducao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Anomalia anomalia = (Anomalia) o;
        return getId() != null && Objects.equals(getId(), anomalia.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}