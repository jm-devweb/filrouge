package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.model.City;
import com.jm.projet.filrouge.model.Department;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.repository.CityRepository;
import com.jm.projet.filrouge.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * Get a {@link Region} with the given id
     *
     * @param departmentId
     * @return a {@link Region} matching the given id or null if no Region matches the given id
     */
    public Optional<City> findById(Long cityId) {
        return cityRepository.findById(cityId)   ;
    }

    /**
     * Get All
     *
     * @return all {@link Region}
     */
    public List<City> findAll() {
        return cityRepository.findAll ()   ;
    }

    public List<City> findCitiesByDepartmentId(Long departmentId) {
        return cityRepository.findCitiesByDepartmentId (departmentId)   ;
    }


}
