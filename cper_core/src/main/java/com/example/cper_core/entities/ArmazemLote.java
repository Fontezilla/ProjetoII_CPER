package com.example.cper_core.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "armazem_lote")
public class ArmazemLote {

    @EmbeddedId
    @Builder.Default
    private ArmazemLoteId id = new ArmazemLoteId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idArmazem")
    @JoinColumn(name = "id_armazem")
    private Armazem armazem;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idLote")
    @JoinColumn(name = "id_lote")
    private Lote lote;

    @Column(name = "quantidade_armazenada", nullable = false, precision = 20, scale = 2)
    private BigDecimal quantidadeArmazenada;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArmazemLote that = (ArmazemLote) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}
