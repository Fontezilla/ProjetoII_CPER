package com.example.cper_core.dtos.nota;

import com.example.cper_core.dtos.anomalia.AnomaliaDto;
import com.example.cper_core.dtos.avaria.AvariaDto;
import com.example.cper_core.dtos.inspecao.InspecaoDto;
import com.example.cper_core.entities.Nota;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Nota}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class NotaDetailsExtendedDto extends NotaDetailsDto implements Serializable {
    @NotNull(message = "A data de criação não pode ser nula")
    private LocalDate dataCriacao;

    private InspecaoDto inspecao;

    private AnomaliaDto anomalia;

    private AvariaDto avaria;

    public NotaDetailsExtendedDto(Integer id, String descricao, String prioridade, String titulo, LocalDate dataCriacao, InspecaoDto inspecao, AnomaliaDto anomalia, AvariaDto avaria) {
        super(id, descricao, prioridade, titulo);
        this.dataCriacao = dataCriacao != null ? dataCriacao : LocalDate.now();
        this.inspecao = inspecao;
        this.anomalia = anomalia;
        this.avaria = avaria;
    }
}