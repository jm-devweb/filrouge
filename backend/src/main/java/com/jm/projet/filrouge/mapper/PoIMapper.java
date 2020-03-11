package com.jm.projet.filrouge.mapper;


import com.jm.projet.filrouge.common.web.EntityDTOMapper;
import com.jm.projet.filrouge.dto.PoIDTO;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.model.PoI;
import com.jm.projet.filrouge.model.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * A basic Mapstruct mapper used to do the mapping between {@link Region} and {@link RegionDTO}.
 */

@Mapper(componentModel = "spring")
public interface PoIMapper  extends EntityDTOMapper<PoI, PoIDTO> {

    PoIMapper MAPPER = Mappers.getMapper(PoIMapper.class);

    @Mapping(source = "name",target = "name")
    @Override
    public PoI toEntity(PoIDTO dto);

    @Mapping (source = "name",target = "name")
    @Override
    public PoIDTO toDTO(PoI entity);

    @Override
    public List<PoIDTO> toListDTO(List<PoI> entity);

}
