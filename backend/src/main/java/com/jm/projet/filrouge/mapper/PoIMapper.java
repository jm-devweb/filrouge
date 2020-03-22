package com.jm.projet.filrouge.mapper;

import com.jm.projet.filrouge.common.web.EntityDTOMapper;
import com.jm.projet.filrouge.dto.PoIDTO;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.model.PoI;
import com.jm.projet.filrouge.model.Region;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PoIMapper  extends EntityDTOMapper<PoI, PoIDTO> {
    PoIMapper INSTANCE = Mappers.getMapper(PoIMapper.class );

    PoI toEntity(PoIDTO dto);
    PoIDTO toDTO(PoI entity);

    List<PoIDTO> toListDTO(List<PoI> entity);
    List<PoI> toListEntity(List<PoIDTO> dto);

}
