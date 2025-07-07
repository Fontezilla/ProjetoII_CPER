package com.example.cper_core.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "fatura")
@ToString(onlyExplicitlyIncluded = true)
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fatura_id_gen")
    @SequenceGenerator(name = "fatura_id_gen", sequenceName = "fatura_id_fatura_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_fatura", nullable = false)
    private Integer id;

    @Column(name = "data_emissao", nullable = false)
    private OffsetDateTime dataEmissao;

    @Column(name = "data_vencimento", nullable = false)
    private OffsetDateTime dataVencimento;

    @Column(name = "v_multa", precision = 20, scale = 2)
    private BigDecimal vMulta;

    @Column(name = "v_electricidade", nullable = false, precision = 20, scale = 2)
    private BigDecimal vElectricidade;

    @Column(name = "qtd_energia", precision = 20, scale = 2)
    private BigDecimal qtdEnergia;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "taxa")
    private Integer taxa;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato")
    private Contrato contrato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

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
        Fatura fatura = (Fatura) o;
        return getId() != null && getId().equals(fatura.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
