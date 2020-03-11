package com.jm.projet.filrouge.mapper;

import com.jm.projet.filrouge.common.web.EntityDTOMapper;
import com.jm.projet.filrouge.dto.PoIDTO;
import com.jm.projet.filrouge.model.PoI;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PoIMapper  extends EntityDTOMapper<PoI, PoIDTO> {

    PoIMapper MAPPER = Mappers.getMapper(PoIMapper.class);

    @Override
    public PoI toEntity(PoIDTO dto);

    @Override
    public PoIDTO toDTO(PoI entity);

    @Override
    public List<PoIDTO> toListDTO(List<PoI> entity);
}
