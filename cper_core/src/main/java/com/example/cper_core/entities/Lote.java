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
@Table(name = "lote")
@ToString(onlyExplicitlyIncluded = true)
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lote_id_gen")
    @SequenceGenerator(name = "lote_id_gen", sequenceName = "lote_id_lote_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_lote", nullable = false)
    private Integer id;

    @Column(name = "codigo_lote", nullable = false, length = 100)
    private String codigoLote;

    @Column(name = "data_chegada")
    private OffsetDateTime dataChegada;

    @Column(name = "data_validade")
    private OffsetDateTime dataValidade;

    @Column(name = "quantidade_total", nullable = false, precision = 20, scale = 2)
    private BigDecimal quantidadeTotal;

    @Column(name = "quantidade_disponivel", nullable = false, precision = 20, scale = 2)
    private BigDecimal quantidadeDisponivel;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_material", nullable = false)
    private Material material;

    @OneToMany(mappedBy = "lote")
    @Builder.Default
    private Set<ArmazemLote> armazemLotes = new LinkedHashSet<>();

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
        Lote lote = (Lote) o;
        return getId() != null && Objects.equals(getId(), lote.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
