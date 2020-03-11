package com.jm.projet.filrouge.mapper;

import com.jm.projet.filrouge.common.web.EntityDTOMapper;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.model.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * A basic Mapstruct mapper used to do the mapping between {@link Region} and {@link RegionDTO}.
 */

@Mapper(componentModel = "spring")
public interface RegionMapper extends EntityDTOMapper<Region, RegionDTO> {

    RegionMapper MAPPER = Mappers.getMapper(RegionMapper.class);

    @Mapping(source = "name",target = "name")
    @Override
    public Region toEntity(RegionDTO dto);

    @Mapping (source = "name",target = "name")
    @Override
    public RegionDTO toDTO(Region entity);

    @Override
    public List<RegionDTO> toListDTO(List<Region> entity);
}
