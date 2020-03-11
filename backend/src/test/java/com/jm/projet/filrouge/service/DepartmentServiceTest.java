package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.model.Department;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.repository.DepartmentRepository;
import com.jm.projet.filrouge.repository.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    DepartmentRepository departmentRepo;

    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() throws Exception {
        departmentService = new DepartmentService (departmentRepo);
    }

    @Test
    public void findById() throws Exception {
        given (departmentRepo.findById (1L )).willReturn (Optional.of (new Department () ));
        Optional<Department> region = departmentService.findById ( 1L);
        assertThat (region).isNotNull ( );
    }

    @Test
    public void findAll() throws Exception {
        given (departmentRepo.findAll ( )).willReturn (new ArrayList<> ( ));
        List<Department> departments = departmentService.findAll ( );
        assertThat (departments).isNotNull ( );
    }

    @Test
    public void findDepartmentsByRegionId() throws Exception {
        given (departmentRepo.findDepartmentsByRegionId (1L )).willReturn (new ArrayList<> ( ));
        List<Department> departments = departmentService.findDepartmentsByRegionId (1L );
        assertThat (departments).isNotNull ( );
    }

    @Test
    public void findDepartmentByName() throws Exception {
        String name = "Test";
        given (departmentRepo.findDepartmentByName (name )).willReturn (Optional.of (new Department () ));
        Optional<Department> region = departmentService.findDepartmentByName (name);
        assertThat (region).isNotNull ( );
    }

}
