package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.CityDTO;
import com.jm.projet.filrouge.mapper.CityMapper;
import com.jm.projet.filrouge.model.City;
import com.jm.projet.filrouge.repository.CityRepository;
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
public class CityServiceTest {

    private CityService cityService;

    @Mock
    CityRepository cityRepo;

    @Mock
    CityMapper cityMapper ;

    @BeforeEach
    public  void SetUp() {
        this.cityService = new CityService(cityRepo, cityMapper);
    }

    @Test
    public void whenFindAll_thenReturnCityList() {
        // given
        City city = City.builder()
                .name("Paris")
                .build();
        List<City> expectedCity = Arrays.asList(city);
        doReturn(expectedCity).when(cityRepo).findAll();

        // when
        List<CityDTO> actualCity = cityService.findAll();

        // then
        assertThat(actualCity).isEqualTo(cityMapper.INSTANCE.toListDTO (expectedCity));
    }

    @Test
    public void whenFindById_thenReturnCity() {
        // given
        Optional<City> expectedCity = Optional.of( City.builder()
                .name("Paris")
                .build());

        doReturn(expectedCity).when(cityRepo).findById (1L);

        // when
        CityDTO actualCity = cityService.findById (1L);

        // then
        assertThat(actualCity).isEqualTo(cityMapper.INSTANCE.toDTO (expectedCity.get ()));
    }

    @Test
    public void whenFindCitiesByDepartmentId_thenReturnCityList() {
        // given
        City city = City.builder()
                .name("Paris")
                .build();
        List<City> expectedCity = Arrays.asList(city);
        doReturn(expectedCity).when(cityRepo).findCitiesByDepartmentId (1L);

        // when
        List<CityDTO> actualCity = cityService.findCitiesByDepartmentId (1L);

        // then
        assertThat(actualCity).isEqualTo(cityMapper.INSTANCE.toListDTO (expectedCity));
    }

}
