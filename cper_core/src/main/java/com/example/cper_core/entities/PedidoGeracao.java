package com.example.cper_core.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"pedido_geracao\"")
@ToString(onlyExplicitlyIncluded = true)
public class PedidoGeracao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_geracao_id_gen")
    @SequenceGenerator(name = "pedido_geracao_id_gen", sequenceName = "pedido_geracao_id_pedidog_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "\"id_pedidoG\"", nullable = false)
    private Integer id;

    @Column(name = "data_criacao", nullable = false)
    @ColumnDefault("CURRENT_DATE")
    private OffsetDateTime dataCriacao;

    @Column(name = "data_previsao")
    private OffsetDateTime dataPrevisao;

    @Column(name = "qtd_energia", nullable = false, precision = 20, scale = 2)
    @ColumnDefault("0")
    private BigDecimal qtdEnergia;

    @NotNull
    @Column(name = "qtd_energia_h", nullable = false, precision = 20, scale = 2)
    private BigDecimal qtdEnergiaH;

    @ColumnDefault("0")
    @Column(name = "qtd_energia_produzida", precision = 20, scale = 2)
    private BigDecimal qtdEnergiaProduzida;

    @ColumnDefault("0")
    @Column(name = "qtd_energia_produzida_h", precision = 20, scale = 2)
    private BigDecimal qtdEnergiaProduzidaH;

    @Column(name = "tipo_energia")
    private Integer tipoEnergia;

    @Column(name = "prioridade")
    @ColumnDefault("1")
    private Integer prioridade;

    @Column(name = "obs", columnDefinition = "TEXT")
    private String obs;

    @Column(name = "relatorio_final", columnDefinition = "TEXT")
    private String relatorioFinal;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "codigo")
    private String codigo;

    @ColumnDefault("false")
    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    // Relationships

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contrato")
    private Contrato contrato;

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

        Class<?> thisClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : getClass();

        Class<?> otherClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();

        if (!thisClass.equals(otherClass)) return false;
        PedidoGeracao that = (PedidoGeracao) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
