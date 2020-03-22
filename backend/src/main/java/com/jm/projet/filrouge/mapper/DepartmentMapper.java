package com.jm.projet.filrouge.mapper;

import com.jm.projet.filrouge.common.web.EntityDTOMapper;
import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.model.Department;
import com.jm.projet.filrouge.model.Region;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper  extends EntityDTOMapper<Department, DepartmentDTO> {
    DepartmentMapper INSTANCE = Mappers.getMapper( DepartmentMapper.class );

    Department toEntity(DepartmentDTO dto);
    DepartmentDTO toDTO(Department entity);

    List<DepartmentDTO> toListDTO(List<Department> entity);
    List<Department> toListEntity(List<DepartmentDTO> dto);


}
