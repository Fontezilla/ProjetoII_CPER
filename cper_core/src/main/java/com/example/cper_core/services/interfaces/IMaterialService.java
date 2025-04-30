package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.material.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IMaterialService {

    // Generic CRUD operations

    Page<MaterialDetailsDto> listAll(Pageable pageable);

    Page<MaterialDetailsDto> listFiltered(Pageable pageable, MaterialDto filter);

    Optional<MaterialDetailsExtendedDto> getById(Integer id);

    MaterialDetailsExtendedDto create(MaterialDetailsExtendedDto dto);

    MaterialDetailsExtendedDto update(Integer id, MaterialDetailsExtendedDto dto);

    void softDelete(Integer id);

    // Association operations: Lote

    MaterialWithLoteDto linkLote(Integer idMaterial, Integer idLote);

    MaterialWithLoteDto unlinkLote(Integer idMaterial, Integer idLote);

    // Association operations: PedidoMaterial

    MaterialWithPedidoMaterialDto linkPedidoMaterial(Integer idMaterial, Integer idPedidoMaterial);

    MaterialWithPedidoMaterialDto unlinkPedidoMaterial(Integer idMaterial, Integer idPedidoMaterial);

    // Association operations: SolicitacaoMaterial

    MaterialWithSolicitacaoMaterialDto linkSolicitacaoMaterial(Integer idMaterial, Integer idSolicitacaoMaterial);

    MaterialWithSolicitacaoMaterialDto unlinkSolicitacaoMaterial(Integer idMaterial, Integer idSolicitacaoMaterial);
}
