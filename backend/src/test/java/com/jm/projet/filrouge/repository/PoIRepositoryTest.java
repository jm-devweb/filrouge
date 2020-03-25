package com.jm.projet.filrouge.repository;

import com.jm.projet.filrouge.model.PoI;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.model.Trip;
import com.jm.projet.filrouge.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PoIRepositoryTest {

    @Autowired
    private PoIRepository poiRepo;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void should_find_ById() throws Exception {
        final Long PARAM = 1L;
        PoI poi = PoI.builder ( )
                .id (1L)
                .name ("Cinéma")
                .trips (new ArrayList<Trip> ( ))
                .users (new ArrayList<User> ( ))
                .build ( );

        Optional<PoI> find = this.poiRepo.findById (PARAM);

        assertThat (find.get ( ).toString ( )).isEqualTo (poi.toString ( ));
    }


    @Test
    public void should_find_all_pois() throws Exception {

        // when
        Iterable<PoI> actual = this.poiRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));

        // then
        assertThat(actual)
                .hasSize(14)
                .doesNotContainNull();
    }
}
