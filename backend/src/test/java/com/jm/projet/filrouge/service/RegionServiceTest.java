package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.repository.RegionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class RegionServiceTest {

    @Mock
    RegionRepository regionRepo;

    private RegionService regionService;

    @BeforeEach
    public void setUp() throws Exception {
        regionService = new RegionService (regionRepo);
    }

    @Test
    public void findById() throws Exception {
        given (regionRepo.findById (1L )).willReturn (Optional.of (new Region() ));
        Optional<Region> region = regionService.findById ( 1L);
        assertThat (region).isNotNull ( );
    }

    @Test
    public void findAll() throws Exception {
        given (regionRepo.findAll ( )).willReturn (new ArrayList<> ( ));
        List<Region> regions = regionService.findAll ( );
        assertThat (regions).isNotNull ( );
    }

    @Test
    public void findRegionByName() throws Exception {
        String name = "TEST" ;
        given (regionRepo.findRegionByName (name)).willReturn (Optional.of (new Region() ));
        Optional<Region> region = regionService.findRegionByName (name);
        assertThat (region).isNotNull ( );
    }
}
