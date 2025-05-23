package com.example.cper_core.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
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
@Table(name = "\"pedido_material\"")
@ToString(onlyExplicitlyIncluded = true)
public class PedidoMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido__id_gen")
    @SequenceGenerator(name = "pedido__id_gen", sequenceName = "pedido_material_id_pedido_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_pedido", nullable = false)
    private Integer id;

    @Column(name = "data_criacao", nullable = false)
    @ColumnDefault("CURRENT_DATE")
    private OffsetDateTime dataCriacao;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "prioridade")
    private Integer prioridade;

    @Column(name = "estado")
    private Integer estado;

    @ColumnDefault("false")
    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    // Relationships

    @OneToMany(mappedBy = "pedidoMaterial", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<MaterialPedidoMaterial> materialPedidoMateriais = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "avaria_pedidomaterial",
            joinColumns = @JoinColumn(name = "id_pedido"),
            inverseJoinColumns = @JoinColumn(name = "id_avaria")
    )
    @Builder.Default
    private Set<Avaria> avarias = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Class<?> thisClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : getClass();

        Class<?> otherClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();

        if (!thisClass.equals(otherClass)) return false;
        PedidoMaterial that = (PedidoMaterial) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
