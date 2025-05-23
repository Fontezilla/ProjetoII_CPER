package com.example.cper_core.entities;

import jakarta.persistence.*;
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
@Table(name = "historico_consumo")
@ToString(onlyExplicitlyIncluded = true)
public class HistoricoConsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historico_consumo_id_gen")
    @SequenceGenerator(name = "historico_consumo_id_gen", sequenceName = "historico_consumo_id_historico_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_historico", nullable = false)
    private Integer id;

    @Column(name = "data_registo", nullable = false)
    private OffsetDateTime dataRegisto;

    @Column(name = "energia_total", nullable = false, precision = 20, scale = 2)
    private BigDecimal energiaTotal;

    @Column(name = "consumo_por_hora", nullable = false, precision = 20, scale = 2)
    private BigDecimal consumoPorHora;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

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
        HistoricoConsumo that = (HistoricoConsumo) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
