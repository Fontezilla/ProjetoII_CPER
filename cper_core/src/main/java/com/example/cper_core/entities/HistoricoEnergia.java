package com.example.cper_core.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "historico_energia",
        uniqueConstraints = @UniqueConstraint(name = "historico_energia_data_id_pedido_key", columnNames = {"data", "id_pedido"})
)
@ToString(onlyExplicitlyIncluded = true)
public class HistoricoEnergia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historico_energia_id_gen")
    @SequenceGenerator(name = "historico_energia_id_gen", sequenceName = "historico_energia_id_historico_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_historico", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "data", nullable = false)
    private OffsetDateTime data;

    @NotNull
    @Column(name = "energia_gerada", nullable = false, precision = 12, scale = 4)
    private BigDecimal energiaGerada;

    @NotNull
    @Column(name = "energia_hora", nullable = false, precision = 12, scale = 4)
    private BigDecimal energiaHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", nullable = false)
    private PedidoGeracao pedidoGeracao;

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
        HistoricoEnergia that = (HistoricoEnergia) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}