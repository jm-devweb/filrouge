package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.dto.TripDTO;
import com.jm.projet.filrouge.mapper.TripMapper;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.model.Trip;
import com.jm.projet.filrouge.repository.TripRepository;
import com.jm.projet.filrouge.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {


    private TripService objectService;

    @Mock
    TripRepository objectRepo;

    @Mock
    UserRepository userRepo;

    @Autowired
    TripMapper objectMapper ;

    @BeforeEach
    public  void SetUp() {
        this.objectService = new TripService(objectRepo,userRepo, objectMapper);
    }

    @Test
    public void whenFindById_thenReturnTrip() {
        // given
        final Long PARAM = 1L ;

        Trip expectedObject = Trip.builder()
                .id (1L)
                .name ("Cinéma : les tontons ...")
                .build ();

        doReturn(Optional.of(expectedObject)).when(objectRepo).findById (PARAM);

        // when
        Optional<TripDTO> actualObject = objectService.findById (1L);

        // then
        assertThat(actualObject.get ()).isEqualTo(objectMapper.INSTANCE.toDTO (expectedObject));
    }

    @Test
    public void whenFindById_thenReturnEmpty() {
        // given
        doReturn(Optional.empty ()).when(objectRepo).findById (1L);

        // when
        Optional<TripDTO> actualObject = objectService.findById (1L);

        // then
        assertThat(actualObject).isEmpty ();
    }


/*    @Test
    public void whenRegisterNewUser_thenReturnTrip() {
        // given
        final Long PARAM = 1L ;

        Trip expectedObject = Trip.builder()
                .id (1L)
                .name ("Cinéma : les tontons ...")
                .build ();

        doReturn(Optional.of(expectedObject)).when(objectRepo).save (expectedObject);

        // when
        Optional<TripDTO> actualObject = objectService.registerService (1L,1L);


        // then
        assertThat(actualObject).isEmpty();
      // assertThat(actualObject.get ()).isEqualTo(objectMapper.INSTANCE.toDTO (expectedObject));
    }*/

}
