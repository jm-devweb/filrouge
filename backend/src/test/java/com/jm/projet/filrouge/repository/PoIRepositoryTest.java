package com.jm.projet.filrouge.repository;

import com.jm.projet.filrouge.model.PoI;
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
public class PoIRepositoryTest {

    @Autowired
    private PoIRepository poiRepo;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findById() throws Exception {
        final Long PARAM = 1L ;
        final String RESULT = "Cinéma" ;

        Optional<PoI> poi = this.poiRepo.findById (PARAM);
        assertThat(poi.get().getName ()).isEqualTo(RESULT);
    }

    @Test
    public void findAll() throws Exception {
        final String RESULT = "Cinéma" ;

        List<PoI> pois = this.poiRepo.findAll();
        assertThat(pois.get(0).getName ()).isEqualTo(RESULT);
    }
}
