package com.jm.projet.filrouge.mapper;

import com.jm.projet.filrouge.common.web.EntityDTOMapper;
import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.model.Department;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper  extends EntityDTOMapper<Department, DepartmentDTO> {

    DepartmentMapper MAPPER = Mappers.getMapper(DepartmentMapper.class);

    @Override
    public Department toEntity(DepartmentDTO dto);

    @Override
    public DepartmentDTO toDTO(Department entity);

    @Override
    public List<DepartmentDTO> toListDTO(List<Department> entity);
}
