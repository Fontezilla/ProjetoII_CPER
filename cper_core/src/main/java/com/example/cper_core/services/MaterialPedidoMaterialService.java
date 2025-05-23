package com.example.cper_core.services;

import com.example.cper_core.dtos.material_pedido_material.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.MaterialPedidoMaterialMapper;
import com.example.cper_core.repositories.MaterialPedidoMaterialRepository;
import com.example.cper_core.repositories.MaterialRepository;
import com.example.cper_core.repositories.PedidoMaterialRepository;
import com.example.cper_core.services.interfaces.IMaterialPedidoMaterialService;
import com.example.cper_core.specifications.MaterialPedidoMaterialSpecification;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MaterialPedidoMaterialService extends AbstractXService<
        MaterialPedidoMaterial,
        MaterialPedidoMaterialDto,
        MaterialPedidoMaterialDetailsDto,
        MaterialPedidoMaterialDetailsDto,
        MaterialPedidoMaterialFiltroDto,
        MaterialPedidoMaterialDto,
        MaterialPedidoMaterialId
        > implements IMaterialPedidoMaterialService {

    private final MaterialPedidoMaterialMapper materialPedidoMaterialMappermapper;
    private final MaterialRepository materialRepository;
    private final PedidoMaterialRepository pedidoMaterialRepository;

    public MaterialPedidoMaterialService(
            MaterialPedidoMaterialRepository repository,
            MaterialPedidoMaterialMapper materialPedidoMaterialMappermapper,
            MaterialRepository materialRepository,
            PedidoMaterialRepository pedidoMaterialRepository,
            Validator validator
    ) {
        super(repository, repository, validator);
        this.materialPedidoMaterialMappermapper = materialPedidoMaterialMappermapper;
        this.materialRepository = materialRepository;
        this.pedidoMaterialRepository = pedidoMaterialRepository;
    }

    @Override
    protected MaterialPedidoMaterial toEntity(MaterialPedidoMaterialDetailsDto dto) {
        MaterialPedidoMaterial entity = materialPedidoMaterialMappermapper.toEntity(dto);

        Integer idMaterial = dto.getId().getIdMaterial();
        Integer idPedido = dto.getId().getIdPedido();

        Material material = materialRepository.findById(idMaterial)
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));
        PedidoMaterial pedido = pedidoMaterialRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido de material não encontrado"));

        entity.setMaterial(material);
        entity.setPedidoMaterial(pedido);

        return entity;
    }

    @Override
    protected void updateEntityFromDto(MaterialPedidoMaterialDetailsDto dto, MaterialPedidoMaterial entity) {
        entity.setQtd(dto.getQtd());
    }

    @Override
    protected MaterialPedidoMaterialDetailsDto toExtendedDto(MaterialPedidoMaterial entity) {
        return materialPedidoMaterialMappermapper.toDetailsDto(entity);
    }

    @Override
    protected MaterialPedidoMaterialDetailsDto toDetailsDto(MaterialPedidoMaterial entity) {
        return materialPedidoMaterialMappermapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<MaterialPedidoMaterial> getSpecificationFromFiltro(MaterialPedidoMaterialFiltroDto filtro) {
        return MaterialPedidoMaterialSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(MaterialPedidoMaterial entity) {
        throw new UnsupportedOperationException("Soft delete não é suportado nesta entidade associativa.");
    }
}