package com.jm.projet.filrouge.repository;

import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.model.Trip;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class TripRepositoryTest {

    @Autowired
    private TripRepository objectRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CityRepository cityRepo;


    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void should_find_ById_ReturnTrip() throws Exception {
        final Long PARAM = 1L ;

        Trip object = Trip.builder()
                .id (1L)
                .name ("Cinéma : les tontons ...")
                .build ();
        Optional<Trip> find = this.objectRepo.findById (PARAM);
        assertThat(find.get().getName ()).isEqualTo(object.getName ());
    }

    @Test
    public void should_find_ById_ReturnEmpty() throws Exception {
        final Long PARAM = 0L ;

        Optional<Trip> find = this.objectRepo.findById (PARAM);
        assertThat(find).isEmpty ();
    }
}
