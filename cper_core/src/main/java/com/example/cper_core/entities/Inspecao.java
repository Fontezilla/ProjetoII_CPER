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
@Table(name = "inspecao")
@ToString(onlyExplicitlyIncluded = true)
public class Inspecao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inspecao_id_gen")
    @SequenceGenerator(name = "inspecao_id_gen", sequenceName = "inspecao_id_inspecao_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_inspecao", nullable = false)
    private Integer id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data", nullable = false)
    private OffsetDateTime data;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "area_inspecionada", columnDefinition = "TEXT")
    private String areaInspecionada;

    @Column(name = "resultados", columnDefinition = "TEXT")
    private String resultados;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    // Relações

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anomalia", unique = true)
    private Anomalia anomalia;

    @OneToMany(mappedBy = "inspecao")
    @Builder.Default
    private Set<Nota> notas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "inspecao")
    @Builder.Default
    private Set<Avaria> avarias = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_centro")
    private CentroProducao centroProducao;

    @ManyToMany
    @JoinTable(
            name = "equipa_inspecao",
            joinColumns = @JoinColumn(name = "id_inspecao"),
            inverseJoinColumns = @JoinColumn(name = "id_equipa")
    )
    @Builder.Default
    private Set<Equipa> equipas = new LinkedHashSet<>();

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
        Inspecao inspecao = (Inspecao) o;
        return getId() != null && Objects.equals(getId(), inspecao.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
