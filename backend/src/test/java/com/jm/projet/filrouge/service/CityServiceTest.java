package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.model.City;
import com.jm.projet.filrouge.model.Department;
import com.jm.projet.filrouge.repository.CityRepository;
import com.jm.projet.filrouge.repository.DepartmentRepository;
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
public class CityServiceTest {

    @Mock
    CityRepository cityRepo;

    private CityService cityService;

    @BeforeEach
    public void setUp() throws Exception {
        cityService = new CityService (cityRepo);
    }

    @Test
    public void findById() throws Exception {
        given (cityRepo.findById (1L )).willReturn (Optional.of (new City () ));
        Optional<City> city = cityService.findById ( 1L);
        assertThat (city).isNotNull ( );
    }

    @Test
    public void findAll() throws Exception {
        given (cityRepo.findAll ( )).willReturn (new ArrayList<> ( ));
        List<City> cities = cityService.findAll ( );
        assertThat (cities).isNotNull ( );
    }

    @Test
    public void findCitiesByDepartmentId() throws Exception {
        given (cityRepo.findCitiesByDepartmentId (1L )).willReturn (new ArrayList<> ( ));
        List<City> cities = cityService.findCitiesByDepartmentId (1L );
        assertThat (cities).isNotNull ( );
    }
}
