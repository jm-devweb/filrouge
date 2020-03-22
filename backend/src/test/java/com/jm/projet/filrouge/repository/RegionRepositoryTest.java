package com.jm.projet.filrouge.repository;

import com.jm.projet.filrouge.model.Region;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class RegionRepositoryTest {

    @Autowired
    private RegionRepository regionRepo;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void should_find_ById() throws Exception {
        final Long PARAM = 1L ;
        Region region = new Region(1L,"Auvergne-Rhône-Alpes");

        Optional<Region> find = this.regionRepo.findById (PARAM);
        assertThat(find.get()).isEqualTo(region);
    }

    @Test
    public void should_find_all_regions() throws Exception {

        // when
        Iterable<Region> actual = this.regionRepo.findAll();

        // then
        assertThat(actual)
                .hasSize(13)
                .doesNotContainNull();
    }

}
