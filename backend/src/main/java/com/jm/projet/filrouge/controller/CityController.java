package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.CityDTO;
import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RequestMapping(value = "/api/cities")

@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAll() {
        List<CityDTO> cities = cityService.findAll ();
        return cities.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cities);
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<CityDTO> getCityById(
            @PathVariable(value = "cityId")
                    Long cityId) {
        CityDTO city = cityService.findById(cityId);
        return Objects.isNull(city) ? ResponseEntity.notFound().build() : ResponseEntity.ok(city);
    }

    @GetMapping("/departments/{departmentId}")
    public ResponseEntity<List<CityDTO>> getCityByDepartmentId(
            @PathVariable(value = "departmentId")
                    Long departmentId) {
        List<CityDTO> cityDTO = cityService.findCitiesByDepartmentId (departmentId);
        return Objects.isNull(cityDTO) ? ResponseEntity.notFound().build() : ResponseEntity.ok(cityDTO);
    }
}
