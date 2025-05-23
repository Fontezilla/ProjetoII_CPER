package com.example.cper_core.dtos.inspecao;

import com.example.cper_core.enums.EstadoInspecao;
import com.example.cper_core.enums.TipoInspecao;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class InspecaoFiltroDto {

    private Integer id;

    private String descricao;

    private OffsetDateTime dataInicio;
    private OffsetDateTime dataFim;

    private TipoInspecao tipo;

    private EstadoInspecao estado;

    private String areaInspecionada;
    private String resultados;

    private Boolean isDeleted = false;

    private Set<Integer> idsCentroProducao;
}
