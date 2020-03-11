package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.mapper.RegionMapper;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.repository.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class RegionServiceTest {

    private RegionService regionService;

    @Mock
    RegionRepository regionRepo;

    @Mock
    RegionMapper regionMapper ;

    @BeforeEach
    public  void SetUp() {
        this.regionService = new RegionService(regionRepo, regionMapper);
    }

    @Test
    public void whenFindAll_thenReturnRegionList() {
            // given
            Region region = Region.builder()
                    .name("Bourgogne")
                    .build();
            List<Region> expectedRegions = Arrays.asList(region);
            doReturn(expectedRegions).when(regionRepo).findAll();

            // when
            List<RegionDTO> actualRegions = regionService.findAll();

            // then
            assertThat(actualRegions).isEqualTo(regionMapper.toListDTO (expectedRegions));
        }

    @Test
    public void whenFindById_thenReturnProduct() {
        // given
        Optional<Region> expectedRegion = Optional.of( Region.builder()
                .name("Bourgogne")
                .build());

        doReturn(expectedRegion).when(regionRepo).findById (1L);

        // when
        RegionDTO actualRegion = regionService.findById (1L);

        // then
        assertThat(actualRegion).isEqualTo(regionMapper.toDTO (expectedRegion.get ()));
    }


    @Test
    public void whenFindByName_thenReturnProduct() {
        // given
        Optional<Region> expectedRegion = Optional.of( Region.builder()
                .name("Bourgogne")
                .build());

        doReturn(expectedRegion).when(regionRepo).findRegionByName ("Bourgogne");

        // when
        RegionDTO actualRegion = regionService.findRegionByName ("Bourgogne");

        // then
        assertThat(actualRegion).isEqualTo(regionMapper.toDTO (expectedRegion.get ()));
    }

}
