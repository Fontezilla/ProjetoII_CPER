package com.example.cper_core.dtos.anomalia;

import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.enums.EstadoAnomalia;
import com.example.cper_core.enums.Prioridade;
import com.example.cper_core.enums.TipoAnomalia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link Anomalia}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AnomaliaDetailsDto extends AnomaliaDto implements Serializable {
    @NotBlank(message = "O tipo de anomalia não pode estar vazio")
    private String tipoAnomalia;

    @NotBlank(message = "A descrição não pode estar vazia")
    @Size(max = 500, message = "A descrição deve conter no máximo 500 caracteres")
    private String descricao;

    @NotNull(message = "A data de identificação não pode ser nula")
    private LocalDate dataIdentificacao;

    @NotBlank(message = "O campo gravidade não pode estar vazio")
    private String gravidade;

    @NotBlank(message = "O estado não pode estar vazio")
    private String estado;

    public AnomaliaDetailsDto(Integer id, String tipoAnomalia, String descricao, LocalDate dataIdentificacao,
                              String gravidade, String estado) {
        super(id);
        this.tipoAnomalia = tipoAnomalia != null ? tipoAnomalia : TipoAnomalia.OUTROS.toString();
        this.descricao = descricao;
        this.dataIdentificacao = dataIdentificacao != null ? dataIdentificacao : LocalDate.now();
        this.gravidade = gravidade != null ? gravidade : Prioridade.NORMAL.toString();
        this.estado = estado != null ? estado : EstadoAnomalia.DETECTADA.toString();
    }
}