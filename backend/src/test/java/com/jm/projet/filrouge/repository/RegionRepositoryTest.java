package com.jm.projet.filrouge.repository;

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
public class RegionRepositoryTest {

    @Autowired
    private RegionRepository regionRepo;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findById() throws Exception {
        final Long PARAM = 1L ;
        final String RESULT = "Region Parisienne" ;

        Optional<Region> region = this.regionRepo.findById (PARAM);
        assertThat(region.get().getName ()).isEqualTo(RESULT);
    }

    @Test
    public void findAll() throws Exception {
        final String RESULT = "Region Parisienne" ;

        List<Region> regions = this.regionRepo.findAll();
        assertThat(regions.get(0).getName ()).isEqualTo(RESULT);
    }

    @Test
    public void findRegionByName_1() throws Exception {
        final String RESULT = "Region Parisienne" ;

        Optional<Region> region = this.regionRepo.findRegionByName (RESULT);
        assertThat(region.get().getName ()).isEqualTo(RESULT);
    }

    @Test
    public void findRegionByName_2() throws Exception {
        final String RESULT = "Test" ;

        Optional<Region> region = this.regionRepo.findRegionByName (RESULT);
        assertThat(region.isPresent ()).isFalse ();
    }

}
