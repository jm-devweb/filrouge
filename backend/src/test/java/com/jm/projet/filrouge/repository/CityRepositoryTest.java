package com.jm.projet.filrouge.repository;

import com.jm.projet.filrouge.model.City;
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
public class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void should_find_ById() throws Exception {
        final Long PARAM = 1L ;
        final String RESULT = "OZAN" ;

        Optional<City> city = this.cityRepo.findById (PARAM);
        assertThat(city.get().getName ()).isEqualTo(RESULT);
    }

    @Test
    public void should_find_all_cities() throws Exception {
        // when
        Iterable<City> actual = this.cityRepo.findAll();

        // then
        assertThat(actual)
                .hasSize(36568)
                .doesNotContainNull();
    }

    @Test
    public void should_find_all_citiesByRegionId() throws Exception {
        final Long PARAM = 1L ;
        final String RESULT = "OZAN" ;

        List<City> cities = this.cityRepo.findCitiesByDepartmentIdOrderByName(PARAM);
        assertThat(cities.get(0).getName ()).isEqualTo(RESULT);
    }

}
