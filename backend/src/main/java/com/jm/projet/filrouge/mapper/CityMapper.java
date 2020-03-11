package com.jm.projet.filrouge.mapper;

import com.jm.projet.filrouge.common.web.EntityDTOMapper;
import com.jm.projet.filrouge.dto.CityDTO;
import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.model.City;
import com.jm.projet.filrouge.model.Department;
import com.jm.projet.filrouge.model.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * A basic Mapstruct mapper used to do the mapping between {@link Region} and {@link RegionDTO}.
 */

@Mapper(componentModel = "spring")
public interface CityMapper extends EntityDTOMapper<City, CityDTO> {
    CityMapper MAPPER = Mappers.getMapper(CityMapper.class);

    @Mapping(source = "name",target = "name")
    @Override
    public City toEntity(CityDTO dto);

    @Mapping (source = "name",target = "name")
    @Override
    public CityDTO toDTO(City entity);

    @Override
    public List<CityDTO> toListDTO(List<City> entity);

}
