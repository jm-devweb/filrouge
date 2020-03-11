package com.jm.projet.filrouge.mapper;

import com.jm.projet.filrouge.common.web.EntityDTOMapper;
import com.jm.projet.filrouge.dto.CityDTO;
import com.jm.projet.filrouge.model.City;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper extends EntityDTOMapper<City, CityDTO> {

    CityMapper MAPPER = Mappers.getMapper(CityMapper.class);

    @Override
    public City toEntity(CityDTO dto);

    @Override
    public CityDTO toDTO(City entity);

    @Override
    public List<CityDTO> toListDTO(List<City> entity);

}
