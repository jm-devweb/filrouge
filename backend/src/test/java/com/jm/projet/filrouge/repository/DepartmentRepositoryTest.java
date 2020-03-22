package com.jm.projet.filrouge.repository;

import com.jm.projet.filrouge.model.Department;

import com.jm.projet.filrouge.model.Region;
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
    public void should_find_ById() throws Exception {
        final Long PARAM = 1L ;
        Department department = new Department(1L,"Ain",new Region(1L,"Auvergne-Rhône-Alpes"));

        Optional<Department> find = this.departmentRepository.findById (PARAM);
        assertThat(find.get()).isEqualTo(department);
    }

    @Test
    public void should_find_all_department() throws Exception {

        // when
        Iterable<Department> actual = this.departmentRepository.findAll();

        // then
        assertThat(actual)
                .hasSize(95)
                .doesNotContainNull();
    }

    @Test
    public void should_find_all_departmentByRegionId() throws Exception {
        final Long PARAM = 1L ;

        // when
        Iterable<Department> actual = this.departmentRepository.findDepartmentsByRegionId (PARAM);

        // then
        assertThat(actual)
                .hasSize(12)
                .doesNotContainNull();
    }

}
