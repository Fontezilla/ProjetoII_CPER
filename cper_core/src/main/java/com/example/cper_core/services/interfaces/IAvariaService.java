package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.avaria.*;
import java.util.Set;

public interface IAvariaService extends IXService<
        AvariaDto,
        AvariaDetailsDto,
        AvariaDetailsExtendedDto,
        AvariaFiltroDto,
        AvariaWithRelationshipsDto,
        Integer
        > {

    void linkToEquipas(Integer idAvaria, Set<Integer> idsEquipas);
    void unlinkFromEquipas(Integer idAvaria, Set<Integer> idsEquipas);

    void linkToPedidoMateriais(Integer idAvaria, Set<Integer> idsPedidos);
    void unlinkFromPedidoMateriais(Integer idAvaria, Set<Integer> idsPedidos);
}