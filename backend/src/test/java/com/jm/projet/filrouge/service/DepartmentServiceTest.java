package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.mapper.DepartmentMapper;
import com.jm.projet.filrouge.model.Department;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    private DepartmentService departmentService;

    @Mock
    DepartmentRepository departmentRepo;

    @Mock
    DepartmentMapper departmentMapper ;

    @BeforeEach
    public  void SetUp() {
        this.departmentService = new DepartmentService(departmentRepo, departmentMapper);
    }

    @Test
    public void whenFindAll_thenReturnDepartmentList() {
        // given
        Department department = Department.builder()
                .id (1L)
                .name("Ain")
                .region (new Region(1L,"Auvergne-Rhône-Alpes"))
                .build();
        List<Department> expectedDepartments = Arrays.asList(department);
        doReturn(expectedDepartments).when(departmentRepo).findAll(Sort.by(Sort.Direction.ASC, "name"));

        // when
        List<DepartmentDTO> actualDepartments = departmentService.findAll();

        // then
        assertThat(actualDepartments).isEqualTo(departmentMapper.INSTANCE.toListDTO (expectedDepartments));
    }

    @Test
    public void whenFindById_thenReturnDepartment() {
        Optional<Department> expectedDepartment = Optional.of( Department.builder()
                .id (1L)
                .name("Auvergne-Rhône-Alpes")
                .build());

        doReturn(expectedDepartment).when(departmentRepo).findById (1L);

        // when
        DepartmentDTO actualDepartment = departmentService.findById (1L);

        // then
        assertThat(actualDepartment).isEqualTo(departmentMapper.INSTANCE.toDTO (expectedDepartment.get ()));
    }


    @Test
    public void whenDepartmentsByRegionId_thenReturnDepartmentList() {
        Department department = Department.builder()
                .id (1L)
                .name("Ain")
                .region (new Region(1L,"Auvergne-Rhône-Alpes"))
                .build();
        List<Department> expectedDepartments = Arrays.asList(department);
        doReturn(expectedDepartments).when(departmentRepo).findDepartmentsByRegionIdOrderByName (1L);

        // when
        List<DepartmentDTO> actualDepartments = departmentService.findDepartmentsByRegionId (1L);

        // then
        assertThat(actualDepartments).isEqualTo(departmentMapper.INSTANCE.toListDTO (expectedDepartments));
    }

}
