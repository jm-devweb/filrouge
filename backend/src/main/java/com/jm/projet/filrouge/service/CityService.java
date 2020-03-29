package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.CityDTO;
import com.jm.projet.filrouge.mapper.CityMapper;
import com.jm.projet.filrouge.model.City;
import com.jm.projet.filrouge.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.jm.projet.filrouge.repository.CitySpecifications.hasDepartmentOptional;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Slf4j
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public CityDTO findById(Long cityId) {
        Optional<City> found = cityRepository.findById(cityId);
        return found.isPresent () ? cityMapper.INSTANCE.toDTO (found.get ()) :  null ;
    }

    public List<CityDTO> findAll() {
        List<City> found = cityRepository.findAll();
        return cityMapper.INSTANCE.toListDTO (found) ;
    }

    public List<CityDTO> findCitiesByDepartmentId(Long id) {
        List<City> found = cityRepository.findCitiesByDepartmentIdOrderByName (id);
        return cityMapper.INSTANCE.toListDTO (found) ;
    }

    /**
     * Filter cities
     *
     * @param pageable the page to get
     * @param departmentId the department id to get (optional)
     * @return a page of cities
     */
    public Page<CityDTO> getFilteredCities(
            Pageable pageable, int departmentId) {
        // Find filtered cities in the repository

        Page<City> cityPage = cityRepository.findAll (pageable);

     //   Page<City> cityPage = cityRepository.findAll(
       //         where(hasDepartmentOptional(departmentId)), pageable);

        return cityPage.map(city -> cityMapper.INSTANCE.toDTO(city));
    }




}
