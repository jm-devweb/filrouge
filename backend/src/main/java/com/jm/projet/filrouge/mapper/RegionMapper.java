package com.jm.projet.filrouge.mapper;

import com.jm.projet.filrouge.common.web.EntityDTOMapper;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.model.Region;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegionMapper extends EntityDTOMapper<Region, RegionDTO> {
    RegionMapper INSTANCE = Mappers.getMapper( RegionMapper.class );

    Region toEntity(RegionDTO dto);
    RegionDTO toDTO(Region entity);

    List<RegionDTO> toListDTO(List<Region> entity);
    List<Region> toListEntity(List<RegionDTO> dto);

}
