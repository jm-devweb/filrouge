package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.model.Department;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Get a {@link Region} with the given id
     *
     * @param departmentId
     * @return a {@link Region} matching the given id or null if no Region matches the given id
     */
    public Optional<Department> findById(Long departmentId) {
        return departmentRepository.findById(departmentId)   ;
    }

    /**
     * Get All
     *
     * @return all {@link Region}
     */
    public List<Department> findAll() {
        return departmentRepository.findAll ()   ;
    }

    public List<Department> findDepartmentsByRegionId(Long regionId) {
        return departmentRepository.findDepartmentsByRegionId (regionId)   ;
    }

    public Optional<Department> findDepartmentByName(String department) {
        return departmentRepository.findDepartmentByName (department)   ;
    }


}
