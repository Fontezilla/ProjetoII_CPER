package com.example.cper_core.dtos.anomalia;

import com.example.cper_core.enums.EstadoAnomalia;
import com.example.cper_core.enums.Prioridade;
import com.example.cper_core.enums.TipoAnomalia;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class AnomaliaFiltroDto {

    private Integer id;

    private String codigo;

    private TipoAnomalia tipoAnomalia;

    private String titulo;

    private String descricao;

    private OffsetDateTime dataIdentificacaoInicio;

    private OffsetDateTime dataIdentificacaoFim;

    private String localizacao;

    private Prioridade gravidade;

    private EstadoAnomalia estado;

    private Boolean isDeleted = false;

    private Set<Integer> idsCentroProducao;
    private Set<Integer> idsFuncionario;
}