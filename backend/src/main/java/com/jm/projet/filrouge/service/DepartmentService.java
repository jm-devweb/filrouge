package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.mapper.DepartmentMapper;
import com.jm.projet.filrouge.model.Department;
import com.jm.projet.filrouge.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentDTO findById(Long departmentId) {
        Optional<Department> found = departmentRepository.findById(departmentId);
        return found.isPresent () ? departmentMapper.INSTANCE.toDTO (found.get ()) :  null ;
    }

    public List<DepartmentDTO> findAll() {
        List<Department> found = departmentRepository.findAll();
        return departmentMapper.INSTANCE.toListDTO (found) ;
    }

     public List<DepartmentDTO> findDepartmentsByRegionId(Long id) {
         List<Department> found = departmentRepository.findDepartmentsByRegionIdOrderByName (id);
         return departmentMapper.INSTANCE.toListDTO (found) ;
    }

}
