package com.jm.projet.filrouge.repository;

import java.util.List;
import java.util.Optional;
import com.jm.projet.filrouge.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findDepartmentsByRegionIdOrderByName(Long id);

}

