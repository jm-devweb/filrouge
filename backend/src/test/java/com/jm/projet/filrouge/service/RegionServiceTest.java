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
            doReturn(expectedRegions).when(regionRepo).findAll();

            // when
            List<RegionDTO> actualRegions = regionService.findAll();

            // then
           assertThat(actualRegions).isEqualTo(regionMapper.INSTANCE.toListDTO (expectedRegions) );
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
        RegionDTO actualRegion = regionService.findById (1L);

        // then
        assertThat(actualRegion).isEqualTo(regionMapper.INSTANCE.toDTO (expectedRegion.get ()));
    }


/*
    @Test
    public void whenSave_thenReturnRegion() {
        // given

        RegionDTO expectedRegionDTO = new RegionDTO();
        expectedRegionDTO.setId (1L);
        expectedRegionDTO.setName ("Bourgogne");
        System.out.println (expectedRegionDTO );
        System.out.println (regionMapper.toEntity ( expectedRegionDTO) );

        assertThat(expectedRegionDTO.getName ()).isEqualTo(regionMapper.toEntity ( expectedRegionDTO).getName ());

        Region expectedRegion = new Region();
        expectedRegion.setId (2L);
        expectedRegion.setName ("AAAA");
        System.out.println (expectedRegion );
        System.out.println (regionMapper.toDTO ( expectedRegion) );



   *//*     System.out.println (expectedRegionDTO );
      //  Region expectedRegion = regionMapper.toEntity (expectedRegionDTO);
        doReturn(expectedRegion).when(regionRepo).save (expectedRegion);

        System.out.println (expectedRegion );
        // when
        RegionDTO actualRegionDTO = regionService.save (expectedRegionDTO);

        // then
        System.out.println (actualRegionDTO.toString () );
        System.out.println (expectedRegionDTO.toString () );
        assertThat(actualRegionDTO.toString ()).isEqualTo(expectedRegionDTO.toString ());*//*
    }*/
}
