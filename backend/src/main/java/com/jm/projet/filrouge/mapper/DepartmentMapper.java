package com.jm.projet.filrouge.mapper;

import com.jm.projet.filrouge.common.web.EntityDTOMapper;
import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.dto.RegionDTO;
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
public interface DepartmentMapper  extends EntityDTOMapper<Department, DepartmentDTO> {

    DepartmentMapper MAPPER = Mappers.getMapper(DepartmentMapper.class);

    @Mapping(source = "name",target = "name")
    @Override
    public Department toEntity(DepartmentDTO dto);

    @Mapping (source = "name",target = "name")
    @Override
    public DepartmentDTO toDTO(Department entity);

    @Override
    public List<DepartmentDTO> toListDTO(List<Department> entity);

}
