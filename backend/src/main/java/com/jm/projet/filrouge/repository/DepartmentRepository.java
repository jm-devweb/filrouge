package com.jm.projet.filrouge.repository;

import com.jm.projet.filrouge.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findDepartmentsByRegionId(Long id);
    Optional<Department> findDepartmentByName(String name);
}

