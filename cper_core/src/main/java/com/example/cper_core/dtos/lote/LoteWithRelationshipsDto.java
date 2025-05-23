package com.example.cper_core.dtos.lote;

import com.example.cper_core.dtos.armazem_lote.ArmazemLoteDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LoteWithRelationshipsDto extends LoteDto {

    @Valid
    private Set<ArmazemLoteDto> armazemLotes;
}