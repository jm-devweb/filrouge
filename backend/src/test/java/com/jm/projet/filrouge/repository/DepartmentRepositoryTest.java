package com.jm.projet.filrouge.repository;

import com.jm.projet.filrouge.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findById() throws Exception {
        final Long PARAM = 1L ;
        final String RESULT = "Paris" ;

        Optional<Department> departmentOpt = this.departmentRepository.findById (PARAM);
        assertThat(departmentOpt.get().getName ()).isEqualTo(RESULT);
    }

    @Test
    public void findAll() throws Exception {
        final String RESULT = "Paris" ;

        List<Department> departments = this.departmentRepository.findAll();
        assertThat(departments.get(0).getName ()).isEqualTo(RESULT);
    }

    @Test
    public void findDepartmentsByRegionId() throws Exception {
        final Long PARAM = 1L ;
        final String RESULT = "Paris" ;

        List<Department> departments = this.departmentRepository.findDepartmentsByRegionId(PARAM);
        assertThat(departments.get(0).getName ()).isEqualTo(RESULT);
    }

    @Test
    public void findDepartmentByName() throws Exception {
        final String RESULT = "Paris" ;

        Optional<Department> departmentOpt = this.departmentRepository.findDepartmentByName (RESULT);
        assertThat(departmentOpt.get().getName ()).isEqualTo(RESULT);
    }


}
