package com.example.cper_core.dtos.material;

import com.example.cper_core.entities.Material;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Material}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MaterialDetailsDto extends MaterialDto implements Serializable {
    @NotBlank(message = "O nome do material não pode estar vazio")
    private String nome;

    private String descricao;

    @NotBlank(message = "A categoria do material não pode estar vazia")
    private String categoria;

    public MaterialDetailsDto(Integer id, String nome, String descricao, String categoria) {
        super(id);
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
    }
}