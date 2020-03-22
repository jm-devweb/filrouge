package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.PoIDTO;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.mapper.PoIMapper;
import com.jm.projet.filrouge.model.PoI;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.model.Trip;
import com.jm.projet.filrouge.model.User;
import com.jm.projet.filrouge.repository.PoIRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class PoIServiceTest {

    @Mock
    PoIRepository poiRepo;

    @Mock
    PoIMapper poiMapper;

    private PoIService poiService;

    @BeforeEach
    public void setUp() throws Exception {
        poiService = new PoIService (poiRepo,poiMapper);
    }

    @Test
    public void whenFindAll_thenReturnPoIList() {
        // given
        PoI poi = PoI.builder()
                .id (1L)
                .name ("Cinéma")
                .trips (new ArrayList<Trip> ( ))
                .users (new ArrayList<User> ( ))
                .build();

        List<PoI> expectedPoIs = Arrays.asList(poi);
        doReturn(expectedPoIs).when(poiRepo).findAll();

        // when
        List<PoIDTO> actualPoIs = poiService.findAll();

        System.out.println (expectedPoIs );

        // then
        assertThat(actualPoIs).isEqualTo(poiMapper.INSTANCE.toListDTO (expectedPoIs) );
    }

    @Test
    public void whenFindById_thenReturnPoI() {
        // given
        Optional<PoI> expectedPoI = Optional.of(PoI.builder()
                .id (1L)
                .name ("Cinéma")
                .trips (new ArrayList<Trip> ( ))
                .users (new ArrayList<User> ( ))
                .build());

        doReturn(expectedPoI).when(poiRepo).findById (1L);

        // when
        PoIDTO actualPoI = poiService.findById (1L);

        // then
        assertThat(actualPoI).isEqualTo(poiMapper.INSTANCE.toDTO (expectedPoI.get ()));
    }

}
