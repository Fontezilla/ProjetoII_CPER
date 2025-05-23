package com.example.cper_core.entities;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ArmazemLoteId implements Serializable {

    @NotNull(groups = OnUpdate.class, message = "O ID do armazém é obrigatório")
    private Integer idArmazem;

    @NotNull(groups = OnUpdate.class, message = "O ID do lote é obrigatório")
    private Integer idLote;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArmazemLoteId that)) return false;
        return Objects.equals(idArmazem, that.idArmazem) &&
                Objects.equals(idLote, that.idLote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArmazem, idLote);
    }
}
