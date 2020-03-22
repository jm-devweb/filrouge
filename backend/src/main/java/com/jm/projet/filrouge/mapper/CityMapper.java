package com.jm.projet.filrouge.mapper;

import com.jm.projet.filrouge.common.web.EntityDTOMapper;
import com.jm.projet.filrouge.dto.CityDTO;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.model.City;
import com.jm.projet.filrouge.model.Region;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper extends EntityDTOMapper<City, CityDTO> {
    CityMapper INSTANCE = Mappers.getMapper( CityMapper.class );

    City toEntity(CityDTO dto);
    CityDTO toDTO(City entity);

    List<CityDTO> toListDTO(List<City> entity);
    List<City> toListEntity(List<CityDTO> dto);

}
