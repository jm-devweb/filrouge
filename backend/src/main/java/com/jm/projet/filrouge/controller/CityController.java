package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.CityDTO;
import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.service.CityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping(value = "/api/cities")

@RestController
@Validated
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAll() {
        List<CityDTO> cities = cityService.findAll ();
        return cities.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cities);
    }

    @GetMapping("/{cityId}")
    @ApiOperation(
            value = "Get a city",
            response = CityDTO.class,
            notes = "Get a city",
            nickname = "getCityById")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Successful operation"),
                    @ApiResponse(code = 404, message = "Not found")
            })
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
