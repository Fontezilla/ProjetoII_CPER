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
@Table(name = "avaria")
@ToString(onlyExplicitlyIncluded = true)
public class Avaria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "avaria_id_gen")
    @SequenceGenerator(name = "avaria_id_gen", sequenceName = "avaria_id_avaria_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_avaria", nullable = false)
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao", length = Integer.MAX_VALUE)
    private String descricao;

    @Column(name = "data_inicio")
    private OffsetDateTime dataInicio;

    @Column(name = "data_resolucao")
    private OffsetDateTime dataResolucao;

    @Column(name = "gravidade")
    private Integer gravidade;

    @Column(name = "impacto_producao")
    private BigDecimal impactoProducao;

    @Column(name = "impacto_percentual")
    private Integer impactoPercentual;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_centro")
    private CentroProducao centroProducao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inspecao")
    private Inspecao inspecao;

    @OneToMany(mappedBy = "avaria")
    @Builder.Default
    private Set<Nota> notas = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "equipa_avaria",
            joinColumns = @JoinColumn(name = "id_avaria"),
            inverseJoinColumns = @JoinColumn(name = "id_equipa")
    )
    @Builder.Default
    private Set<Equipa> equipas = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "avaria_pedidomaterial",
            joinColumns = @JoinColumn(name = "id_avaria"),
            inverseJoinColumns = @JoinColumn(name = "id_pedido")
    )
    @Builder.Default
    private Set<PedidoMaterial> pedidoMateriais = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Avaria avaria = (Avaria) o;
        return getId() != null && Objects.equals(getId(), avaria.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
