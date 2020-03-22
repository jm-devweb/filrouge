package com.jm.projet.filrouge.service;

import com.jm.projet.filrouge.dto.CityDTO;
import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.mapper.CityMapper;
import com.jm.projet.filrouge.model.City;
import com.jm.projet.filrouge.model.Department;
import com.jm.projet.filrouge.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<City> found = cityRepository.findCitiesByDepartmentId (id);
        return cityMapper.INSTANCE.toListDTO (found) ;
    }
}
