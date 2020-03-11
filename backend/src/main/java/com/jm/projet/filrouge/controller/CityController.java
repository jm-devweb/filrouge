package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.CityDTO;
import com.jm.projet.filrouge.dto.DepartmentDTO;
import com.jm.projet.filrouge.mapper.CityMapper;
import com.jm.projet.filrouge.mapper.DepartmentMapper;
import com.jm.projet.filrouge.model.City;
import com.jm.projet.filrouge.model.Department;
import com.jm.projet.filrouge.service.CityService;
import com.jm.projet.filrouge.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/cities")
@Validated
@Api(tags = "City")
@Slf4j
public class CityController {

    private final CityService cityService;

    private final CityMapper cityMapper;

    @Autowired
    CityController(CityService cityService, CityMapper cityMapper) {
        this.cityService = cityService;
        this.cityMapper= cityMapper;
    }

    @GetMapping()
    public ResponseEntity<List<CityDTO>> find(
            @ApiParam(value = "Id of Department for 'idDepartment'")
            @Valid
            @RequestParam(value = "idDepartment", required = false, defaultValue = "0") Long idDepartment) {
        List<City> cities = null ;
        if(idDepartment == 0L) {
            cities = cityService.findAll ( );
        }
        else {
            cities = cityService.findCitiesByDepartmentId (idDepartment);
        }
        return (!cities.isEmpty ()) ? ResponseEntity.ok(cityMapper.toListDTO (cities)) : ResponseEntity.notFound().build() ;
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<CityDTO> getCityById(
            @ApiParam(value = "The id of the city to retrieve", required = true)
            @PathVariable(value = "cityId")
                    Long cityId) {
        // log.info("[ENDPOINT] Received request to get a region");
        Optional<City> cityOpt = cityService.findById(cityId);
        return (cityOpt.isPresent ()) ? ResponseEntity.ok(cityMapper.toDTO (cityOpt.get ())) : ResponseEntity.notFound().build() ;
    }
}
