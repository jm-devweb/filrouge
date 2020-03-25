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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class RegionServiceTest {

    private RegionService regionService;

    @Mock
    RegionRepository regionRepo;

    @Autowired
    RegionMapper regionMapper ;

    @BeforeEach
    public  void SetUp() {
        this.regionService = new RegionService(regionRepo, regionMapper);
    }

    @Test
    public void whenFindAll_thenReturnRegionList() {
            // given
            Region region = Region.builder()
                    .id (1L)
                    .name("Auvergne-Rhône-Alpes")
                    .build();
            List<Region> expectedRegions = Arrays.asList(region);
            doReturn(expectedRegions).when(regionRepo).findAll(Sort.by(Sort.Direction.ASC, "name"));

            // when
            List<RegionDTO> actualRegions = regionService.findAll();

            // then
           assertThat(actualRegions).isEqualTo(regionMapper.INSTANCE.toListDTO (expectedRegions) );
        }

    @Test
    public void whenFindAll_thenReturnEmptyList() {
        // given
        List<Region> expectedRegions = Arrays.asList();
        doReturn(expectedRegions).when(regionRepo).findAll(Sort.by(Sort.Direction.ASC, "name"));

        // when
        List<RegionDTO> actualRegions = regionService.findAll();

        // then
        assertThat(actualRegions).isEmpty ();
    }

    @Test
    public void whenFindById_thenReturnRegion() {
        // given
        Optional<Region> expectedRegion = Optional.of( Region.builder()
                .id (1L)
                .name("Auvergne-Rhône-Alpes")
                .build());

        doReturn(expectedRegion).when(regionRepo).findById (1L);

        // when
        Optional<RegionDTO> actualRegion = regionService.findById (1L);

        // then
        assertThat(actualRegion.get ()).isEqualTo(regionMapper.INSTANCE.toDTO (expectedRegion.get ()));
    }

    @Test
    public void whenFindById_thenReturnEmpty() {
        // given
        doReturn(Optional.empty ()).when(regionRepo).findById (1L);

        // when
        Optional<RegionDTO> actualRegion = regionService.findById (1L);

        // then
        assertThat(actualRegion).isEmpty ();
    }
}
