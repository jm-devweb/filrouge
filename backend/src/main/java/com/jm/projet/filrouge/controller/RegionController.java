package com.jm.projet.filrouge.controller;

import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.mapper.RegionMapper;
import com.jm.projet.filrouge.model.Region;
import com.jm.projet.filrouge.service.DepartmentService;
import com.jm.projet.filrouge.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
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
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/regions")
@Validated
@Api(tags = "Region")
@Slf4j
public class RegionController {

    private final RegionService regionService;

    @Autowired
    RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public ResponseEntity<List<RegionDTO>> getAll() {
        List<RegionDTO> regions = regionService.findAll ();
        return (!regions.isEmpty ()) ? ResponseEntity.ok(regions) : new ResponseEntity(HttpStatus.NO_CONTENT) ;
    }

    @GetMapping("/{regionId}")
    public ResponseEntity<RegionDTO> getRegionById(
            @ApiParam(value = "The id of the region to retrieve", required = true)
            @PathVariable(value = "regionId")
                    Long regionId) {
        // log.info("[ENDPOINT] Received request to get a region");
        RegionDTO region = regionService.findById(regionId);
        return (!Objects.isNull (region)) ? ResponseEntity.ok(region) : new ResponseEntity(HttpStatus.NO_CONTENT) ;
    }
}
