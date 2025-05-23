package com.example.cper_core.dtos.nota;

import com.example.cper_core.dtos.anomalia.AnomaliaDto;
import com.example.cper_core.dtos.avaria.AvariaDto;
import com.example.cper_core.dtos.inspecao.InspecaoDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotaWithRelationshipsDto extends NotaDto {
    // Nada adicionado aqui, apenas para manter a estrutura {futuras extensões}
}